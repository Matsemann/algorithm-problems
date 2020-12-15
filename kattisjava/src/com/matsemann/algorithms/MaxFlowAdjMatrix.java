package com.matsemann.algorithms;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;


// Edmonds-Karp
public class MaxFlowAdjMatrix {

    public static void main(String[] args) {
        int[][] ints = {
                {0, 3, 0, 3, 0, 0, 0},
                {0, 0, 4, 0, 0, 0, 0},
                {3, 0, 0, 1, 2, 0, 0},
                {0, 0, 0, 0, 2, 6, 0},
                {0, 1, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 9},
                {0, 0, 0, 0, 0, 0, 0},
        };

        int i = runEdmondsKarp(ints, 0, 6);
        System.out.println(i);
    }

    static int runEdmondsKarp(int[][] graph, int sIndex, int tIndex) {
        int maxFlow = 0;
        int numEdges = graph.length;

        int[][] residual = new int[numEdges][numEdges];
        int[] parents = new int[numEdges];

        for (int u = 0; u < numEdges; u++) {
            for (int v = 0; v < numEdges; v++) {
                residual[u][v] = graph[u][v];
            }
        }

        while (true) {
            boolean pathToT = bfsOnResidual(residual, sIndex, tIndex, parents);
            if (!pathToT) {
                break;
            }

            int minResidual = Integer.MAX_VALUE;

            int v = tIndex;
            while (v != sIndex) {
                int u = parents[v];
                minResidual = Math.min(minResidual, residual[u][v]);
                v = u;
            }

            v = tIndex;
            while (v != sIndex) {
                int u = parents[v];
                residual[u][v] -= minResidual;
                residual[v][u] += minResidual;
                v = u;
            }

            maxFlow += minResidual;

        }

        return maxFlow;
    }

    static boolean bfsOnResidual(int[][] residual, int sIndex, int tIndex, int[] fillParents) {
        Arrays.fill(fillParents, -1);
        boolean[] visited = new boolean[fillParents.length];

        Deque<Integer> Q = new ArrayDeque<>();
        Q.add(sIndex);
        visited[sIndex] = true;

        while (!Q.isEmpty()) {

            Integer u = Q.poll();

            for (int v = 0; v < fillParents.length; v++) {
                if (!visited[v] && residual[u][v] > 0) {
                    Q.add(v);
                    fillParents[v] = u;
                    visited[v] = true;
                }
            }

        }

        return (visited[tIndex]);
    }

}
