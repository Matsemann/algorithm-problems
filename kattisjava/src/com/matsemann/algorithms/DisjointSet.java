package com.matsemann.algorithms;

import java.util.Arrays;

public class DisjointSet {

    int[] set;
    int[] size;

    public DisjointSet(int size) {
        set = new int[size];
        for (int i = 0; i < size; i++) {
            set[i] = i;
        }
        this.size = new int[size];
        Arrays.fill(this.size, 1);
    }

    public int root(int i) {
        while (i != set[i]) { // traverse parents until parent is itself
            set[i] = set[set[i]];
            i = set[i];
        }

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
}
