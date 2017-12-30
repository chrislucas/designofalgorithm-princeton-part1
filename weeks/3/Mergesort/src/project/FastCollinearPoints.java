package project;

import edu.princeton.cs.algs4.ResizingArrayBag;
import java.util.Arrays;
import java.util.Iterator;

public class FastCollinearPoints implements Solver {
    private final Point[] arraysOfpoints;
    private final LineSegment lineSegments [];;
    public FastCollinearPoints(final Point[] points) {
        this.arraysOfpoints = points;
        if (!verify(points))
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
        int qPoints = arraysOfpoints.length;
        ResizingArrayBag<Point[]> bagOfLineSegment = new ResizingArrayBag<>();
        Arrays.sort(arraysOfpoints);
        Point [] copyArrayPoints = new Point[qPoints];
        System.arraycopy(arraysOfpoints, 0, copyArrayPoints, 0, qPoints);
        for(int i = 0; i < qPoints; i++) {
            Point origin = copyArrayPoints[i];
            double [] slopes = new double[qPoints];
            Arrays.sort(copyArrayPoints, i, qPoints, origin.slopeOrder());
            for (int j = 0; j < qPoints; j++) {
                slopes[j] = origin.slopeTo(copyArrayPoints[j]);
            }
            int acc = 0;
            for(int j = 1; j < qPoints-2; j++) {
                if(slopes[j] == slopes[j+1] && slopes[j] == slopes[j+2]) {
                    acc += acc == 0 ? 3 : 1;
                }
                else {
                    acc = 0;
                }
                if(acc >= 3) {
                    Point p = copyArrayPoints[j];
                    Point q = copyArrayPoints[j+1];
                    Point r = copyArrayPoints[j+2];
                    if(p.compareTo(q) < 0) {
                        Point aux = p;
                        p = q;
                        q = aux;
                    }
                    if(q.compareTo(r) < 0) {
                        Point aux = q;
                        q = r;
                        r = aux;
                    }
                    if(p.compareTo(q) < 0) {
                        Point aux = p;
                        p = q;
                        q = aux;
                    }
                    boolean exists = false;
                    int c = 0;
                    for(Point[]  pair : bagOfLineSegment) {
                        c++;    // so por curiosidade
                        if (pair[0].compareTo(origin) == 0 && pair[1].compareTo(p)==0) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        bagOfLineSegment.add(new Point[]{origin.compareTo(r) < 0 ? origin : r, p});
                    }
                }
            }
        }
        LineSegment [] segs1 = new LineSegment[bagOfLineSegment.size()];
        Iterator<Point[]> keys1 = bagOfLineSegment.iterator();
        int m1 = 0;
        while (keys1.hasNext()) {
            Point [] pair = keys1.next();
            segs1[m1++] = new LineSegment(pair[0], pair[1]);
        }

        return segs1;
    }

    @Override
    public LineSegment [] segments() {
        return lineSegments;
    }
}
