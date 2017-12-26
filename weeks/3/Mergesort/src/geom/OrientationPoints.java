package geom;


public class OrientationPoints {

    /**
     * @return {-1,0,1} {clockwise, colinear, clockwise}
     * */
    public static int orientation(Point2f a, Point2f b, Point2f c) {
        double area = (b.getY() - a.getY()) * (c.getX() - b.getX())
                - (b.getX() - a.getX()) * (c.getY() - b.getY());
        if(area > 0)
            return -1;
        else if(area < 0)
            return 1;
        return 0;
    }

    /**
     * @return {-1,0,1} {clockwise, colinear, counterclockwise}
     * */
    public static int orientation2(Point2f a, Point2f b, Point2f c) {
        double area = (b.getX() - a.getX()) * (c.getY() - a.getY())
                - (b.getY() - a.getY()) * (c.getX() - a.getX());
        if(area < 0)
            return -1;
        else if(area > 0)
            return 1;
        return 0;
    }

    public static void main(String[] args) {
        Point2f points [][] = {
             {new Point2f(0,0), new Point2f(4,4), new Point2f(1, 2)}
            ,{new Point2f(0,0), new Point2f(4,4), new Point2f(1, 1)}
            ,{new Point2f(0,0), new Point2f(4,4), new Point2f(0, 3)}
            ,{new Point2f(0,0), new Point2f(4,4), new Point2f(5, 3)}
            ,{new Point2f(0,3), new Point2f(1,2), new Point2f(4, 4)}
        };
        int idx = 4;
        int o = orientation(points[idx][0], points[idx][1], points[idx][2]);
        if(o == 0)
            System.out.println("Colinear");
        else if(o < 0)
            System.out.println("Sentido horario");
        else
            System.out.println("Sentido anti-horario");

        o = orientation2(points[idx][0], points[idx][1], points[idx][2]);
        if(o == 0)
            System.out.println("Colinear");
        else if(o < 0)
            System.out.println("Sentido horario");
        else
            System.out.println("Sentido anti-horario");
    }
}
