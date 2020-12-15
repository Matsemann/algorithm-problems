package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class Quadrant {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/quadrant.txt"));

        sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        int q = (b > 0 ? 1 : 4);

        if (a < 0 && q == 1) {
            q = 2;
        } else if (a < 0 && q == 4) {
            q = 3;
        }

        System.out.println(q);

    }
}
