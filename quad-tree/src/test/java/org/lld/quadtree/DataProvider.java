package org.lld.quadtree;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {
    static List<Point> getPoints() {
        List<Point> points = new ArrayList<>();
        Point p = new Point(210, 215);
        points.add(p);
        p = new Point(202, 219);
        points.add(p);
        p = new Point(227, 230);
        points.add(p);
        p = new Point(211, 235);
        points.add(p);
        p = new Point(238, 235);
        points.add(p);
        p = new Point(240, 245);
        points.add(p);
        p = new Point(180, 220);
        points.add(p);
        p = new Point(178, 198);
        points.add(p);
        p = new Point(201, 202);
        points.add(p);
        p = new Point(201, 190);
        points.add(p);
        p = new Point(210, 176);
        points.add(p);
        return points;
    }
}
