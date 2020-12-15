package com.matsemann;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class SodaSurpler {


    static Scanner sc;

    public SodaSurpler(int start, int found, int price) {
        int drunk = 0;

        int empty = start + found;

        while (empty >= price) {
            int newBottles = empty / price;
            empty = empty - (newBottles * price);

            drunk += newBottles;
            empty += newBottles;
        }
        System.out.println(drunk);
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/sodasurpler.txt"));

        sc = new Scanner(System.in);

        String[] values = sc.nextLine().split(" ");

        new SodaSurpler(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]));
    }
}
