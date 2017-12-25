package geom;

import java.util.Comparator;

public class Point2f {
    public static final double EPS = 1E-9;
    private double x, y;
    public Point2f(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double diffY(Point2f pb) { return pb.y - this.y; }
    public double diffX(Point2f pb) {
        return pb.x - this.x;
    }
    public boolean almostEquals(double a, double b) {
        return Math.abs(a-b) < EPS;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("Point2f (%.3f, %.3f)", x, y);
    }

    /**
     *
     * */
    public static int ccw(Point2f a, Point2f b, Point2f c) {
        double mArea = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if(mArea < 0)
            return -1; // sentido horario;
        else if(mArea > 0)
            return 1;   // anti horario
        return 0;       // colinear
    }

    public static double distance(Point2f p, Point2f q) {
        double diffY = p.getY() - q.getY();
        double diffX = p.getX() - q.getX();
        return diffX*diffX+diffY*diffY;
    }

    public static double distanceSquared(Point2f p, Point2f q) {
        return Math.sqrt(distance(p, q));
    }

    public class ComparePolarAngle implements Comparator<Point2f> {
        @Override
        public int compare(Point2f pa, Point2f pb) {
            double dy1 = pa.diffY(Point2f.this);
            double dy2 = pb.diffY(Point2f.this);
            double dx1 = pa.diffX(Point2f.this);
            double dx2 = pb.diffX(Point2f.this);
            // Pontos pc pa e pb estao estao na mesma altura em Y,
            if(dy1 == 0 && dy2 == 0) {
                if(dx1 >= 0 && dx2 < 0)
                    return -1;
                else if(dx2 >= 0 && dx1 < 0)
                    return 1;
                return 0;
            }
            // Ponto pa esta acima de pc em Y e pc esta acima de pb em Y
            else if(dy1 >= 0 && dy2 < 0)
                return -1;
                // pb esta acima ou na mesma altura q pc em Y e pa esta abaixo de pc
            else if(dy2 >= 0 && dy1 < 0)
                return 1;
            return -ccw(Point2f.this, pa, pb);
        }
    }

    public class OrderByDistanceAsc implements Comparator<Point2f> {
        @Override
        public int compare(Point2f p, Point2f q) {
            int o = ccw(Point2f.this, p, q);
            if(o == 0)
                return distance(Point2f.this, q) >= distance(Point2f.this, p) ? -1 : 1;
            return o;
        }
    }

    public class DescY implements Comparator<Point2f> {
        @Override
        public int compare(Point2f pa, Point2f pb) {
            return (int)(pa.y - pb.y);
        }
    }

    public static class DescYX implements Comparator<Point2f> {
        @Override
        public int compare(Point2f pa, Point2f pb) {
            if(pa.y - pb.y < EPS)
                return (int) (pa.x - pb.x);
            return (int) (pa.y - pb.y);
        }
    }

    public Comparator<Point2f> orderByPolarAngleAsc() {
        return new ComparePolarAngle();
    }

    public Comparator<Point2f> orderByDistAsc() {
        return new OrderByDistanceAsc();
    }

}
