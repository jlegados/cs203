import processing.core.PImage;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class IronMan extends ActionEntity implements Movement {
    int countball;
    public IronMan(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);
        this.countball = 0;
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> IronManTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(IronManBall.class)));

        if (IronManTarget.isPresent()) {
            Point tgtPos = IronManTarget.get().getPosition();
            if (this.moveTo(world, IronManTarget.get(), scheduler)) {
                this.countball++;

                Point pos = new Point(NumberUtil.getIntFromRange(world.getNumCols(), 0), NumberUtil.getIntFromRange(world.getNumRows(), 0));
                while(world.isOccupied(pos)){
                    pos = new Point(NumberUtil.getIntFromRange(world.getNumCols(), 0), NumberUtil.getIntFromRange(world.getNumRows(), 0));
                }
                Entity ironmanball = new IronManBall(Entity.IRONMANBALL_KEY, pos, imageStore.getImageList(Entity.IRONMANBALL_KEY));
                world.addEntity(ironmanball);
                if (countball > 4){
                    int choose = NumberUtil.getIntFromRange(2, 0);
                    countball = 0;
                    if (choose == 0){
                        Entity house = createHouse(HOUSE_KEY + "_" + IronManTarget.get().getId(), tgtPos, imageStore.getImageList(HOUSE_KEY));

                        world.addEntity(house);
                    } else {
                        ActionEntity sapling = createSapling(SAPLING_KEY + "_" + IronManTarget.get().getId(), tgtPos, imageStore.getImageList(SAPLING_KEY), 0);

                        world.addEntity(sapling);
                        sapling.scheduleActions(scheduler, world, imageStore);
                    }

                }
            }
        }

        scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
    }

    @Override
    public boolean moveToHelp(WorldModel world, Entity target, EventScheduler scheduler) {
        world.removeEntity(scheduler, target);
        return true;
    }

    @Override
    public Point nextPosition(WorldModel world, Point destPos) {
        PathingStrategy strat = new AStarPathingStrategy();
        List<Point> path = strat.computePath(
                this.getPosition(),
                destPos,
                point -> world.withinBounds(point) && (!world.isOccupied(point)),
                (p1, p2) -> Point.adjacent(p1, p2),
                PathingStrategy.CARDINAL_NEIGHBORS);
        if (path.isEmpty()) {
            return this.getPosition();
        }
        return path.get(0);
    }
}

