package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class Bijele {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/bijele.txt"));

        sc = new Scanner(System.in);

        int[] correct = new int[]{1, 1, 2, 2, 2, 8};

        for (int i = 0; i < correct.length; i++) {
            int diff = correct[i] - sc.nextInt();
            System.out.print(diff);
            if (i != 5) {
                System.out.print(" ");
            }
        }
    }
}
