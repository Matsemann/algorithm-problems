package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class HissingMicrophone {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("inputs/hissing.txt"));
        sc = new Scanner(System.in);

        String string = sc.nextLine();
        if (string.contains("ss")) {
            System.out.println("hiss");
        } else {
            System.out.println("no hiss");
        }
    }
}
