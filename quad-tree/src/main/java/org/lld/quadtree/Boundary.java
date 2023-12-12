package org.lld.quadtree;

public class Boundary {
    private final double x;
    private final double y;
    private final double h;
    private final double w;

    public Boundary(double x, double y, double h, double w) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getH() {
        return h;
    }

    public double getW() {
        return w;
    }

    @Override
    public String toString() {
        return "Boundary{" +
                "x=" + x +
                ", y=" + y +
                ", h=" + h +
                ", w=" + w +
                '}';
    }

    public boolean contains(Point pPoint) {
        return pPoint.getX() >= this.x - this.w
        && pPoint.getX() <= this.x + this.w
        && pPoint.getY() <= this.y + this.h
        && pPoint.getY() >= this.y - this.h;
    }

    /**
     * Check if one rectangle intersects another.
     * Basically two rectangle do not intersect if
     * one of them is above the other or if one is
     * on left of other.
     * @param range
     * @return
     */
    public boolean intersects(Boundary range) {
        if(this.getTopRightY() <  range.getBottomLeftY()
                || this.getBottomLeftY() > range.getTopRightY())
        return false;

        if(this.getTopRightX() <  range.getBottomLeftX()
                || this.getBottomLeftX() > range.getTopRightX())
            return false;
        return true;
    }

    public double getTopRightY() {
        return this.y + this.h/2;
    }
    public double getTopRightX() {
        return this.x + this.w/2;
    }

    public double getBottomLeftY() {
        return this.y - this.h/2;
    }
    public double getBottomLeftX() {
        return this.x - this.w/2;
    }
}
