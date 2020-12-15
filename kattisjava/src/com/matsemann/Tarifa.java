package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class Tarifa {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/tarifa.txt"));

        sc = new Scanner(System.in);

        int dataLimit = Integer.parseInt(sc.nextLine());
        int months = Integer.parseInt(sc.nextLine());

        int rest = 0;

        while (months-- > 0) {
            int use = Integer.parseInt(sc.nextLine());
            rest += dataLimit - use;
        }

        System.out.println(rest + dataLimit);

    }
}
