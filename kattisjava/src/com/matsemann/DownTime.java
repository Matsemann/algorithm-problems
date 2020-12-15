package com.matsemann;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DownTime {


    static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("inputs/downtime.txt"));

        sc = new Scanner(System.in);

        int nrRequests = sc.nextInt();
        int maxPerServer = sc.nextInt();
        sc.nextLine();

        int[] requests = new int[110_000];

        for (int i = 0; i < nrRequests; i++) {
            int timestamp = Integer.parseInt(sc.nextLine());

            for (int j = timestamp; j < timestamp + 1000; j++) {
                requests[j]++;
            }
        }

        int max = 0;
        for (int r : requests) {
            if (r > max) {
                max = r;
            }
        }


        int servers = (int) Math.ceil(max / (double) maxPerServer);

        System.out.println(servers);

    }

}
