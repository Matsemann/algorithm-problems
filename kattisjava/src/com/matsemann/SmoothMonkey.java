package com.matsemann;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class SmoothMonkey {


    static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("inputs/smoothmonkey.txt"));

        sc = new Scanner(System.in);

        double distance = (double) sc.nextInt();
        double smoothie = (double) sc.nextInt();
        int carry = sc.nextInt();


        while (true) {
            int trips = (int) Math.ceil(smoothie / (float) carry);
            int loss = (2 * trips) - 1;


            double diffDst = (smoothie % carry) / (double) loss;

            if (diffDst == 0) {
                diffDst = carry / loss;
            }

            //double diffDst = (double) carry / (double) loss;

            diffDst = Math.min(distance, diffDst);
            double smoothieUsed = diffDst * loss;

            if (smoothieUsed > smoothie) {
                System.out.println("0");
                return;
            }

            smoothie -= smoothieUsed;
            distance -= diffDst;

            if (distance <= 0) {
                System.out.println(String.format("%.10f", smoothie));
                return;
            }
        }



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
