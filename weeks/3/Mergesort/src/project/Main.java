package project;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Classe para ler os arquivos de teste do projeto
 * */

public class Main {

    private static final String [] paths = {
         "C:\\Users\\r028367\\Downloads\\collinear-testing\\collinear"
        ,"C:\\Users\\r028367\\Downloads\\collinear-testing\\collinear\\special"
        ,"C:\\Users\\r028367\\Downloads\\collinear-testing\\collinear\\special2"
    };

    public static final int INDEX_PATH = 0;

    private static void drawing(Solver solver, Point [] points) {
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points)
            p.draw();
        StdDraw.show();
        // print and draw the line segments
        for (LineSegment lineSegment : solver.segments()) {
            StdOut.println(lineSegment);
            lineSegment.draw();
        }
        StdDraw.show();
    }

    public static Point[] getPoints(String absolutePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(absolutePath));
        int cases = Integer.parseInt(reader.readLine().trim());
        System.out.printf("%d pontos\n", cases);
        Point [] points = new Point[cases];
        int m = 0;
        String in = "";
        while ((in = reader.readLine()) != null) {
            // verifiquei um arquivo com linhas em branco
            if(in.equals(""))
                continue;
            StringTokenizer tk = new StringTokenizer(in.trim(), " ");
            int x = Integer.parseInt(tk.nextToken());
            int y = Integer.parseInt(tk.nextToken());
            points[m++] = new Point(x, y);
        }
        return points;
    }

    public static List<Point[]> readFiles() {
        List<Point[]> listOfPoints = new ArrayList<>();
        try {
            File dir = new File(paths[INDEX_PATH]);
            File [] fs = dir.listFiles();
            if(fs != null) {
                for(File file : fs) {
                    if(file.isFile()) {
                        String name = file.getName();
                        if( name.matches("^.*\\.txt") ) {
                            String absolutePath = file.getAbsolutePath();
                            System.out.printf("Arquivo %s\n", absolutePath);
                            /**
                             * Aparentemente todos os arquivos de teste desse problema estao com
                             * um formato parecido com de competicao de programacao. Comeca com um
                             * inteiro N na primeira linha e depois segue N linhas com pares de inteiros
                             * representando pontos num plano cartesiano
                             * */
                            listOfPoints.add(getPoints(absolutePath));
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return listOfPoints;
    }

    public static void bruteCollinearPointsTest(Point [] points) {
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        System.out.printf("Número de segmentos %d\n\n", bruteCollinearPoints.numberOfSegments());
        drawing(bruteCollinearPoints, points);
    }

    public static void fastCollinearPointsTest(Point [] points) {
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        System.out.printf("Número de segmentos %d\n\n", fastCollinearPoints.numberOfSegments());
        drawing(fastCollinearPoints, points);
    }

    public static void readAllFiles() {
        List<Point[]> listOfPoints = readFiles();
        for(Point[] points : listOfPoints)
            fastCollinearPointsTest(points);
        for(Point[] points : listOfPoints)
            bruteCollinearPointsTest(points);
    }

    public static void readSpecificFile() {
        try {
            Point [] points =  getPoints("C:\\Users\\r028367\\Downloads\\collinear-testing\\collinear\\inarow.txt");
            //fastCollinearPointsTest(points);
            bruteCollinearPointsTest(points);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        readSpecificFile();
    }
}
