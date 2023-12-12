package org.lld.quadtree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoundaryTest {

    @Test
    @DisplayName("Test if point is inside the boundary")
    void testIfPointIsInsideBoundary() {
        Point p = new Point(150, 150);
        Boundary boundary = new Boundary(200, 200, 100, 100);
        Assertions.assertTrue(boundary.contains(p));
    }

    @Test
    @DisplayName("Test edge condition of a point being at the boundary")
    void testIfPointIsAtTheBoundary() {
        Point p = new Point(100, 100);
        Boundary boundary = new Boundary(200, 200, 100, 100);
        Assertions.assertTrue(boundary.contains(p));
    }

    @Test
    @DisplayName("Check intersection")
    void shouldCheckIntersection() {
        Boundary b1 = new Boundary(200, 200, 100, 100);
        Boundary b2 = new Boundary(200, 200, 100, 100);
        Assertions.assertTrue(b1.intersects(b2));

        b1 = new Boundary(200, 200, 100, 100);
        b2 = new Boundary(100, 100, 100, 100);
        Assertions.assertTrue(b1.intersects(b2));
    }

    @Test
    @DisplayName("Check intersection with no common points")
    void shouldReturnCheckIntersection() {
        Boundary b1 = new Boundary(200, 200, 100, 100);
        Boundary b2 = new Boundary(10, 10, 50, 50);
        Assertions.assertFalse(b1.intersects(b2));

    }
}
