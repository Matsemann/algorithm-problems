package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class Ladder {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("inputs/ladder.txt"));
        sc = new Scanner(System.in);


        int h = sc.nextInt();
        int v = sc.nextInt();

        System.out.println((int) Math.ceil(h / Math.sin(Math.toRadians(v))));

    }
}
