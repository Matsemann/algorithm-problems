package com.matsemann;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import static com.matsemann.FunHouse.Dir.*;


public class FunHouse {

    enum Dir {
        LEFT(-1, 0),
        RIGHT(1, 0),
        UP(0, -1),
        DOWN(0, 1);

        private final int x;
        private final int y;

        Dir(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
    static class Vec {
        int x, y;
        public Vec(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("inputs/funhouse.txt"));

        sc = new Scanner(System.in);
        //int cases = sc.nextInt(); sc.nextLine();
        String size = sc.nextLine();
        int nr = 1;

        while (!size.equals("0 0")) {
            String[] s = size.split(" ");
            new FunHouse(nr++, Integer.parseInt(s[0]), Integer.parseInt(s[1]));

            size = sc.nextLine();
        }
    }


    char[][] board;
    Vec pos;
    Dir dir;


    public FunHouse(int nr, int w, int l) {
        readBoard(w, l);
        solveForBoard();

        board[pos.y][pos.x] = '&';

        System.out.println("HOUSE " + nr);

        for (char[] line : board) {
            for (char c : line) {
                System.out.print(c);
            }
            System.out.println();
        }

    }

    private void readBoard(int w, int l) {
        board = new char[l][];
        for (int i = 0; i < l; i++) {
            board[i] = sc.nextLine().toCharArray();
        }

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < l; j++) {
                if (board[j][i] == '*') {

                    if (i == 0) {
                        dir = RIGHT;
                    } else if (i == l - 1) {
                        dir = LEFT;
                    } else if (j == 0) {
                        dir = DOWN;
                    } else if (j == w - 1) {
                        dir = UP;
                    }
                    pos = new Vec(i, j);

                    break;
                }
            }
        }
    }

    private void solveForBoard() {
        while (true) {
            pos.x += dir.x;
            pos.y += dir.y;

            if (isAtEdge(pos)) {
                return;
            }

            if (board[pos.y][pos.x] == '/') {
                switch (dir) {
                    case UP: dir = RIGHT; break;
                    case DOWN: dir = LEFT; break;
                    case RIGHT: dir = UP; break;
                    case LEFT: dir = DOWN; break;
                }
            } else if (board[pos.y][pos.x] == '\\') {
                switch (dir) {
                    case UP: dir = LEFT; break;
                    case DOWN: dir = RIGHT; break;
                    case RIGHT: dir = DOWN; break;
                    case LEFT: dir = UP; break;
                }
            }
        }
    }

    private boolean isAtEdge(Vec pos) {
        return (pos.x == 0 || pos.x == board[0].length - 1 || pos.y == 0 || pos.y == board.length - 1);
    }

    /*
    public void solveForBoard() {
        Set<String> visited = new HashSet<>();
    public boolean isAtEdge(Point node) {
        return (node.x == 0 || node.x == maxX || node.y == 0 || node.y == maxY);
    }

    public boolean isOutsideEdge(Point node) {
        return (node.x < 0 || node.x > maxX || node.y < 0 || node.y > maxY);
    }*/
}
