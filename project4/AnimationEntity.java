import processing.core.PImage;

import java.util.List;

public abstract class AnimationEntity extends Entity {
    private final double animationPeriod;
    public AnimationEntity(String id, Point position, List<PImage> images, double animationPeriod) {
        super(id, position, images);
        this.animationPeriod = animationPeriod;
    }
    public double getAnimationPeriod() {
        return this.animationPeriod;
    }

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, new Animation(this, 0), getAnimationPeriod());

    }

    public void nextImage() {
        setImageIndex(getImageIndex() + 1);
    }



}
