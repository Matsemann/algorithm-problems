package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class R2 {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/r2.txt"));

        sc = new Scanner(System.in);

        int s = sc.nextInt();
        int r = sc.nextInt();

        int diff = r - s;

        System.out.println(r + diff);

    }
}
