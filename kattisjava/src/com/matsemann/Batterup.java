package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Batterup {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/batterup.txt"));

        sc = new Scanner(System.in);

        int nr = parseInt(sc.nextLine());

        int count = 0;
        int sum = 0;

        while (nr-- > 0) {
            int i = sc.nextInt();
            if (i != -1) {
                count++;
                sum += i;
            }
        }

        System.out.println((float) sum / count);
    }
}
