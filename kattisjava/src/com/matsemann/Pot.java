package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Pot {


    static Scanner sc;

    public Pot(int nrs) {
        int sum = 0;
        while (nrs-- > 0) {
            String line = sc.nextLine();
            int number = parseInt(line.substring(0, line.length() - 1));
            int pow = parseInt(line.substring(line.length() - 1));
            sum += Math.pow(number, pow);
        }
        System.out.println(sum);
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/pot.txt"));

        sc = new Scanner(System.in);

        new Pot(parseInt(sc.nextLine()));
    }
}
