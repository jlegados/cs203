public interface Movement {
    default boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler){
        if (Point.adjacent(this.getPosition(), target.getPosition())) {
            return moveToHelp(world, target, scheduler);
        } else {
            Point nextPos = nextPosition(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                world.moveEntity(scheduler, (Entity)this, nextPos );
            }
            return false;
        }
    }
    Point nextPosition(WorldModel world, Point desPos);
    boolean moveToHelp(WorldModel world, Entity target, EventScheduler scheduler);
    Point getPosition();
}

