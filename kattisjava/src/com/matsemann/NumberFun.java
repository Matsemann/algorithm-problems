package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class NumberFun {


    static Scanner sc;


    public NumberFun() {
        String[] nrs = sc.nextLine().split(" ");
        int val1 = Integer.parseInt(nrs[0]);
        int val2 = Integer.parseInt(nrs[1]);
        int val3 = Integer.parseInt(nrs[2]);

        if (val1 + val2 == val3 || val1 - val2 == val3 || val2 - val1 == val3
                || val1 * val2 == val3 || (val2 != 0 && (float)val1 / val2 == val3) || (val1 != 0 && (float)val2 / val1 == val3)) {
            System.out.println("Possible");
        } else {
            System.out.println("Impossible");
        }

    }



    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/numberfun.txt"));

        sc = new Scanner(System.in);

        int cases = Integer.parseInt(sc.nextLine());

        while (cases-- > 0) {
            new NumberFun();
        }

    }
}
