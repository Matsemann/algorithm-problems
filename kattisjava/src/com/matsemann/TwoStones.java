package com.matsemann;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class TwoStones {


    static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("inputs/dart.txt"));

        sc = new Scanner(System.in);

        int stones = Integer.parseInt(sc.nextLine());

        if (stones % 2 == 0) {
            System.out.println("Bob");
        } else {
            System.out.println("Alice");
        }

    }
}
