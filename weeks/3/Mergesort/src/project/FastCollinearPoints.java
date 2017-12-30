package project;

import edu.princeton.cs.algs4.ResizingArrayBag;
import java.util.Arrays;
import java.util.Iterator;

public class FastCollinearPoints implements Solver {
    final private Point[] points;
    final private LineSegment lineSegments [];;
    public FastCollinearPoints(Point[] points) {
        this.points = points;
        if(!verify(points))
            throw new IllegalArgumentException();
        lineSegments = run();
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
        int qPoints = points.length;
        ResizingArrayBag<LineSegment> bagOfLineSegment = new ResizingArrayBag<>();
        Arrays.sort(points);
        Point [] copyArrayPoints = new Point[qPoints];
        System.arraycopy(points, 0, copyArrayPoints, 0, qPoints);
        for(int i=0; i<qPoints; i++) {
            Point origin = copyArrayPoints[i];
            Arrays.sort(copyArrayPoints, i, qPoints, origin.slopeOrder());
            double [] slopes = new double[qPoints];
            for (int j=0; j<qPoints; j++) {
                slopes[j] = origin.slopeTo(copyArrayPoints[j]);
            }
            int acc = 0;
            for(int j=1;j<qPoints-2;j++) {
                if(acc < 2) {
                    if(slopes[j] == slopes[j+1] && slopes[j] == slopes[j+2]) {
                        acc = 3;
                    }
                    else
                        acc = 0;
                }
                else {
                    if(slopes[j] == slopes[j+1] && slopes[j] == slopes[j+2]) {
                        acc++;
                    }
                    else
                        acc = 0;
                }
                if(acc >= 3) {
                    bagOfLineSegment.add(new LineSegment(origin, copyArrayPoints[j+2]));
                }
            }
        }
        LineSegment [] segs1 = new LineSegment[bagOfLineSegment.size()];
        Iterator<LineSegment> keys1 = bagOfLineSegment.iterator();
        int m1 = 0;
        while (keys1.hasNext())
            segs1[m1++] = keys1.next();
        return segs1;
    }

    @Override
    public LineSegment [] segments() {
        return lineSegments;
    }
}
