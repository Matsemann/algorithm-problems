package com.matsemann;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConvexPolygonArea {


    static Scanner sc;

    public ConvexPolygonArea() {
        String[] line = sc.nextLine().split(" ");

        List<int[]> points = new ArrayList<>();

        for (int i = 1; i < line.length; i += 2) {
            points.add(new int[]{Integer.parseInt(line[i]), Integer.parseInt(line[i + 1])});
        }

        System.out.println(area(points));
    }

    public double area(List<int[]> points) {
        double area = 0;
        for (int i = 0; i < points.size(); i++) {
            int j = (i + 1) % points.size();
            int[] pointI = points.get(i);
            int[] pointJ = points.get(j);
            area += (pointI[0] * pointJ[1]) - (pointI[1] * pointJ[0]);
        }

        area = area / 2;

        return Math.abs(area);
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/convexpolygonarea.txt"));

        sc = new Scanner(System.in);

        int cases = Integer.parseInt(sc.nextLine());

        while (cases-- > 0) {
            new ConvexPolygonArea();
        }
    }


}
