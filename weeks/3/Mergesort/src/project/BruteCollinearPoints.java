package project;

import edu.princeton.cs.algs4.ResizingArrayBag;
import java.util.Arrays;
import java.util.Iterator;

public class BruteCollinearPoints implements Solver {
    private final Point [] arrayOfPoints;
    private final LineSegment [] lineSegments;
    public BruteCollinearPoints(final Point[] points) {
        if (!verify(points))
            throw new IllegalArgumentException();
        this.arrayOfPoints = points;
        this.lineSegments = run();
    }

    private boolean verify(Point [] points) {
        if (points == null)
            return false;
        for (Point p : points) {
            if (p == null)
                return false;
        }
        for (int i = 0; i < points.length-1; i++) {
            for (int j = i+1; j < points.length; j++) {
              if(points[i].compareTo(points[j]) == 0)
                  return false;
            }
        }
        return true;
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    private LineSegment [] run() {
        ResizingArrayBag<LineSegment> resizingArrayBag = new ResizingArrayBag<>();
        Arrays.sort(arrayOfPoints);
        int limit = arrayOfPoints.length;
        for(int i = 0; i < limit-3; i++) {
            for (int j = i+1; j < limit-2; j++) {
                for (int k = j+1; k < limit-1; k++) {
                    for (int m = k+1; m < arrayOfPoints.length; m++) {
                        Point p = arrayOfPoints[i];
                        Point q = arrayOfPoints[j];
                        Point r = arrayOfPoints[k];
                        Point s = arrayOfPoints[m];
                        double slopeToPQ = p.slopeTo(q);
                        double slopeToPR = p.slopeTo(r);
                        double slopeToPS = p.slopeTo(s);
                        if(slopeToPQ == slopeToPR && slopeToPQ == slopeToPS) {
                            resizingArrayBag.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }
        LineSegment [] segs = new LineSegment[resizingArrayBag.size()];
        Iterator<LineSegment> it = resizingArrayBag.iterator();
        int m = 0;
        while (it.hasNext())
            segs[m++] = it.next();
        return segs;
    }


    @Override
    public LineSegment [] segments() {
        return lineSegments;
    }
}
