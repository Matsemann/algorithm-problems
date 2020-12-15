package com.matsemann;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ReverseBinary {


    static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("inputs/reversebinary.txt"));

        sc = new Scanner(System.in);

        int nr = Integer.parseInt(sc.nextLine());

        String binS = "";


        for (int i = 32; i >= 0; i--) {
            double pow = Math.pow(2, i);
            if (nr >= pow) {
                binS += "1";
                nr -= pow;
            } else {
                binS += "0";
            }
        }

        String reversed = new StringBuilder(binS.replaceAll("^0+", "")).reverse().toString();

        int result = Integer.parseInt(reversed, 2);

        System.out.println(result);
    }
}
