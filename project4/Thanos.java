import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Thanos extends ActionEntity implements Movement {

    public Thanos(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> ThanosTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(Tree.class)));

        if (ThanosTarget.isPresent()) {
            Point tgtPos = ThanosTarget.get().getPosition();
            if (this.moveTo(world, ThanosTarget.get(), scheduler)) {
                world.setBackgroundCell(tgtPos, new Background("thanos_spawn", imageStore.getImageList("thanos_spawn")));
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
                point -> world.withinBounds(point) && (!world.isOccupied(point) || world.getOccupancyCell(point).getClass() == Stump.class),
                (p1, p2) -> Point.adjacent(p1, p2),
                PathingStrategy.CARDINAL_NEIGHBORS);
        if (path.isEmpty()) {
            return this.getPosition();
        }
        return path.get(0);
    }
}