package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class SantaKlas {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("inputs/santaklas.txt"));

        sc = new Scanner(System.in);

        int H = sc.nextInt();
        int v = sc.nextInt();

        if (v >= 0 && v <= 180) {
            System.out.println("safe");
        } else {
            double B;
            if (v <= 270) {
                B = (v - 180);
            } else {
                B = 360 - v;
            }

//            if (v == 0) {
//                System.out.println(H);
//                return;
//            }


            double c = (Math.sin(Math.toRadians(90)) * H) / Math.sin(Math.toRadians(B));
            System.out.println((int)Math.floor(c));
        }

    }
}
