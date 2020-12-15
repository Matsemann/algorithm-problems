package com.matsemann;

import com.matsemann.JupiterNew.MaxFlowNodes.Node;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;

public class JupiterNew {

    static Scanner sc;

    public JupiterNew(int nrDownlinks, int nrQueues, int nrSensors) {

        int[] queueSizes = new int[nrQueues];
        int[] sensorToQueue = new int[nrSensors];
        int[][] dataPerDownlink = new int[nrDownlinks][nrQueues];
        int[] transferPerDownlink = new int[nrDownlinks];


        String[] sensorQueueLine = sc.nextLine().split(" ");
        for (int i = 0; i < nrSensors; i++) {
            sensorToQueue[i] = parseInt(sensorQueueLine[i]) - 1;
        }

        String[] queueSizeLine = sc.nextLine().split(" ");
        for (int i = 0; i < nrQueues; i++) {
            queueSizes[i] = parseInt(queueSizeLine[i]);
        }

        for (int i = 0; i < nrDownlinks; i++) {
            String[] line = sc.nextLine().split(" ");
            transferPerDownlink[i] = parseInt(line[0]);

            for (int j = 0; j < nrSensors; j++) {
                int data = parseInt(line[j + 1]);
                dataPerDownlink[i][sensorToQueue[j]] += data;
            }
        }

        MaxFlowNodes maxFlow = new MaxFlowNodes();

        for (int i = 0; i < nrDownlinks; i++) {

            Node downlinkNode = maxFlow.addNode("D" + i);
            maxFlow.addEdge(downlinkNode, maxFlow.sink, transferPerDownlink[i]);

            for (int j = 0; j < nrQueues; j++) {
                // from S to each queue, data for that downlink
                Node node = maxFlow.addNode("Q" + j + "D" + i);
                maxFlow.addEdge(maxFlow.source, node, dataPerDownlink[i][j]);

                // from the queue to special node, max capacity of the queue
                Node capNode = maxFlow.addNode("QCAP" + j + "D" + i);
                maxFlow.addEdge(node, capNode, queueSizes[j]);

                // from capacity node to common downlink node
                maxFlow.addEdge(capNode, downlinkNode, MAX_VALUE);

                // possibly overflow from next downlink queues
                if (i > 0) {
                    Node prevNode = maxFlow.getNode("QCAP" + j + "D" + (i - 1));
                    maxFlow.addEdge(prevNode, node, MAX_VALUE);
                }
            }

        }

        int totalSensorData = 0;
        for (int[] datas : dataPerDownlink) {
            for (int data : datas) {
                totalSensorData += data;
            }
        }

        //System.out.println((System.nanoTime() - start) / 1000000.0f);

        //print(graph);

        int maxFlowRes = maxFlow.findMaxFlow();

        if (maxFlowRes == totalSensorData) {
            System.out.println("possible");
        } else {
            System.out.println("impossible");
        }
        System.out.println("(max flow should be 29108: " + maxFlowRes + ")");

    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        //Thread.sleep(10000);
        //Thread.sleep(10000);
        System.setIn(new FileInputStream("inputs/jupiter1.txt"));

        sc = new Scanner(System.in);

        String[] split = sc.nextLine().split(" ");
        long start = System.nanoTime();
        new JupiterNew(parseInt(split[0]), parseInt(split[1]), parseInt(split[2]));

        System.out.println((System.nanoTime() - start) / 1000000.f);
    }

    static class MaxFlowNodes {

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
}
