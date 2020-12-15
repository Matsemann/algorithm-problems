package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class NastyHacks {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/nastyhacks.txt"));

        sc = new Scanner(System.in);

        int cases = sc.nextInt();

        while (cases-- > 0) {
            int r = sc.nextInt();
            int e = sc.nextInt();
            int c = sc.nextInt();

            int profit = e - c;

            if (r > profit) {
                System.out.println("do not advertise");
            } else if (r == profit) {
                System.out.println("does not matter");
            } else {
                System.out.println("advertise");
            }
        }
    }
}
