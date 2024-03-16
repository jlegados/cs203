import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Dude_Full extends Dude{
    public Dude_Full(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod,
                     int resourceCount, int resourceLimit) {
        super(id, position, images, animationPeriod, actionPeriod, resourceLimit,resourceCount);

    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> fullTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(House.class)));

        if (fullTarget.isPresent() && this.moveTo(world, fullTarget.get(), scheduler)) {
            this.transform(world, scheduler, imageStore);
        } else {
            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
        }
    }

    @Override
    public boolean moveToHelp(WorldModel world, Entity target, EventScheduler scheduler) {
        return true;
    }

    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        ActionEntity dude = createDudeNotFull(this.getId(), this.getPosition(),
                this.getActionPeriod(), this.getAnimationPeriod(), this.getResourceLimit(), this.getImages());

        world.removeEntity(scheduler,this);

        world.addEntity(dude);
        dude.scheduleActions(scheduler, world, imageStore);

        return true;
    }
}
