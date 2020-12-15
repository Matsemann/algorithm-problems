package com.matsemann;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by matskrugersvensson on 27-Sep-17.
 */
public class Deathstar {

    static Scanner sc;

    public Deathstar(int size) {
        int[] nrs = new int[size];

        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            String[] line = sc.nextLine().split(" ");
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int nr = matrix[i][j];
                nrs[i] |= nr;
                nrs[j] |= nr;
            }
        }

        for (int i = 0; i < size; i++) {
            System.out.print(nrs[i]);
            if (i < size - 1) {
                System.out.print(" ");
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("inputs/deathstar1.txt"));
        sc = new Scanner(System.in);

        int size = Integer.parseInt(sc.nextLine());

        new Deathstar(size);
    }
}
