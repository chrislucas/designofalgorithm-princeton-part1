package xplore.com.br.impl;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Implementacao baseada no curso de algoritmos de princeton
 *
 * */
public class GrahanScan {

    static class Point2Df {
        double x, y, angle;
        public Point2Df(double x, double y, double angle) {
            this.x = x;
            this.y = y;
            this.angle = angle;
        }
    }

    private static Comparator<Point2Df> orderByY = (a, b) -> {
        if(a.y < b.y)
            return 1;
        else if(a.y > b.y)
            return -1;
        return 0;
    };

    private static Comparator<Point2Df> orderByPolarAngle = (a, b) -> {
        if(a.y < b.y)
            return 1;
        else if(a.y > b.y)
            return -1;
        return 0;
    };

    static Point2Df [] points;

    static Point2Df findPointWithSmallestY() {
        double y = Double.MAX_VALUE;
        Point2Df target = points[0];
        for (Point2Df p : points) {
            if(p.y < target.y)
                target = p;
        }
        return target;
    }

    public static void initialize(int qPoints) {
        points = new Point2Df[qPoints];
    }


    public double area(Point2Df a, Point2Df b, Point2Df c) {
        double xa = a.x, xb = b.x, xc = c.x;
        double ya = a.y, yb = b.y, yc = c.y;
        double area = (xb - xa)*(yc - ya)-(yb - ya)*(xc-xa);
        /**
         * se area > 0 os pontos estao no sentido antihorario
         * se area < 0 sentido horario
         * se area == 0 sao pontos colineares
         * */
        return area;
    }

    public static void main(String[] args) {

    }
}
