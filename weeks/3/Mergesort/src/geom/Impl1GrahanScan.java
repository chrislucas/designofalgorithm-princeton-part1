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
    public static class Point2f {
        private double x, y;
        public Point2f() {}
        public Point2f(double x, double y) {
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
        public static int orientation(Point2f a, Point2f b, Point2f c) {
            double mArea = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
            if(mArea < 0)
                return -1;  // sentido horario;
            else if(mArea > 0)
                return 1;   // anti horario
            return 0;       // colinear
        }

        public static double distance(Point2f p, Point2f q) {
            double diffY = p.y - q.y;
            double diffX = p.x - q.x;
            return diffX*diffX+diffY*diffY;
        }

        public static double distanceSquared(Point2f p, Point2f q) {
            return Math.sqrt(distance(p, q));
        }

        public Comparator<Point2f> orderByDistAsc() {
            return new OrderByDistanceAsc();
        }

        public class OrderByDistanceAsc implements Comparator<Point2f> {
            @Override
            public int compare(Point2f p, Point2f q) {
                int o = orientation(Point2f.this, p, q);
                if(o == 0)
                    return distance(Point2f.this, q)
                            >= distance(Point2f.this, p) ? -1 : 1;
                return o;
            }
        }

        @Override
        public String toString() {
            return String.format("Ponto2f (%.2f, %.2f)", x, y);
        }
    }

    public static void swap(Point2f[] points, int i, int j) {
        Point2f aux = points[i];
        points[i] = points[j];
        points[j] = aux;
    }

    public static Point2f nextTop(Stack<Point2f> stack) {
        Point2f top = stack.pop();
        Point2f point2f =  stack.peek();        // nextTop
        stack.push(top);
        return point2f;
    }

    public static Stack<Point2f> scan(Point2f[] points) {
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
        int limit = points.length;

        Stack<Point2f> stack = new Stack<>();
        stack.push(points[0]);
        stack.push(points[1]);
        /**
         *
         * */
        int sizeNewArray = 1;
        for(int i=1; i<limit; i++) {
            /**
             * Se os pontos i e i+1 forem colineares em relacao ao ponto p0
             * removemos o ponto i+1. Mas manteremos o ultimo ponto do array (o mais longe de p0)
             * */
            while(i < limit-1 && Point2f.orientation(points[0]
                    , points[i], points[i+1]) == 0) {
                i++;
            }
            points[sizeNewArray++] = points[i];
        }
        if(sizeNewArray < 3)
            return stack;
        stack.push(points[2]);
        for (int i=3; i<sizeNewArray; i++) {
            Point2f top = stack.peek();
            Point2f nextTop = nextTop(stack);
            Point2f current = points[i];
            while (Point2f.orientation(top, nextTop, current) < 1)
                stack.pop();
            stack.push(current);
        }
        return stack;
    }

    public static void test() {
        Point2f [] point2fs = {
             new Point2f(0,3)
            ,new Point2f(1,1)
            ,new Point2f(2,2)
            ,new Point2f(4,4)
            ,new Point2f(0,0)
            ,new Point2f(1,2)
            ,new Point2f(3,1)
            ,new Point2f(3,3)
        };
        Stack<Point2f> hull = scan(point2fs);
        while (!hull.empty()) {
            System.out.println(hull.pop());
        }
    }

    public static void main(String[] args) {
        test();
    }

}
