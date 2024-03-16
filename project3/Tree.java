import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Tree extends Plant {

    public Tree(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int health) {
        super(id, position, images, animationPeriod,actionPeriod, health);
    }


    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

        if (!this.transform(world, scheduler, imageStore)) {

            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
        }
    }

    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.health <= 0) {
            Entity stump = createStump(STUMP_KEY + "_" + this.getId(), this.getPosition(), imageStore.getImageList(STUMP_KEY));

            world.removeEntity(scheduler, this);

            world.addEntity(stump);

            return true;
        }

        return false;
    }

}