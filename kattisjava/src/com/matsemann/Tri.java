package com.matsemann;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


public class Tri {


    static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("inputs/pizzahawaii.txt"));

        sc = new Scanner(System.in);
        int nr1 = sc.nextInt();
        int nr2 = sc.nextInt();
        int nr3 = sc.nextInt();

        if (nr1 + nr2 == nr3) {
            System.out.println(nr1 + "+" + nr2 + "=" + nr3);
        } else if (nr1 - nr2 == nr3) {
            System.out.println(nr1 + "-" + nr2 + "=" + nr3);
        } else if (nr1 * nr2 == nr3) {
            System.out.println(nr1 + "*" + nr2 + "=" + nr3);
        } else if (nr1 / nr2 == nr3) {
            System.out.println(nr1 + "/" + nr2 + "=" + nr3);
        } else if (nr1 == nr2 + nr3) {
            System.out.println(nr1 + "=" + nr2 + "+" + nr3);
        } else if (nr1 == nr2 - nr3) {
            System.out.println(nr1 + "=" + nr2 + "-" + nr3);
        } else if (nr1 == nr2 * nr3) {
            System.out.println(nr1 + "=" + nr2 + "*" + nr3);
        } else if (nr1 == nr2 / nr3) {
            System.out.println(nr1 + "=" + nr2 + "/" + nr3);
        }

    }

}
