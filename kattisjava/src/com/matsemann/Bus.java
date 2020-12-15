package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Bus {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/bus.txt"));
        sc = new Scanner(System.in);

        int cases = parseInt(sc.nextLine());
        while (cases --> 0) {
            System.out.println((2 << parseInt(sc.nextLine()) - 1) - 1);
        }
    }
}
