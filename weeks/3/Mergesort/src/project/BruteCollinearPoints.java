package project;

import edu.princeton.cs.algs4.ResizingArrayBag;
import java.util.Arrays;
import java.util.Iterator;

public class BruteCollinearPoints implements Solver {
    final private Point [] points;
    final private LineSegment [] lineSegments;
    public BruteCollinearPoints(Point[] points) {
        if(!verify(points) )
            throw new IllegalArgumentException();
        this.points = points;
        this.lineSegments = run();
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
        return lineSegments.length;
    }

    public LineSegment [] run() {
        ResizingArrayBag<LineSegment> resizingArrayBag = new ResizingArrayBag<>();
        int m = 0;
        Arrays.sort(points);
        int limit = points.length;
        for(int i=0; i<limit-3; i++) {
            for (int j = i+1; j <limit-2; j++) {
                for (int k = j+1; k<limit-1; k++) {
                    for (int l = k+1; l<points.length; l++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];
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
        while (it.hasNext())
            segs[m++] = it.next();
        return segs;
    }


    @Override
    public LineSegment [] segments() {
        return lineSegments;
    }
}
