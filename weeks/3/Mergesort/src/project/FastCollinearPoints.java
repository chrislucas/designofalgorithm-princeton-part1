package project;

import javax.sound.sampled.Line;
import java.util.Arrays;

public class FastCollinearPoints implements Solver {
    private Point[] points;

    public FastCollinearPoints(Point[] points) {
        this.points = points;
        if(!verify(points))
            throw new IllegalArgumentException();
    }

    private boolean verify(Point [] points) {
        if(points == null)
            return false;
        for(Point p : points) {
            if (p == null)
                return false;
        }
        for (int i=0; i<points.length-1; i++) {
            for (int j=i+1; j<points.length ; j++) {
                if(points[i].compareTo(points[j]) == 0)
                    return false;
            }
        }
        return true;
    }

    public int numberOfSegments() {
        int acc = 0;
        for(LineSegment segment : segments()) {
            if(segment != null)
                acc++;
        }
        return acc;
    }

    @Override
    public LineSegment [] segments() {
        Arrays.sort(points, points[0].slopeOrder());
        LineSegment [] segs = new LineSegment[points.length];
        for (int i=0; i<points.length ; i++) {

        }
        return segs;
    }
}
