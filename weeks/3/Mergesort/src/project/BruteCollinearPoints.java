package project;

import com.sun.org.apache.regexp.internal.RE;
import edu.princeton.cs.algs4.ResizingArrayBag;

import java.util.Arrays;
import java.util.Iterator;

public class BruteCollinearPoints implements Solver {
    private Point [] points;
    public BruteCollinearPoints(Point[] points) {
        if( ! verify(points) )
            throw new IllegalArgumentException();
        this.points = points;
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
        LineSegment [] segments = segments();
        int acc = 0;
        for(int i=0; i<segments.length; i++) {
            if(segments[i] != null)
                acc++;
            else
                break;
        }
        return acc;
    }


    @Override
    public LineSegment [] segments() {
        ResizingArrayBag<LineSegment> resizingArrayBag = new ResizingArrayBag<>();
        ResizingArrayBag<Point> resizingArrayPoints = new ResizingArrayBag<>();
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
                            boolean exists = false;
                            /*
                            if(resizingArrayPoints.size() > 0) {
                                Iterator<Point> it = resizingArrayPoints.iterator();
                                while (it.hasNext()) {
                                    Point pointAdded = it.next();
                                    if(pointAdded.compareTo(p) == 0 || pointAdded.compareTo(s) == 0) {
                                        exists = true;
                                    }
                                }
                            }
                            if(!exists) {
                                resizingArrayPoints.add(p);
                            }
                               */
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
}
