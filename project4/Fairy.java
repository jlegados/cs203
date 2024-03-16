import processing.core.PImage;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Fairy extends ActionEntity implements Movement {

    public Fairy(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> fairyTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(Stump.class)));

        if (fairyTarget.isPresent()) {
            Point tgtPos = fairyTarget.get().getPosition();

            if (this.moveTo(world, fairyTarget.get(), scheduler)) {

                ActionEntity sapling = createSapling(SAPLING_KEY + "_" + fairyTarget.get().getId(), tgtPos, imageStore.getImageList(SAPLING_KEY), 0);


                world.addEntity(sapling);
                sapling.scheduleActions(scheduler, world, imageStore);
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
                point -> world.withinBounds(point) && (!world.isOccupied(point) || world.getOccupancyCell(point).getClass() == House.class),
                (p1, p2) -> Point.adjacent(p1, p2),
                PathingStrategy.CARDINAL_NEIGHBORS);
        if (path.isEmpty()) {
            return this.getPosition();
        }
        return path.get(0);
    }
}