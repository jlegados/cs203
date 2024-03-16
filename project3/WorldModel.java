import processing.core.PImage;

import java.util.*;

/**
 * Represents the 2D World in which this simulation is running.
 * Keeps track of the size of the world, the background image for each
 * location in the world, and the entities that populate the world.
 */
public final class WorldModel {
    private int numRows;
    private int numCols;
    private Background[][] background;
    private Entity[][] occupancy;
    private Set<Entity> entities;

    public WorldModel() {

    }

    /**
     * Helper method for testing. Don't move or modify this method.
     */
    public List<String> log(){
        List<String> list = new ArrayList<>();
        for (Entity entity : entities) {
            String log = entity.log();
            if(log != null) list.add(log);
        }
        return list;
    }

    public void parseSapling(String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == Entity.SAPLING_NUM_PROPERTIES) {
            int health = Integer.parseInt(properties[Entity.SAPLING_HEALTH]);
            Entity entity = Entity.createSapling(id, pt, imageStore.getImageList(Entity.SAPLING_KEY), health);
            this.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", Entity.SAPLING_KEY, Entity.SAPLING_NUM_PROPERTIES));
        }
    }

    public void parseDude(String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == Entity.DUDE_NUM_PROPERTIES) {
            Entity entity = Entity.createDudeNotFull(id, pt, Double.parseDouble(properties[Entity.DUDE_ACTION_PERIOD]), Double.parseDouble(properties[Entity.DUDE_ANIMATION_PERIOD]), Integer.parseInt(properties[Entity.DUDE_LIMIT]), imageStore.getImageList(Entity.DUDE_KEY));
            this.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", Entity.DUDE_KEY, Entity.DUDE_NUM_PROPERTIES));
        }
    }

    public void parseFairy(String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == Entity.FAIRY_NUM_PROPERTIES) {
            Entity entity = Entity.createFairy(id, pt, Double.parseDouble(properties[Entity.FAIRY_ACTION_PERIOD]), Double.parseDouble(properties[Entity.FAIRY_ANIMATION_PERIOD]), imageStore.getImageList(Entity.FAIRY_KEY));
            this.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", Entity.FAIRY_KEY, Entity.FAIRY_NUM_PROPERTIES));
        }
    }

    public void parseTree(String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == Entity.TREE_NUM_PROPERTIES) {
            Entity entity = Entity.createTree(id, pt, Double.parseDouble(properties[Entity.TREE_ACTION_PERIOD]), Double.parseDouble(properties[Entity.TREE_ANIMATION_PERIOD]), Integer.parseInt(properties[Entity.TREE_HEALTH]), imageStore.getImageList(Entity.TREE_KEY));
            this.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", Entity.TREE_KEY, Entity.TREE_NUM_PROPERTIES));
        }
    }

    public void parseObstacle(String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == Entity.OBSTACLE_NUM_PROPERTIES) {
            Entity entity = Entity.createObstacle(id, pt, Double.parseDouble(properties[Entity.OBSTACLE_ANIMATION_PERIOD]), imageStore.getImageList(Entity.OBSTACLE_KEY));
            this.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", Entity.OBSTACLE_KEY, Entity.OBSTACLE_NUM_PROPERTIES));
        }
    }

    public void parseHouse(String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == Entity.HOUSE_NUM_PROPERTIES) {
            Entity entity = Entity.createHouse(id, pt, imageStore.getImageList(Entity.HOUSE_KEY));
            this.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", Entity.HOUSE_KEY, Entity.HOUSE_NUM_PROPERTIES));
        }
    }

    public void parseStump(String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == Entity.STUMP_NUM_PROPERTIES) {
            Entity entity = Entity.createStump(id, pt, imageStore.getImageList(Entity.STUMP_KEY));
            this.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", Entity.STUMP_KEY, Entity.STUMP_NUM_PROPERTIES));
        }
    }

    public void tryAddEntity(Entity entity) {
        if (this.isOccupied(entity.getPosition())) {
            // arguably the wrong type of exception, but we are not
            // defining our own exceptions yet
            throw new IllegalArgumentException("position occupied");
        }

        this.addEntity(entity);
    }

    public boolean isOccupied(Point pos) {
        return this.withinBounds(pos) && this.getOccupancyCell(pos) != null;
    }

    public boolean withinBounds(Point pos) {
        return pos.y >= 0 && pos.y < this.numRows && pos.x >= 0 && pos.x < this.numCols;
    }

    public static Optional<Entity> nearestEntity(List<Entity> entities, Point pos) {
        if (entities.isEmpty()) {
            return Optional.empty();
        } else {
            Entity nearest = entities.get(0);
            int nearestDistance = Point.distanceSquared(nearest.getPosition(), pos);

            for (Entity other : entities) {
                int otherDistance = Point.distanceSquared(other.getPosition(), pos);

                if (otherDistance < nearestDistance) {
                    nearest = other;
                    nearestDistance = otherDistance;
                }
            }

            return Optional.of(nearest);
        }
    }

    public Optional<Entity> findNearest(Point pos, List<Class> kinds) {
        List<Entity> ofType = new LinkedList<>();
        for (Class kind : kinds) {
            for (Entity entity : this.entities) {
                if (entity.getClass() == kind) {
                    ofType.add(entity);
                }
            }
        }

        return nearestEntity(ofType, pos);
    }

    /*
           Assumes that there is no entity currently occupying the
           intended destination cell.
        */
    public void addEntity(Entity entity) {
        if (withinBounds(entity.getPosition())) {
            this.setOccupancyCell(entity.getPosition(), entity);
            this.entities.add(entity);
        }
    }

    public void moveEntity(EventScheduler scheduler, Entity entity, Point pos) {
        Point oldPos = entity.getPosition();
        if (withinBounds(pos) && !pos.equals(oldPos)) {
            this.setOccupancyCell(oldPos, null);
            Optional<Entity> occupant = this.getOccupant(pos);
            occupant.ifPresent(target -> removeEntity(scheduler, target));
            this.setOccupancyCell(pos, entity);
            entity.setPosition(pos);
        }
    }

    public void removeEntity(EventScheduler scheduler, Entity entity) {
        scheduler.unscheduleAllEvents(entity);
        removeEntityAt(entity.getPosition());
    }

    public void removeEntityAt(Point pos) {
        if (withinBounds(pos) && this.getOccupancyCell(pos) != null) {
            Entity entity = this.getOccupancyCell(pos);

            /* This moves the entity just outside of the grid for
             * debugging purposes. */
            entity.setPosition(new Point(-1, -1));
            this.entities.remove(entity);
            this.setOccupancyCell(pos, null);
        }
    }

    public Optional<Entity> getOccupant(Point pos) {
        if (isOccupied(pos)) {
            return Optional.of(getOccupancyCell(pos));
        } else {
            return Optional.empty();
        }
    }

    public Entity getOccupancyCell(Point pos) {
        return this.occupancy[pos.y][pos.x];
    }

    public void setOccupancyCell(Point pos, Entity entity) {
        this.occupancy[pos.y][pos.x] = entity;
    }

    public void load(Scanner saveFile, ImageStore imageStore, Background defaultBackground){
        parseSaveFile(saveFile, imageStore, defaultBackground);
        if(this.background == null){
            this.background = new Background[this.numRows][this.numCols];
            for (Background[] row : this.background)
                Arrays.fill(row, defaultBackground);
        }
        if(this.occupancy == null){
            this.occupancy = new Entity[this.numRows][this.numCols];
            this.entities = new HashSet<>();
        }
    }

    public void parseSaveFile(Scanner saveFile, ImageStore imageStore, Background defaultBackground){
        String lastHeader = "";
        int headerLine = 0;
        int lineCounter = 0;
        while(saveFile.hasNextLine()){
            lineCounter++;
            String line = saveFile.nextLine().strip();
            if(line.endsWith(":")){
                headerLine = lineCounter;
                lastHeader = line;
                switch (line){
                    case "Backgrounds:" -> this.background = new Background[this.numRows][this.numCols];
                    case "Entities:" -> {
                        this.occupancy = new Entity[this.numRows][this.numCols];
                        this.entities = new HashSet<>();
                    }
                }
            }else{
                switch (lastHeader){
                    case "Rows:" -> this.numRows = Integer.parseInt(line);
                    case "Cols:" -> this.numCols = Integer.parseInt(line);
                    case "Backgrounds:" -> parseBackgroundRow(line, lineCounter-headerLine-1, imageStore);
                    case "Entities:" -> parseEntity(line, imageStore);
                }
            }
        }
    }

    public void parseBackgroundRow(String line, int row, ImageStore imageStore) {
        String[] cells = line.split(" ");
        if(row < this.numRows){
            int rows = Math.min(cells.length, this.numCols);
            for (int col = 0; col < rows; col++){
                this.background[row][col] = new Background(cells[col], imageStore.getImageList(cells[col]));
            }
        }
    }

    public void parseEntity(String line, ImageStore imageStore) {
        String[] properties = line.split(" ", Entity.ENTITY_NUM_PROPERTIES + 1);
        if (properties.length >= Entity.ENTITY_NUM_PROPERTIES) {
            String key = properties[Entity.PROPERTY_KEY];
            String id = properties[Entity.PROPERTY_ID];
            Point pt = new Point(Integer.parseInt(properties[Entity.PROPERTY_COL]), Integer.parseInt(properties[Entity.PROPERTY_ROW]));

            properties = properties.length == Entity.ENTITY_NUM_PROPERTIES ?
                    new String[0] : properties[Entity.ENTITY_NUM_PROPERTIES].split(" ");

            switch (key) {
                case Entity.OBSTACLE_KEY -> parseObstacle(properties, pt, id, imageStore);
                case Entity.DUDE_KEY -> parseDude(properties, pt, id, imageStore);
                case Entity.FAIRY_KEY -> parseFairy(properties, pt, id, imageStore);
                case Entity.HOUSE_KEY -> parseHouse(properties, pt, id, imageStore);
                case Entity.TREE_KEY -> parseTree(properties, pt, id, imageStore);
                case Entity.SAPLING_KEY -> parseSapling(properties, pt, id, imageStore);
                case Entity.STUMP_KEY -> parseStump(properties, pt, id, imageStore);
                default -> throw new IllegalArgumentException("Entity key is unknown");
            }
        }else{
            throw new IllegalArgumentException("Entity must be formatted as [key] [id] [x] [y] ...");
        }
    }

    public Background getBackgroundCell(Point pos) {
        return this.background[pos.y][pos.x];
    }

    public void setBackgroundCell(Point pos, Background background) {
        this.background[pos.y][pos.x] = background;
    }

    public Optional<PImage> getBackgroundImage(Point pos) {
        if (withinBounds(pos)) {
            return Optional.of(ImageUtil.getCurrentImage(this.getBackgroundCell(pos)));
        } else {
            return Optional.empty();
        }
    }

    public Set<Entity> getEntities() {
        return entities;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }
}
