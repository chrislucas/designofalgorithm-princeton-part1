package project;

import edu.princeton.cs.algs4.ResizingArrayBag;

import javax.sound.sampled.Line;
import java.util.Arrays;
import java.util.Iterator;

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
        LineSegment [] s = segments();
        return s.length;
    }

    @Override
    public LineSegment [] segments() {
        int qPoints = points.length;
        ResizingArrayBag<LineSegment> bagOfLineSegment = new ResizingArrayBag<>();
        /*
        int minIdx = 0;
        for(int i=1; i<qPoints; i++) {
            if(points[i].compareTo(points[minIdx]) < 0) {
                minIdx = i;
            }
        }
        // swap, menor elemento ve para frente do vetor
        if(minIdx != 0) {
            Point p = points[minIdx];
            points[minIdx] = points[0];
            points[0] = p;
        }
        // ordena o resto do vetor usando o coeficiente angular que os n-1 pontos formam com p0
        Arrays.sort(points, 1, qPoints, points[0].slopeOrder());
        */
        Arrays.sort(points);
        Point [] copyArrayPoints = new Point[qPoints];
        System.arraycopy(points, 0, copyArrayPoints, 0, qPoints);
        for(int i=0; i<qPoints; i++) {
            Point origin = copyArrayPoints[i];
            Arrays.sort(copyArrayPoints, origin.slopeOrder());
            double [] slopes = new double[qPoints];
            for (int j=0; j<qPoints; j++) {
                slopes[j] = origin.slopeTo(copyArrayPoints[j]);
            }
            int acc = 0;
            double previousSlope = 0.0;
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
                if(acc >= 3)
                    bagOfLineSegment.add(new LineSegment(origin, copyArrayPoints[j+2]));
            }
        }

/*
        for (int i=1; i<qPoints-2; i++) {
            for(int j=i+1; j<qPoints-1; j++) {
                double slopePointPAndQ1 = points[0].slopeTo(points[i]);
                double slopePointPAndQ2 = points[0].slopeTo(points[j]);
                double slopePointPAndQ3 = points[0].slopeTo(points[j+1]);
                if(slopePointPAndQ1 == slopePointPAndQ2 && slopePointPAndQ1 == slopePointPAndQ3) {
                    bagOfLineSegment.add(new LineSegment(points[0], points[j+1]));
                }
            }
        }
*/
        LineSegment [] segs = new LineSegment[bagOfLineSegment.size()];
        Iterator<LineSegment> it = bagOfLineSegment.iterator();
        int m = 0;
        while (it.hasNext())
            segs[m++] = it.next();
        return segs;
    }
}
