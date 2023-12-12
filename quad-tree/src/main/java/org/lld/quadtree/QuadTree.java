package org.lld.quadtree;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {
    private final Boundary boundary;
    private final int capacity;
    private QuadTree northEast;
    private QuadTree northWest;
    private QuadTree southEast;
    private QuadTree southWest;
    private final List<Point> points;
    private boolean isDivided;

    public QuadTree(Boundary boundary, int capacity) {
        this.boundary = boundary;
        this.capacity = capacity;
        points = new ArrayList<>();
    }

    public int getSize() {
        return this.points.size();
    }

    public boolean insert(Point pPoint) {
        if (!this.boundary.contains(pPoint))
            return false;
        if (points.size() < capacity) {
            this.points.add(pPoint);
            return true;
        } else {
            if (!isDivided) {
                this.subdivide();
            }
            return this.northEast.insert(pPoint) || this.northWest.insert(pPoint)
                    || this.southEast.insert(pPoint) || this.southWest.insert(pPoint);
        }
    }

    private void subdivide() {
        var neBoundary = new Boundary(this.boundary.getX() + this.boundary.getW() / 2,
                this.boundary.getY() + this.boundary.getH() / 2, this.boundary.getH() / 2, this.boundary.getW() / 2);

        var nwBoundary = new Boundary(this.boundary.getX() - this.boundary.getW() / 2,
                this.boundary.getY() + this.boundary.getH() / 2, this.boundary.getH() / 2, this.boundary.getW() / 2);

        var seBoundary = new Boundary(this.boundary.getX() + this.boundary.getW() / 2,
                this.boundary.getY() - this.boundary.getH() / 2, this.boundary.getH() / 2, this.boundary.getW() / 2);

        var swBoundary = new Boundary(this.boundary.getX() - this.boundary.getW() / 2,
                this.boundary.getY() - this.boundary.getH() / 2, this.boundary.getH() / 2, this.boundary.getW() / 2);

        this.northEast = new QuadTree(neBoundary, capacity);
        this.northWest = new QuadTree(nwBoundary, capacity);
        this.southEast = new QuadTree(seBoundary, capacity);
        this.southWest = new QuadTree(swBoundary, capacity);
        this.isDivided = true;
    }

    public boolean isDivided() {
        return isDivided;
    }

    public List<Point> query(Boundary range) {
        List<Point> result = new ArrayList<>();
        if(!this.boundary.intersects(range))
            return result;
        query(range, result);
        return result;
    }

    private void query(Boundary range, List<Point> result) {
        for (Point p : this.points) {
            if (range.contains(p))
                result.add(p);
        }
        if (isDivided){
            this.northEast.query(range, result);
            this.northWest.query(range, result);
            this.southEast.query(range, result);
            this.southWest.query(range, result);
        }
    }
}
