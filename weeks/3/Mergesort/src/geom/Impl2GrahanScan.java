package geom;

import project.Point;

import java.util.Arrays;
import java.util.Stack;

/**
 * Implementacao baseado no algoritmo em: https://algs4.cs.princeton.edu/99hull/GrahamScan.java.html
 * */

public class Impl2GrahanScan {
    public static Stack<Point2f> grahanScan(Point2f [] points) {
        int minIdx = 0;
        for(int i=1; i<points.length; i++) {
            if(points[i].getY() < points[minIdx].getY()
                    || points[i].getY() == points[minIdx].getY() && points[i].getX() < points[minIdx].getX())
                minIdx = i;
        }
        if(minIdx != 0)
            swap(points, 0, minIdx);
        int n = points.length;
        Arrays.sort(points, 1, n, points[0].orderByPolarAngleAsc());
        Stack<Point2f> stack = new Stack<>();
        stack.push(points[0]);
        int idx = 1;
        while(idx < n) {
            if(points[0].getX() != points[idx].getX() && points[0].getY() != points[idx].getY())
                break;
            idx++;
        }
        if(idx == n-1)
            return stack;
        int idx2 = idx+1;
        while (idx2 < n) {
            if(Point2f.ccw(points[0], points[idx], points[idx2]) != 0)
                break;
            idx2++;
        }
        stack.push(points[idx2-1]);
        for (int i = idx2; i<n ; i++) {
            Point2f top = stack.pop();
            while (Point2f.ccw(stack.peek(), top, points[i]) <= 0)
                top = stack.pop();
            stack.push(top);
            stack.push(points[i]);
        }
        return stack;
    }

    public static void test() {
        Point2f[] points = {
             new Point2f(0,3)
            ,new Point2f(1,1)
            ,new Point2f(2,2)
            ,new Point2f(4,4)
            ,new Point2f(0,0)
            ,new Point2f(1,2)
            ,new Point2f(3,1)
            ,new Point2f(3,3)
        };
        Stack<Point2f> stack = grahanScan(points);
        while (! stack.empty() ) {
            System.out.println(stack.pop());
        }
    }

    public static void swap(Point2f [] points, int i, int j) {
        Point2f aux = points[i];
        points[i] = points[j];
        points[j] = aux;
    }

    public static void main(String[] args) {
        test();
    }
}
