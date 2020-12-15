package com.matsemann.algorithms;

import java.util.*;

import static java.lang.Integer.MAX_VALUE;

public class MaxFlowNodes {

    Node source, sink;
    Map<String, Node> nodes = new HashMap<>();

    static class Node {
        final String name;
        final Map<Node, Integer> residual = new HashMap<>();

        Node residualParent;

        Node(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            return Objects.equals(name, ((Node) o).name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public MaxFlowNodes() {
        source = addNode("source");
        sink = addNode("sink");
    }


    public Node addNode(String name) {
        Node node = new Node(name);
        nodes.put(name, node);
        return node;
    }

    public Node getNode(String name) {
        return nodes.get(name);
    }

    public void addEdge(Node from, Node to, int capacity) {
        from.residual.put(to, capacity);
        to.residual.put(from, 0);
    }

    public int findMaxFlow() {
        int maxFlow = 0;

        while (bfsOnResidual()) {

            // Find min residual in path
            int minResidual = MAX_VALUE;

            Node v = sink;
            while (v != source) {
                Node u = v.residualParent;
                minResidual = Math.min(minResidual, u.residual.get(v));
                v = u;
            }

            // Update residual
            v = sink;
            while (v != source) {
                Node u = v.residualParent;
                Integer oldUv = u.residual.get(v);
                u.residual.put(v, oldUv - minResidual);
                Integer oldVu = v.residual.get(u);
                v.residual.put(u, oldVu + minResidual);
                v = u;
            }

            maxFlow += minResidual;
        }

        return maxFlow;
    }

    private boolean bfsOnResidual() {
        Set<Node> visited = new HashSet<>(nodes.size());
        Deque<Node> queue = new ArrayDeque<>(nodes.size());
        queue.add(source);

        while (!queue.isEmpty()) {
            Node u = queue.poll();

            for (Map.Entry<Node, Integer> entry : u.residual.entrySet()) {
                Node v = entry.getKey();
                if (!visited.contains(v) && entry.getValue() > 0) {
                    queue.add(v);
                    visited.add(v);
                    v.residualParent = u;

                    if (v == sink) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}