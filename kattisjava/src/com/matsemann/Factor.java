package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class Factor {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/factor.txt"));

        sc = new Scanner(System.in);

        System.out.println(sc.nextInt() * (sc.nextInt() - 1) + 1);
    }
}
