package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Aaah {

    static Scanner sc;

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        if (s1.length() >= s2.length()) {
            System.out.println("go");
        } else {
            System.out.println("no");
        }
    }
}
