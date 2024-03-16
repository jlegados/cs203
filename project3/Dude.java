import processing.core.PImage;

import java.util.List;

public abstract class Dude extends ActionEntity implements Movement{
    private final int resourceLimit;
    private int resourceCount;
    public Dude (String id, Point position, List<PImage> images, double animationPeriod,
                 double actionPeriod, int resourceLimit, int resourceCount) {
        super(id, position, images, animationPeriod, actionPeriod);
        this.resourceLimit = resourceLimit;
        this.resourceCount = resourceCount;
    }


    public int getResourceLimit() {
        return this.resourceLimit;
    }

    public void setResourceLimit(int resourceCount){
        this.resourceCount = resourceCount;
    }

    public int getResourceCount(){
        return this.resourceCount;
    }
    @Override
    public Point nextPosition(WorldModel world, Point destPos) {
        PathingStrategy strat = new AStarPathingStrategy();
        List<Point> path = strat.computePath(
                this.getPosition(),
                destPos,
                point -> world.withinBounds(point) && (!world.isOccupied(point) || world.getOccupancyCell(point).getClass() == Stump.class),
                (p1, p2) -> Point.adjacent(p1, p2),
                PathingStrategy.CARDINAL_NEIGHBORS);
        if (path.isEmpty()){
            System.out.println("Dude: no path found");
            return this.getPosition();
        }
        return path.get(0);
    }

}
