package org.lld.quadtree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuadTreeTest {
    @Test
    @DisplayName("should insert four points in the quadtree")
    void shouldInsertPointsInTree() {
        double x = 100, y = 100;
        Boundary boundary = new Boundary(200, 200, 100, 100);
        QuadTree quadTree = new QuadTree(boundary, 4);
        Random random = new Random();
        for(int i=0; i<4; i++) {
            Point p = new Point(Math.random() * 200 + x, Math.random() * 200 + y);
            quadTree.insert(p);
        }
        Assertions.assertEquals(4, quadTree.getSize() );
    }

    @Test
    @DisplayName("should insert no points in the quadtree if not in the boundary")
    void shouldNotInsertPointsInTree() {
        double x = 100, y = 100;
        Boundary boundary = new Boundary(200, 200, 100, 100);
        QuadTree quadTree = new QuadTree(boundary, 4);
        Random random = new Random();
        for(int i=0; i<4; i++) {
            Point p = new Point(Math.random() * x, Math.random() * y);
            quadTree.insert(p);
        }
        Assertions.assertEquals(0, quadTree.getSize() );
    }

    @Test
    @DisplayName("should get points in range")
    void shouldGetPointsInRange() {
        Boundary boundary = new Boundary(200, 200, 50, 50);
        QuadTree quadTree = new QuadTree(boundary, 4);
        for(Point point : DataProvider.getPoints()) {
            quadTree.insert(point);
        }
        Boundary range = new Boundary(200, 200, 25,25);
        List<Point> points = quadTree.query(range);
        Assertions.assertEquals(4, quadTree.getSize() );
        Assertions.assertTrue(quadTree.isDivided() );
        Assertions.assertEquals(7, points.size() );

        range = new Boundary(230, 230, 25,25);
        points = quadTree.query(range);
        List<Point> expectedPoints = new ArrayList<>();
        Point p = new Point(210, 215);
        expectedPoints.add(p);
        p = new Point(227, 230);
        expectedPoints.add(p);
        p = new Point(211, 235);
        expectedPoints.add(p);
        p = new Point(238, 235);
        expectedPoints.add(p);
        p = new Point(240, 245);
        expectedPoints.add(p);
        Assertions.assertEquals(5, points.size() );
        Assertions.assertIterableEquals(expectedPoints, points);
    }

}
