package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class ClosestSums {


    static Scanner sc;

    public ClosestSums(int caseNr, int[] nrs, int[] sums) {
        int[] sumOfSums = sumOfSums(nrs);
        System.out.println("Case " + caseNr + ":");

        for (int sum : sums) {
            int closest = closest(sumOfSums, sum);
            System.out.println("Closest sum to " + sum + " is " + closest + ".");
        }

    }

    private int[] sumOfSums(int[] nrs) {
        int[] allSums = new int[(nrs.length * (nrs.length - 1)) / 2];
        int index = 0;

        for (int i = 0; i < nrs.length; i++) {
            for (int j = i + 1; j < nrs.length; j++) {
                allSums[index++] = nrs[i] + nrs[j];
            }
        }

        return allSums;
    }

    private int closest(int[] sums, int nr) {
        int minDiff = Integer.MAX_VALUE;
        int closest = Integer.MAX_VALUE;

        for (int sum : sums) {
            int diff = Math.abs(sum - nr);
            if (diff < minDiff) {
                minDiff = diff;
                closest = sum;
            }
        }

        return closest;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/closestsums.txt"));

        sc = new Scanner(System.in);
        int caseNr = 1;

        while (sc.hasNextLine()) {
            int numbers = Integer.parseInt(sc.nextLine());
            int[] nrs = new int[numbers];
            for (int i = 0; i < numbers; i++) {
                nrs[i] = Integer.parseInt(sc.nextLine());
            }

            int nrSums = Integer.parseInt(sc.nextLine());
            int[] sums = new int[nrSums];
            for (int i = 0; i < nrSums; i++) {
                sums[i] = Integer.parseInt(sc.nextLine());
            }

            new ClosestSums(caseNr++, nrs, sums);
        }
    }
}
