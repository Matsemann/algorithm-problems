package idiopen;

import java.io.FileInputStream;
import java.util.*;

/**
 * Created by matskrugersvensson on 22-Apr-17.
 */
public class Fencing {


    static class Pole {
        final int x, y;

        Pole(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Tree {
        final int length, time;

        Tree(int length, int time) {
            this.length = length;
            this.time = time;
        }
    }

    static ArrayList<Pole> getHullPoints(List<Pole> poles) {
        poles.sort((a, b) -> {
            if (a.x - b.x == 0)
                return a.y - b.y;
            else
                return a.x - b.x;
        });

        ArrayList<Pole> uQueue = new ArrayList<>();

        uQueue.add(poles.get(0));
        uQueue.add(poles.get(1));


        for (int i = 2; i < poles.size(); i++) {
            Pole pole = poles.get(i);

            while (uQueue.size() >= 2 && isTurningLeft(uQueue.get(uQueue.size() - 2), uQueue.get(uQueue.size() - 1), pole) > 0) {
                uQueue.remove(uQueue.size() - 1);
            }

            uQueue.add(pole);
        }


        ArrayList<Pole> lQueue = new ArrayList<>();

        lQueue.add(poles.get(poles.size() - 1));
        lQueue.add(poles.get(poles.size() - 2));

        for (int i = poles.size() - 3; i >= 0; i--) {
            Pole pole = poles.get(i);

            while (lQueue.size() >= 2 && isTurningLeft(lQueue.get(lQueue.size() - 2), lQueue.get(lQueue.size() - 1), pole) > 0) {
                lQueue.remove(lQueue.size() - 1);
            }

            lQueue.add(pole);
        }

        uQueue.remove(uQueue.size() - 1);
        uQueue.addAll(lQueue);

        return uQueue;
    }

    private static float getHullLength(ArrayList<Pole> hullPoints) {
        float length = 0;

        for (int i = 0; i < hullPoints.size() - 1; i++) {
            Pole p1 = hullPoints.get(i);
            Pole p2 = hullPoints.get(i + 1);
            double dst = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
            length += dst;
        }

        return length;
    }

    static int isTurningLeft(Pole pole1, Pole pole2, Pole pole3) {
        int x1 = pole2.x - pole1.x;
        int y1 = pole2.y - pole1.y;

        int x2 = pole3.x - pole2.x;
        int y2 = pole3.y - pole2.y; // obs

        int cross = (x1 * y2) - (x2 * y1);

        return cross;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/fencing.txt"));

        Scanner scanner = new Scanner(System.in);

        int noftrees = scanner.nextInt();
        int nofpoles = scanner.nextInt();


        ArrayList<Tree> trees = new ArrayList<>();
        ArrayList<Pole> poles = new ArrayList<>();


        for (int i = 0; i < noftrees; i++) {
            trees.add(new Tree(scanner.nextInt(), scanner.nextInt()));
        }

        for (int i = 0; i < nofpoles; i++) {
            poles.add(new Pole(scanner.nextInt(), scanner.nextInt()));
        }

        ArrayList<Pole> hullPoints = getHullPoints(poles);
        float hullLength = getHullLength(hullPoints);


        int result = dpTree(trees, hullLength);

        System.out.println(result);

    }

    private static int dpTree(ArrayList<Tree> trees, float min) {
        int maxT = 0;
        for (Tree t : trees) {
            if (t.time > maxT) {
                maxT = t.time;
            }
        }

        int[][] d = new int[maxT][trees.size() + 1];
        for (int y = 1; y < maxT; y++) {

            for (int x = 1; x < trees.size() + 1; x++) {
                int w = trees.get(x-1).length;
                int t = trees.get(x-1).time;

                if (y >= t) {
                    d[y][x] = Math.max(d[y][x-1], d[y-t][x-1] + w);
                    if (d[y][x] >= min) {
                        return y;
                    }
                } else {
                    d[y][x] = d[y][x-1];
                }
            }


        }
        return 9999; // shouldnt happen?
    }

}
