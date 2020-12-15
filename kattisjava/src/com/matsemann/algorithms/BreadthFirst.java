package com.matsemann.algorithms;

import com.matsemann.WheresMyInternet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BreadthFirst {

    static class Node {
        List<Integer> neighbors = new ArrayList<>();
    }
    
    void bfs() {
        int nrVertices = 5;
        int nrEdgess = 10;


        List<Node> houses = new ArrayList<>(nrVertices);
        for (int i = 0; i < nrVertices; i++) {
            houses.add(new Node());
        }



        for (int i = 0; i < nrEdgess; i++) {
            int u = 0; // next edge pair
            int v = 0; // next edge pair

            houses.get(u).neighbors.add(v);
            houses.get(v).neighbors.add(u);
        }

        boolean[] visited = new boolean[nrVertices];
        Deque<Integer> Q = new ArrayDeque<>();
        Q.add(0); // bfs from first node
        visited[0] = true;

        while (!Q.isEmpty()) {
            int u = Q.poll();

            Node house = houses.get(u);

            for (Integer neighbor : house.neighbors) {
                if (!visited[neighbor] && house.neighbors.contains(neighbor)) {
                    Q.add(neighbor);
                    visited[neighbor] = true;
                }
            }

        }
    }
}
