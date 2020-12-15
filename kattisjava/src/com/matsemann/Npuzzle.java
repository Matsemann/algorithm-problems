package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class Npuzzle {


    static Scanner sc;

    char[][] fasit = {
            {'A', 'B', 'C', 'D'},
            {'E', 'F', 'G', 'H'},
            {'I', 'J', 'K', 'L'},
            {'M', 'N', 'O', '.'},
    };

    public Npuzzle() {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            String string = sc.nextLine();
            for (int j = 0; j < 4; j++) {
                char c = string.charAt(j);
                if (c != '.') {
                    sum += findCharDistance(c, j, i);
                }
            }
        }
        System.out.println(sum);
    }

    private int findCharDistance(char c, int x, int y) {
        for (int i = 0; i < 4; i++) {
            char[] chars = fasit[i];
            for (int j = 0; j < 4; j++) {
                if (chars[j] == c) {
                    return manhattan(x, y, j, i);
                }
            }
        }
        return 0;
    }

    private int manhattan(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }


    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/npuzzle.txt"));

        sc = new Scanner(System.in);


        new Npuzzle();

    }
}
