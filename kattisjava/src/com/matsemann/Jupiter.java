package com.matsemann;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;

public class Jupiter {

    static Scanner sc;

    public Jupiter(int nrDownlinks, int nrQueues, int nrSensors) {

        long start = System.nanoTime();
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

        int nrVertices = (nrQueues * 2) * nrDownlinks + nrDownlinks + 2;
        int tIndex = nrVertices - 1;
        int[][] graph = new int[nrVertices][nrVertices];

        int vCount = 0;


        List<Set<Integer>> neigbours = new ArrayList<>(nrVertices);
        for (int i = 0; i < nrVertices; i++) {
            neigbours.add(new HashSet<>());
        }

        for (int i = 0; i < nrDownlinks; i++) {

            int downLinkNode = (nrQueues * 2 + 1) * (i + 1);

            for (int j = 0; j < nrQueues; j++) {
                // from S to each queue, data for that downlink
                ++vCount;
                graph[0][vCount] = dataPerDownlink[i][j];
                neigbours.get(0).add(vCount);
                neigbours.get(vCount).add(0);

                // from the queue to special node, max capacity of the queue
                graph[vCount][vCount+1] = queueSizes[j];
                neigbours.get(vCount).add(vCount+1);
                neigbours.get(vCount+1).add(vCount);
                ++vCount;

                // from capacity node to common downlink node
                //int downLinkNode = vCount + (nrQueues - j - 1) * 2 + 1;
                graph[vCount][downLinkNode] = MAX_VALUE;
                neigbours.get(vCount).add(downLinkNode);
                neigbours.get(downLinkNode).add(vCount);

                // possibly overflow to next downlink queues
                if (i + 1 < nrDownlinks) {
                    int i1 = vCount + (nrQueues * 2);
                    graph[vCount][i1] = MAX_VALUE;
                    neigbours.get(vCount).add(i1);
                    neigbours.get(i1).add(vCount);
                }
            }

            vCount++;

            // from downlink to sink
            graph[downLinkNode][tIndex] = transferPerDownlink[i];
            neigbours.get(downLinkNode).add(tIndex);
            neigbours.get(tIndex).add(downLinkNode);

        }

        int totalSensorData = 0;
        for (int[] datas : dataPerDownlink) {
            for (int data : datas) {
                totalSensorData += data;
            }
        }

        //System.out.println((System.nanoTime() - start) / 1000000.0f);

        //print(graph);

        int maxFlowRes = new MaxFlow().runEdmondsKarp(graph, 0, tIndex, neigbours);

        if (maxFlowRes == totalSensorData) {
            System.out.println("possible");
        } else {
            System.out.println("impossible");
        }
        System.out.println("(max flow should be 29108: " + maxFlowRes + ")");

    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("inputs/jupiter1.txt"));

        sc = new Scanner(System.in);

        String[] split = sc.nextLine().split(" ");
        long start = System.nanoTime();
        new Jupiter(parseInt(split[0]), parseInt(split[1]), parseInt(split[2]));

        System.out.println((System.nanoTime() - start) / 1000000.f);
    }

    static class MaxFlow {


        int runEdmondsKarp(int[][] graph, int sIndex, int tIndex, List<Set<Integer>> neigbours) {
            int maxFlow = 0;
            int numEdges = graph.length;

            int[] parents = new int[numEdges];


            while (bfsOnResidual(graph, sIndex, tIndex, parents, neigbours)) {

                int minResidual = MAX_VALUE;

                int v = tIndex;
                while (v != sIndex) {
                    int u = parents[v];
                    minResidual = Math.min(minResidual, graph[u][v]);
                    v = u;
                }

                v = tIndex;
                while (v != sIndex) {
                    int u = parents[v];
                    graph[u][v] -= minResidual;
                    graph[v][u] += minResidual;
                    v = u;
                }

                maxFlow += minResidual;

            }

            return maxFlow;
        }


        boolean bfsOnResidual(int[][] residual, int sIndex, int tIndex, int[] fillParents, List<Set<Integer>> neigbours) {
            //Arrays.fill(fillParents, -1);
            boolean[] visited = new boolean[fillParents.length];

            Deque<Integer> Q = new ArrayDeque<>(fillParents.length);
            Q.add(sIndex);
            visited[sIndex] = true;

            while (!Q.isEmpty()) {

                Integer u = Q.poll();

                for (Integer v : neigbours.get(u)) {
                    if (!visited[v] && residual[u][v] > 0) {
                        Q.add(v);
                        fillParents[v] = u;
                        visited[v] = true;

                        if (v == tIndex) {
                            return true;
                        }

                    }
                }


            }

            return false;
        }
    }
}
