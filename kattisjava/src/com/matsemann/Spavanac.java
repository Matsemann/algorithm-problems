package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class Spavanac {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/spavanac.txt"));

        sc = new Scanner(System.in);

        int h = sc.nextInt();
        int mm = sc.nextInt();

        if (mm < 45) {
            h = (h - 1 + 24) % 24;
        }

        mm = (mm - 45 + 60) % 60;

        System.out.println(h + " " + mm);

    }
}
