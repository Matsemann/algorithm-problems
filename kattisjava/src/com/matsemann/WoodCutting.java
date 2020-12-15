package com.matsemann;

import java.io.FileInputStream;
import java.util.*;

import static java.lang.Integer.parseInt;

public class WoodCutting {


    static Scanner sc;

    public WoodCutting() {
        int customers = parseInt(sc.nextLine());
        int[] totals = new int[customers];

        for (int i = 0; i < customers; i++) {
            String[] in = sc.nextLine().split(" ");
            for (int j = 1; j <= parseInt(in[0]); j++) {
                totals[i] += parseInt(in[j]);
            }
        }

        Arrays.sort(totals);

        int time = 0;
        int sum = 0;

        for (int i = 0; i < totals.length; i++) {
            int total = totals[i];
            time += total;
            sum += time;
        }

        //System.out.println(String.format("%.15f", (double) sum / customers));
        System.out.println(sum / (1.0 * customers));
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/woodcutting.txt"));

        sc = new Scanner(System.in);
/*
        int cases = parseInt(sc.nextLine());

        while (cases --> 0) {
            new WoodCutting();
        }
// */

        StringBuilder sb = new StringBuilder();
        int t = sc.nextInt();

        for(int i = 0; i < t; i++) {
            int n = sc.nextInt();
            List<Integer> times = new ArrayList<>();
            for(int k = 0; k < n; k++) {
                int time = 0;
                int w = sc.nextInt();
                for(int j = 0; j < w; j++) {
                    time += sc.nextInt();
                }
                times.add(time);

            }
            Collections.sort(times);
            long sumOfPrev = 0;
            long total = 0;
            for(Integer curr:times) {
                total += sumOfPrev + curr;
                sumOfPrev += curr;
            }
            sb.append(total / (n * 1.0));
            sb.append("\n");
        }

        System.out.print(sb);
        // */
    }
}
