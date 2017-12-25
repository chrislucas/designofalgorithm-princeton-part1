package geom;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * Implementacao baseada em: http://www.geeksforgeeks.org/convex-hull-set-2-graham-scan/
 * Estudo sobre algoritmos de problemas de geometria computacional
 * */

public class Impl1GrahanScan {
    public static final double EPS = 1E-9;
    public static class Point2Df {
        private double x, y;
        public Point2Df() {}
        public Point2Df(double x, double y) {
            this.x = x;
            this.y = y;
        }
        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        /**
         * {-1, 0, 1} sentido horario, colinear, antihorario
         *
         * Ao inves de usar funcoes trigonometricas para ordenar o array
         * de pontos pelo angulo polar que os pontos Pi (0 < i < N) formam
         * em relacao ao ponto p0, usamos um truque baseado na orientacao
         * formada por 3 pontos
         * */
        public static int orientation(Point2Df a, Point2Df b, Point2Df c) {
            double mArea = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
            if(mArea < 0)
                return -1;  // sentido horario;
            else if(mArea > 0)
                return 1;   // anti horario
            return 0;       // colinear
        }

        public static double distance(Point2Df p, Point2Df q) {
            double diffY = p.y - q.y;
            double diffX = p.x - q.x;
            return diffX*diffX+diffY*diffY;
        }

        public static double distanceSquared(Point2Df p, Point2Df q) {
            return Math.sqrt(distance(p, q));
        }

        public Comparator<Point2Df> orderByDistAsc() {
            return new OrderByDistanceAsc();
        }

        public class OrderByDistanceAsc implements Comparator<Point2Df> {
            @Override
            public int compare(Point2Df p, Point2Df q) {
                int o = orientation(Point2Df.this, p, q);
                if(o == 0)
                    return distance(Point2Df.this, p) <= distance(Point2Df.this, q) ? -1 : 1;
                return o;
            }
        }

        @Override
        public String toString() {
            return String.format("Ponto2f (%.2f, %.2f)", x, y);
        }
    }

    public static void swap(Point2Df[] points, int i, int j) {
        Point2Df aux = points[i];
        points[i] = points[j];
        points[j] = aux;
    }

    public static Point2Df nextTop(Stack<Point2Df> stack) {
        Point2Df top = stack.pop();
        Point2Df point2Df =  stack.peek();        // nextTop
        stack.push(top);
        return point2Df;
    }

    public static Stack<Point2Df> scan(Point2Df[] points) {
       int minIdx = 0;
       int n = points.length;
       // pegar o ponto com o menor Y, se tivermos mais de 1, pegamos o com o menor X
       for(int i=1; i<n; i++) {
           if(points[i].getY() < points[minIdx].getY() ||
                   points[minIdx].getY() == points[i].getY()
                   && points[i].getX() < points[minIdx].getX()) {
               minIdx = i;
           }
       }
       if(minIdx != 0)
           swap(points, 0, minIdx);
        Arrays.sort(points, 1, n, points[0].orderByDistAsc());
        Stack<Point2Df> stack = new Stack<>();
        stack.push(points[0]);
        stack.push(points[1]);
        /**
         *
         * */
        int sizeNewArray = 1;
        for(int i=1; i<n; i++) {
            /**
             * Se os pontos i e i+1 forem colineares em relacao ao ponto p0
             * removemos o ponto i+1. Mas manteremos o ultimo ponto do array (o mais longe de p0)
             * */
            while(i<n-1 && Point2Df.orientation(points[0],  points[i],  points[i+1]) == 0)
                i++;
            points[sizeNewArray++] = points[i];
        }
        if(sizeNewArray < 3)
            return stack;
        stack.push(points[2]);
        for (int i=3; i<sizeNewArray; i++) {
            Point2Df current = points[i];
            Point2Df next = nextTop(stack);
            Point2Df top = stack.peek();
            while (Point2Df.orientation(top, next, current) < 1 && ! stack.empty()) {
                top =  stack.pop();// stack.peek();
                next = nextTop(stack);
            }
            stack.push(current);
        }
        return stack;
    }

    public static void test() {
        Point2Df[] point2Dfs = {
             new Point2Df(0,3)
            ,new Point2Df(1,1)
            ,new Point2Df(2,2)
            ,new Point2Df(4,4)
            ,new Point2Df(0,0)
            ,new Point2Df(1,2)
            ,new Point2Df(3,1)
            ,new Point2Df(3,3)
        };
        Stack<Point2Df> hull = scan(point2Dfs);
        while (!hull.empty()) {
            System.out.println(hull.pop());
        }
    }

    public static void main(String[] args) {
        test();
    }

}
