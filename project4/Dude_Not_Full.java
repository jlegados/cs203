import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Dude_Not_Full extends Dude {
    public Dude_Not_Full(String id, Point position, List<PImage> images, int resourceLimit,
                         int resourceCount, double actionPeriod, double animationPeriod) {
        super(id, position, images, animationPeriod, actionPeriod, resourceLimit, resourceCount);


    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> target = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));

        if (target.isEmpty() || !this.moveTo(world, target.get(), scheduler) || !this.transform(world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
        }
    }
    @Override
    public boolean moveToHelp(WorldModel world, Entity target, EventScheduler scheduler){
        setResourceLimit(this.getResourceCount() + 1);
        if(target instanceof Plant){
            ((Plant) target).setHealth(((Plant) target).getHealth()-1);
        }return true;
    }

    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.getResourceCount() >= this.getResourceLimit()) {
            ActionEntity dude = createDudeFull(this.getId(), this.getPosition(), this.getActionPeriod(), this.getAnimationPeriod(), 0,
                    this.getResourceLimit(), this.getImages());

            world.removeEntity(scheduler,this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(dude);
            dude.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }
}

