package com.matsemann;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class HittingTargets {


    static Scanner sc;

    List<Target> targets = new ArrayList<>();

    public HittingTargets() {
        int nrTargets = sc.nextInt();
        sc.nextLine();
        for (int i  = 0; i < nrTargets; i++) {
            String[] def = sc.nextLine().split(" ");

            if (def[0].equals("rectangle")) {
                targets.add(new Rectangle(parseInt(def[1]), parseInt(def[2]), parseInt(def[3]), parseInt(def[4])));
            } else {
                targets.add(new Circle(parseInt(def[1]), parseInt(def[2]), parseInt(def[3])));
            }
        }

        int nrshots = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < nrshots; i++) {

            int hits = 0;
            String[] def = sc.nextLine().split(" ");
            int shotX = parseInt(def[0]);
            int shotY = parseInt(def[1]);

            for (Target target : targets) {
                if (target.hits(shotX, shotY)) {
                    hits++;
                }
            }

            System.out.println(hits);
        }
    }

    interface Target {
        boolean hits (int x, int y);
    }

    static class Rectangle implements Target {

        int x1, y1, x2, y2;

        public Rectangle(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public boolean hits(int x, int y) {
            return (x1 <= x && x <= x2) && (y1 <= y && y <= y2);
        }
    }

    static class Circle implements Target {

        int x1, y1, r;

        public Circle(int x1, int y1, int r) {
            this.x1 = x1;
            this.y1 = y1;
            this.r = r;
        }

        @Override
        public boolean hits(int x, int y) {
            return Math.sqrt(Math.pow(x1 - x, 2) + Math.pow(y1 - y, 2) ) <= r;
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/hittingtargets.txt"));

        sc = new Scanner(System.in);


        new HittingTargets();
    }
}
