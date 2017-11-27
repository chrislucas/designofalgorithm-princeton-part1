package exercises;

public class Point2D {

    private final double x, y;
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Rotacao em sentido anti-horario
     * */
    public int ccw(Point2D a, Point2D b, Point2D c) {
        double area = (b.x-a.x) * (c.y-a.y) * (c.x-a.x);
        if(area < 0)
            return -1;  // clockwise;
        else if(area > 0)
            return 1; // counter-clockwise
        else
            return 0; // collinear
    }
}
