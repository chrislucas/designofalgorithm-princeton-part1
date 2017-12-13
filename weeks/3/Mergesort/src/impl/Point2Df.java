package impl;

import edu.princeton.cs.algs4.Point2D;

import java.util.Comparator;

public class Point2Df {
    private double x, y;
    public static final Comparator<Point2Df> polarOrder = new PolarOrder();
    public Point2Df(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     *
     * */
    public static int ccw(Point2Df a, Point2Df b, Point2Df c) {
        return 0;
    }

    private static class PolarOrder implements Comparator<Point2Df> {
        public static final double EPS = 1E-9;

        @Override
        public int compare(Point2Df pa, Point2Df pb) {
            return 0;
        }

        public boolean almostEquals(double a, double b) {
            return Math.abs(a-b) < EPS;
        }
    }
}
