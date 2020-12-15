package com.matsemann;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by matskrugersvensson on 27-Sep-17.
 */
public class CountingStars {

    static Scanner sc;
    
    char[][] board;
    private int caseNr;

    public CountingStars(int h, int w, int caseNr) {
        this.caseNr = caseNr;
        board = new char[h][w];
        
        for (int i = 0; i < h; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < w; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        solve();
    }

    private void solve() {
        int stars = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '-') {
                    stars++;
                    Pos pos = new Pos(j, i);
                    removeStar(pos);
                }
            }
        }

        System.out.println("Case " + caseNr + ": " + stars);

    }
    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private void removeStar(Pos start) {
        Deque<Pos> q = new ArrayDeque<>();
        q.offer(start);

        while (!q.isEmpty()) {
            Pos pos = q.pop();
            board[pos.y][pos.x] = '#';

            if (pos.y - 1 >= 0 && board[pos.y - 1][pos.x] == '-') {
                q.offerFirst(new Pos(pos.x, pos.y - 1));
            }
            if (pos.y + 1 < board.length && board[pos.y + 1][pos.x] == '-') {
                q.offerFirst(new Pos(pos.x, pos.y + 1));
            }

            if (pos.x - 1 >= 0 && board[pos.y][pos.x - 1] == '-') {
                q.offerFirst(new Pos(pos.x - 1, pos.y));
            }
            if (pos.x + 1 < board[pos.y].length && board[pos.y][pos.x + 1] == '-') {
                q.offerFirst(new Pos(pos.x + 1, pos.y));
            }

        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("inputs/countingstars.txt"));
        sc = new Scanner(System.in);

        int caseNr = 0;
        while (sc.hasNext()) {
            caseNr++;
            String[] split = sc.nextLine().split(" ");

            new CountingStars(Integer.parseInt(split[0]), Integer.parseInt(split[1]), caseNr);
        }
    }
}
