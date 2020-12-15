package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Volim {


    static Scanner sc;

    public Volim(int start, int games) {

        int time = 210;
        int current = start;

        for (int i = 0; i < games; i++) {

            String[] in = sc.nextLine().split(" ");
            int used = parseInt(in[0]);
            String result = in[1];

            time -= used;

            if (time <= 0) {
                System.out.println(current);
                return;
            }

            if (result.equals("T")) {
                current++;
                if (current == 9) {
                    current = 1;
                }
            }

        }

    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/volim.txt"));

        sc = new Scanner(System.in);

        int start = parseInt(sc.nextLine());
        int games = parseInt(sc.nextLine());
        new Volim(start, games);
    }
}
