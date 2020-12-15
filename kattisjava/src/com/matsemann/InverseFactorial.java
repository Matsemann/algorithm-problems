package com.matsemann;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class InverseFactorial {
    static Scanner sc;


    public InverseFactorial(BigInteger nr) {
        BigInteger factor = BigInteger.valueOf(2);
        BigInteger fac = BigInteger.ONE;

        while (true) {
            fac = fac.multiply(factor);
            if (fac.equals(nr)) {
                System.out.println(factor);
                return;
            }
            factor = factor.add(BigInteger.ONE);
        }

       /* int factor = 2;
        int sum = 1;

        while ((sum *= factor++) != nr);
        System.out.println(--factor);*/
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("inputs/inversefactor.txt"));

        sc = new Scanner(System.in);
        String nr = sc.nextLine();

        //factorial(100000);
        BigInteger bigInt = new BigInteger(nr);
        new InverseFactorial(bigInt);
    }

    static void factorial(int nr) {
        BigInteger val = BigInteger.ONE;

        for (int i = 2; i <= nr; i++) {
            val = val.multiply(BigInteger.valueOf(i));
        }
        System.out.println(val);

    }
}
