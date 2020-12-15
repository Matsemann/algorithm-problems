package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.stream.Stream;

public class EarlyWinter {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("inputs/earlywinter.txt"));

        sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");

        int[] numbers = Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int thisYear = Integer.parseInt(s[1]);

        int years = -1;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] <= thisYear) {
                years = i;
                break;
            }
        }
        if (years == -1) {
            System.out.println("It had never snowed this early!");
        } else {
            System.out.println("It hadn't snowed this early in " + years + " years!");
        }

    }
}
