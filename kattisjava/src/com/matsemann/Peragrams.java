package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class Peragrams {


    static Scanner sc;

    /*
    abcddcbazzvy => 1
    abcd => 3
    abba => 0
    abbba => 0



     */

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/peragrams.txt"));

        sc = new Scanner(System.in);

        String word = sc.nextLine();
        int[] counts = new int[26];

        for (int i = 0; i < word.length(); i++) {
            counts[word.charAt(i) - 'a']++;
        }

        int nrOfOdds = 0;
        for (int c : counts) {
            if (c % 2 == 1) {
                nrOfOdds++;
            }
        }

        int toRemove = nrOfOdds - 1;

        if (toRemove == -1) {
            toRemove = 0;
        }


        System.out.println(toRemove);
    }
}
