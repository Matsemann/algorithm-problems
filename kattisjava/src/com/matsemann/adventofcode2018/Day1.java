package com.matsemann.adventofcode2018;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {

    static Scanner sc;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("inputs/adventofcode/day1.txt"));
        sc = new Scanner(System.in);

        int nr = 0;

        while (sc.hasNext()) {
            nr += sc.nextInt();
        }

        System.out.println(nr);

    }
}
