package com.matsemann;

import java.io.FileInputStream;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class Mia {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("inputs/mia.txt"));
        sc = new Scanner(System.in);

        while (true) {
            String s = sc.nextLine();
            if (s.equals("0 0 0 0")) {
                return;
            }

            String[] n = s.split(" ");
            int[] n1 = {Integer.parseInt(n[0]), Integer.parseInt(n[1])};
            int[] n2 = {Integer.parseInt(n[2]), Integer.parseInt(n[3])};

            if (isMia(n1) && !isMia(n2)) {
                System.out.println("Player 1 wins.");
            } else if (isMia(n1) && isMia(n2)) {
                System.out.println("Tie.");
            } else if (!isMia(n1) && isMia(n2)) {
                System.out.println("Player 2 wins.");
            } else if (isDouble(n1) && isDouble(n2)) {
                if (n1[0] > n2[0]) {
                    System.out.println("Player 1 wins.");
                } else if (n1[0] == n2[0]) {
                    System.out.println("Tie.");
                } else {
                    System.out.println("Player 2 wins.");
                }
            } else if(isDouble(n1) && !isDouble(n2)) {
                System.out.println("Player 1 wins.");
            } else if(!isDouble(n1) && isDouble(n2)) {
                System.out.println("Player 2 wins.");
            } else {
                int i1 = 0;
                int i2 = 0;
                if (n1[0] < n1[1]) {
                    i1 = Integer.parseInt(n1[1] + "" + n1[0]);
                } else {
                    i1 = Integer.parseInt(n1[0] + "" + n1[1]);
                }
                if (n2[0] < n2[1]) {
                    i2 = Integer.parseInt(n2[1] + "" + n2[0]);
                } else {
                    i2 = Integer.parseInt(n2[0] + "" + n2[1]);
                }

                if (i1 > i2) {
                    System.out.println("Player 1 wins.");
                } else if (i2 > i1) {
                    System.out.println("Player 2 wins.");
                } else {
                    System.out.println("Tie.");
                }
            }

        }


    }

    static boolean isDouble(int[] n) {
        return n[0] == n[1];
    }
    static boolean isMia(int[] n) {
        return (n[0] == 1 && n[1] == 2) || (n[0] == 2 && n[1] == 1);
    }


}
