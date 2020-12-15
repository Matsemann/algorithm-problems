package com.matsemann;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.*;

public class WheresMyInternet {


    static Scanner sc;


    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("inputs/wheresmyinternet.txt"));
        sc = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(System.out));

        int numHouses = sc.nextInt();
        int numLines = sc.nextInt();

        DisjointSet ds = new DisjointSet(numHouses);

        for (int i = 0; i < numLines; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;

            ds.union(u, v);
        }


        int firstHouseSet = ds.root(0);


        boolean notConnected = false;
        for (int i = 1; i < numHouses; i++) {
            if (ds.root(i) != firstHouseSet) {
                writer.println(i+1);
                notConnected = true;
            }
        }
        if (!notConnected) {
            System.out.println("Connected");
        }

        writer.close();
    }


    static class DisjointSet {

        int[] set;
        int[] size;

        public DisjointSet(int size) {
//            set = IntStream.range(0, size).toArray();
            set = new int[size];
            for (int i = 0; i < size; i++) {
                set[i] = i;
            }
            this.size = new int[size];
            Arrays.fill(this.size, 1);
        }

        public int root(int i) {
            while (i != set[i]) { // traverse parents until parent is itself
                set[i] = set[set[i]]; // flatten
                i = set[i];
            }

//            set[i] = i; // flatten
            return i;
        }

        public void union(int p, int q) {
            int firstRoot = root(p);
            int secondRoot = root(q);

            if (size[firstRoot] < size[secondRoot]) { // balance so always smallest is merged into larger
                set[firstRoot] = secondRoot; // merge
                size[secondRoot] += size[firstRoot]; // update sizes
            } else {
                set[secondRoot] = firstRoot;
                size[firstRoot] += size[secondRoot];
            }
        }

        /*
        public final int[] id;

        public DisjointSet(int n) {
            this.id = new int[n + 1];
            for (int i = 0; i < n; i++) {
                this.id[i] = i;
            }
        }

        public int root(int i) {
            if (id[i] == i)
                return i;

            id[i] = root(id[i]);
            return id[i];
        }

        public void union(int e1, int e2) {
            int i = root(e1);
            int j = root(e2);
            id[i] = j;
        }*/
    }

}
