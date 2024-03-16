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
        int horiz = Integer.signum(destPos.x - this.getPosition().x);
        Point newPos = new Point(this.getPosition().x + horiz, this.getPosition().y);

        if (horiz == 0 || world.isOccupied(newPos) && world.getOccupancyCell(newPos).getClass() != Stump.class) {
            int vert = Integer.signum(destPos.y - this.getPosition().y);
            newPos = new Point(this.getPosition().x, this.getPosition().y + vert);

            if (vert == 0 || world.isOccupied(newPos) && world.getOccupancyCell(newPos).getClass() != Stump.class) {
                newPos = this.getPosition();
            }
        }

        return newPos;
    }

}
