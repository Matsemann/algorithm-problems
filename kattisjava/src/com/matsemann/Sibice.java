package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class Sibice {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/sibice.txt"));

        sc = new Scanner(System.in);

        int nr = sc.nextInt();
        int w = sc.nextInt();
        int h = sc.nextInt();

        double size = Math.sqrt(h * h + w * w);

        while (nr-- > 0) {
            System.out.println(sc.nextInt() <= size ? "DA" : "NE");
        }
    }
}
