package com.matsemann;

import java.util.Scanner;

public class Apples {

    static Scanner sc;

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);

        String[] sizes = sc.nextLine().split(" ");
        int rows = Integer.parseInt(sizes[0]);
        int cols = Integer.parseInt(sizes[1]);

        String[][] board = new String[rows][cols];
        String[][] result = new String[rows][cols];
        int[][] freesBelow = new int[rows][cols];

        for (int j = 0; j < cols; j++) {
            freesBelow[rows - 1][j] = 0;
        }

        for (int i = 0; i < rows; i++) {
            String[] line = sc.nextLine().split("");
            for (int j = 0; j < cols; j++) {
                board[i][j] = line[j];
                result[i][j] = line[j];
            }
        }

        long start = System.currentTimeMillis();

        for (int i = rows - 2; i >= 0; i--) {
            for (int j = 0; j < cols; j++) {
                if (board[i + 1][j].equals("a")) {
                    freesBelow[i][j] = freesBelow[i + 1][j];
                } else if (board[i + 1][j].equals(".")) {
                    freesBelow[i][j] = freesBelow[i + 1][j] + 1;
                } else if (board[i + 1][j].equals("#")) {
                    freesBelow[i][j] = 0;
                }

                if (board[i][j].equals("a")) {
                    result[i][j] = ".";
                    result[i + freesBelow[i][j]][j] = "a";
                }
            }
        }

        long end = System.currentTimeMillis() - start;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(result[i][j]);
//                System.out.print(result[i][j]);
            }
//            System.out.println();
            sb.append("\n");
        }

        System.out.println(sb.toString());

//        System.out.println("time spent");
//        System.out.println(end);
//        System.out.println("print time");
//        System.out.println(System.currentTimeMillis() - start);
    }
}
