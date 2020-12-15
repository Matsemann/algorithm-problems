package com.matsemann;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.Scanner;

public class HillNumbers {

    static Scanner sc;

    public HillNumbers(BigInteger number) {
        if (!isHillNumber(number)) {
            System.out.println("-1");
            return;
        }

        int numberOfHills = 1;

        while (true) {
            number = number.subtract(BigInteger.ONE);
            if (number.equals(BigInteger.ZERO)) {
                break;
            }
            numberOfHills += isHillNumber(number) ? 1 : 0;
        }

        System.out.println(numberOfHills);
    }

    private boolean isHillNumber(BigInteger number) {
        String str = number.toString();

        boolean hasFallen = false;

        char last = '0';

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < last) {
                hasFallen = true;
            } else if (hasFallen && c > last) {
                return false;
            }
            last = c;
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("inputs/hillnumbers.txt"));

        sc = new Scanner(System.in);

        BigInteger number = new BigInteger(sc.nextLine());

        new HillNumbers(number);

        // lagre rising/falling/lik/ikke per

        // for et tall, se på første siffer og finn oppslaget for resten, x + bcd
        // f. eks. 5123 -> 5 + 123

        // om bcd ikke er hill, er denne heller ikke hill
        // om bcd er rising, og x <= b, er denne rising og +er på 1, eks 5+567
        // om bcd er rising, og x > b, er denne ikke hill, eks 6+567
        // om bcd er falling, og x => b, er denne falling og +er på 1, eks 3+321
        // om bcd er falling, og x < b, er denne rising og +er på 1, eks 2+321
        // definerer 1111 som falling? 5+333 er ok og falling, 2+3333 er ok og rising

        // 2 + 321
        // hva med 5 + 12321


        // hmm eller hva med
        //     []
        //   [][]
        // [][][]
        // og telle opp per siffer antall rising/falling, og øke telleren?

/*
        System.out.println();
        HillNumbers hillNumbers = new HillNumbers(BigInteger.ONE);

        for (int i = 0; i < 1200; i++) {
            System.out.println(i + "\t" + hillNumbers.isHillNumber(BigInteger.valueOf(i)));
        }*/
    }
}
