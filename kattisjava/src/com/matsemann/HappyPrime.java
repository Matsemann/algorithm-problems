package com.matsemann;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class HappyPrime {


    static Scanner sc;

    public HappyPrime() {
        String[] in = sc.nextLine().split(" ");
        String caseNr = in[0];

        int nr = parseInt(in[1]);

        boolean prime = isPrime(nr);
        if (prime) {
            if (happy(nr)) {
                System.out.println(caseNr + " " + nr + " YES");
                return;
            }
        }
        System.out.println(caseNr + " " + nr + " NO");

    }

    boolean happy(int nr) {
        Set<Integer> seenNrs = new HashSet<>();
        seenNrs.add(nr);

        while (true) {
            nr = squareNrs(nr);
            if (nr == 1) {
                return true;
            }
            if (seenNrs.contains(nr)) {
                return false;
            }
            seenNrs.add(nr);
        }

    }

    int squareNrs(int nr) {
        String nrs = "" + nr;
        int sum = 0;
        for (int i = 0; i < nrs.length(); i++) {
            int siffer = parseInt(nrs.charAt(i) + "");
            sum += siffer * siffer;
        }
        return sum;
    }

    boolean isPrime(int nr) {
        if (nr == 1) {
            return false;
        }
        int upper = (int) Math.sqrt(nr);

        for (int i = 2; i < upper+1; i++) {
            if (nr % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/happyprime.txt"));
        sc = new Scanner(System.in);

        int cases = parseInt(sc.nextLine());
        while (cases --> 0) {
            new HappyPrime();
        }
    }
}
