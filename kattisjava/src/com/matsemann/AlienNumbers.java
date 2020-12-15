package com.matsemann;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Created by matskrugersvensson on 27-Sep-17.
 */
public class AlienNumbers {

    static Scanner sc;

    public AlienNumbers(String alienNumber, String sourceLang, String targetLang, int caseNr) {
        String result = solve(alienNumber.toCharArray(), sourceLang, targetLang);
        System.out.println("Case #" + caseNr + ": " + result);
    }

    private String solve(char[] alienNumber, String sourceLang, String targetLang) {
        int alienNumberBase10 = 0;

        int sourceRadix = sourceLang.length();
        for (int i = 0; i < alienNumber.length; i++) {
            char c = alienNumber[i];
            alienNumberBase10 += sourceLang.indexOf(c) * Math.pow(sourceRadix, alienNumber.length - i - 1);
        }

        String result = "";
        int targetRadix = targetLang.length();

        int quot = alienNumberBase10;

        while (true) {
            int sub = quot / targetRadix;
            int remainder = quot - (sub * targetRadix);
            quot = sub;

            result = targetLang.charAt(remainder) + result;
            if (quot == 0) {
                break;
            }
        }

        // 910 01
        // 910 / 2 = 455 + 0
        // 455 / 2 = 227 + 1
        // 227 / 2 = 113 + 1
        // 113 / 2 =  56 + 1
        //  56 / 2 =  28 + 0
        //  28 / 2 =  14 + 0
        //  14 / 2 =   7 + 0
        //   7 / 2 =   3 + 1
        //   3 / 2 =   1 + 1
        //   1 / 2 =   0 + 1

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("inputs/aliennumbers.txt"));
        sc = new Scanner(System.in);

        int cases = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < cases; i++) {
            String[] split = sc.nextLine().split(" ");

            new AlienNumbers(split[0], split[1], split[2], i + 1);
        }
    }
}
