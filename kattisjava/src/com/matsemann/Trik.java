package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class Trik {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/trik.txt"));

        sc = new Scanner(System.in);

        String[] moves = sc.nextLine().split("");

        int pos = 1;

        for (String move : moves) {
            if (move.equals("A") && pos == 1) {
                pos = 2;
            } else if (move.equals("A") && pos == 2) {
                pos = 1;
            } else if (move.equals("B") && pos == 2) {
                pos = 3;
            } else if (move.equals("B") && pos == 3) {
                pos = 2;
            } else if (move.equals("C") && pos == 1) {
                pos = 3;
            } else if (move.equals("C") && pos == 3) {
                pos = 1;
            }
        }

        System.out.println(pos);

    }
}
