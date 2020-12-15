package com.matsemann;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


public class Telescope {

    static Scanner sc;
    public Telescope(int blur, int h, int w) {
        int[][] grid = new int[h][w];

        for (int i = 0; i < h; i++) {
            String[] split = sc.nextLine().split(" ");
            for (int j = 0; j < w; j++) {
                grid[i][j] = Integer.parseInt(split[j], 16);
            }
        }


        printGrid(blur(grid, blur));
    }



    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("inputs/telescopein.txt"));
        sc = new Scanner(System.in);

        String[] split = sc.nextLine().split(" ");
        new Telescope(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
    }


    public static int[][] blur(int[][] board, int blurSize) {
        int radius = (blurSize - 1) / 2;

        int[][] blurred = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                int sum = 0;

                for (int y = i - radius; y <= i + radius; y++) {
                    for (int x = j - radius; x <= j + radius; x++) {
                        sum += getValue(board, x, y);
                    }
                }

                blurred[i][j] = sum / (blurSize * blurSize);
            }
        }

        return blurred;
    }

    private static int getValue(int[][] board, int x, int y) {
        if ( y < 0 || y > board.length - 1) {
            return 0;
        } else if (x < 0 || x > board[y].length - 1 ) {
            return 0;
        } else {
            return board[y][x];
        }
    }

    private void printGrid(int[][] grid) {
        for (int[] ints : grid) {
            for (int anInt : ints) {
                System.out.print(pad(Integer.toString(anInt, 16)) + " ");
            }
            System.out.println();
        }
    }

    private String pad(String nr) {
        return "0000".substring(nr.length()) + nr;
    }


}
