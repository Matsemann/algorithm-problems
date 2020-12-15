package com.matsemann;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DartScores {


    static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("inputs/dart.txt"));

        sc = new Scanner(System.in);

        int target = Integer.parseInt(sc.nextLine());

        for (int i = 3; i > 0; i--) {
            for (int j = 20; j > 0; j--) {

                int result = i * j;
                if (result == target) {
                    System.out.println(nrToStr(i) + " " + j);
                    return;
                }

                for (int k = 3; k > 0; k--) {
                    for (int l = 20; l > 0; l--) {

                        int result2 = i * j + k * l;
                        if (result2 == target) {
                            System.out.println(nrToStr(i) + " " + j);
                            System.out.println(nrToStr(k) + " " + l);
                            return;
                        }

                        for (int m = 3; m > 0; m--) {
                            for (int n = 20; n > 0; n--) {

                                int result3 = i * j + k * l + m * n;
                                if (result3 == target) {
                                    System.out.println(nrToStr(i) + " " + j);
                                    System.out.println(nrToStr(k) + " " + l);
                                    System.out.println(nrToStr(m) + " " + n);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("impossible");

    }

    private static String nrToStr(int nr) {
        switch (nr) {
            case 1:
                return "single";
            case 2:
                return "double";
            case 3:
                return "triple";
            default:
                return "";
        }
    }
}
