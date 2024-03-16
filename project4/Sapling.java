import processing.core.PImage;
import java.util.List;


public class Sapling extends Plant{
    public int healthLimit;

    public Sapling(String id, Point position, List<PImage> images, double actionPeriod,
                   double animationPeriod,
                   int health, int healthLimit) {
        super(id, position, images, animationPeriod, actionPeriod, health);
        this.healthLimit = healthLimit;
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        this.setHealth(this.getHealth() + 1);
        if (!this.transform(world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
        }
    }

    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.getHealth() <= 0) {
            Entity stump = createStump(STUMP_KEY + "_" + this.getId(), this.getPosition(), imageStore.getImageList(STUMP_KEY));

            world.removeEntity(scheduler, this);

            world.addEntity(stump);

            return true;
        } else if (this.getHealth() >= this.healthLimit) {
            ActionEntity tree = createTree(TREE_KEY + "_" + this.getId(), this.getPosition(), NumberUtil.getNumFromRange(TREE_ACTION_MAX, TREE_ACTION_MIN), NumberUtil.getNumFromRange(TREE_ANIMATION_MAX, TREE_ANIMATION_MIN), NumberUtil.getIntFromRange(TREE_HEALTH_MAX, TREE_HEALTH_MIN), imageStore.getImageList(TREE_KEY));

            world.removeEntity(scheduler,this);

            world.addEntity(tree);
            tree.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }

}
