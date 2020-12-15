package com.matsemann.algorithms;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;

public class Utils {

    public static void print(int[][] arr) {
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
                if (anInt < 2000) {
                    System.out.print("\t\t");
                }
            }
            System.out.println();
        }
    }

    PrintWriter fastWriter = new PrintWriter(new BufferedOutputStream(System.out));


}
