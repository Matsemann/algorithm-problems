package com.matsemann;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Banjo {

    static Scanner sc;

    double x1, y1, x2, y2, xc, yc, r, t;

    public Banjo(String[] values) {
        x1 = Integer.parseInt(values[0]);
        y1 = Integer.parseInt(values[1]);
        x2 = Integer.parseInt(values[2]);
        y2 = Integer.parseInt(values[3]);
        xc = Integer.parseInt(values[4]);
        yc = Integer.parseInt(values[5]);
        r = Integer.parseInt(values[6]);
        t = Integer.parseInt(values[7]);

        solve();
    }

    public void solve() {
        Point point = nearestPointFromCircleOnLine();

        if (point == null) { // circle outside segment, shortest distance is straight ahead
            double distance = dst(x1, y1, x2, y2);
            printAnswer(distance);
            return;
        }

        double distanceToLine = dst(point.x, point.y, xc, yc);
        double distanceToSafeCross = distanceFromCircleCenterWeCanCrossWithoutBurningToDeath();

        double distanceOutFromLine = Math.max(0, distanceToSafeCross - distanceToLine);

        if (distanceOutFromLine <= 0) {
            printAnswer(dst(x1, y1, x2, y2));
            return;
        }

        // Calculating distances from start/end to where we can cross the circle
        double distanceAlongLine1 = dst(x1, y1, point.x, point.y) - t / 2;
        double hypToCircleCross1 = Math.sqrt(Math.pow(distanceAlongLine1, 2) + Math.pow(distanceOutFromLine, 2));

        double distanceAlongLine2 = dst(x2, y2, point.x, point.y) - t / 2;
        double hypToCircleCross2 = Math.sqrt(Math.pow(distanceAlongLine2, 2) + Math.pow(distanceOutFromLine, 2));

        // adding the distance traveling through the circle/lava
        double totalDistance = hypToCircleCross1 + hypToCircleCross2 + t;
        printAnswer(totalDistance);
    }

    public void printAnswer(double answer) {
        System.out.printf("%.2f%n", answer);
    }

    public double dst(double px1, double py1, double px2, double py2) {
        return Math.sqrt(Math.pow(px1 - px2, 2) + Math.pow(py1 - py2, 2));
    }

    public Point nearestPointFromCircleOnLine() {

        double d = (xc - x1) * (x2 - x1) + (yc - y1) * (y2 - y1);
        double n = Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2);

        double k = d / n;

        if (k < 0 || k > 1) {
            return null;
        }

        double x = x1 + k * (x2 - x1);
        double y = y1 + k * (y2 - y1);

        return new Point(x, y);
    }

    /*public double distanceFromCircleCenterToLineBetweenPoints() {
        double d = (y2 - y1) * xc - (x2 - x1) * yc + (x2 * y1) + (y2 * x1);
        double n = Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 -x1, 2));

        return Math.abs(d) / n;
    }*/

    public double distanceFromCircleCenterWeCanCrossWithoutBurningToDeath() {
        if (t >= 2 * r) {
            return 0;
        }
        return Math.sqrt(Math.pow(r, 2) - Math.pow(t / 2, 2));
    }

    static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("inputs/banjo1.txt"));

        sc = new Scanner(System.in);

        while (true) {
            String line = sc.nextLine();
            if (line.equals("0 0 0 0 0 0 0 0")) {
                break;
            }
            new Banjo(line.split(" "));
        }
    }
}
