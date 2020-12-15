package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class FizzBuzz {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("inputs/fizzbuzz.txt"));
        sc = new Scanner(System.in);


        int x = sc.nextInt();
        int y = sc.nextInt();
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            if (i % x == 0) {
                System.out.print("Fizz");
            }
            if (i % y == 0) {
                System.out.print("Buzz");
            }

            if (!(i % x == 0 || i % y == 0)) {
                System.out.print(i);
            }

            System.out.println();


        }

    }
}
