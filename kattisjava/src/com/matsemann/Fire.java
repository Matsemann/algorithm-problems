package com.matsemann;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by matskrugersvensson on 24-Mar-17.
 */
public class Fire {

    static Scanner sc;

    char[][] board;
    int maxX, maxY;
    Point startPos;
    List<Point> fires;

    public Fire() {
        readBoard();
        solveForBoard();
    }

    public void solveForBoard() {
        Set<String> visited = new HashSet<>();
        ArrayDeque<Point> queue = new ArrayDeque<>();

        if (startPos == null) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        queue.add(startPos);
        queue.addAll(fires);

        while (true) {
            Point node = queue.poll();

            if (node == null) {
                break;
            }

            if (visited.contains(node.x + "-" + node.y)) {
                continue;
            }

            if (node.type == '@') {
                visited.add(node.x + "-" + node.y);
            }


            if (node.type == '@' && board[node.y][node.x] == '*') {
                continue;
            }

            if (node.type == '@' && isAtEdge(node)) {
                System.out.println(node.distance + 1);
                return;
            }

            Point[] neighbors = new Point[]{
                    new Point(node.x + 1, node.y, node.type),
                    new Point(node.x - 1, node.y, node.type),
                    new Point(node.x, node.y + 1, node.type),
                    new Point(node.x, node.y - 1, node.type),
            };

            for (Point newPoint : neighbors) {
                if (isOutsideEdge(newPoint) || board[newPoint.y][newPoint.x] != '.') {
                    continue;
                }

                if (node.type == '@' && !visited.contains(newPoint.x + "-" + newPoint.y)) {
                    newPoint.distance = node.distance + 1;
                } else {
                    board[newPoint.y][newPoint.x] = '*';
                }

                queue.add(newPoint);
            }
        }

        System.out.println("IMPOSSIBLE");
    }

    public boolean isAtEdge(Point node) {
        return (node.x == 0 || node.x == maxX || node.y == 0 || node.y == maxY);
    }

    public boolean isOutsideEdge(Point node) {
        return (node.x < 0 || node.x > maxX || node.y < 0 || node.y > maxY);
    }

    public void readBoard() {
        maxX = sc.nextInt() - 1;
        maxY = sc.nextInt() - 1;
        sc.nextLine();


        startPos = null;
        fires = new ArrayList<>();
        board = new char[maxY + 1][maxX + 1];

        for (int i = 0; i <= maxY; i++) {
            String line = sc.nextLine();
            for (int j = 0; j <= maxX; j++) {
                char c = line.charAt(j);
                board[i][j] = c;

                if (c == '@') {
                    startPos = new Point(j, i, '@');
                    board[i][j] = '.';
                } else if (c == '*') {
                    fires.add(new Point(j, i, '*'));
                }

            }
        }
    }

    static class Point {
        int x, y, distance;
        char type;

        public Point(int x, int y, char type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("inputs/fire1.txt"));

        sc = new Scanner(System.in);
        int cases = sc.nextInt(); sc.nextLine();

        while (cases-- > 0) {
            new Fire();
        }
    }
}
