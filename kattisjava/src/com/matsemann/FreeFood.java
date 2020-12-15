package com.matsemann;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class FreeFood {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);

        int numEvents = Integer.parseInt(sc.nextLine());

        int[] days = new int[366];
        Arrays.fill(days, 0);

        while (numEvents --> 0) {
            String[] ins = sc.nextLine().split(" ");
            int from = Integer.parseInt(ins[0]);
            int to = Integer.parseInt(ins[1]);

            for (int i = from; i <= to; i++) {
                days[i]++;
            }

        }

        int count = 0;
        for (int i = 0; i < days.length; i++) {
            if (days[i] > 0) {
                count++;
            }
        }

        System.out.println(count);
    }
}
