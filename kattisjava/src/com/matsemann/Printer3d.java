package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class Printer3d {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("inputs/printer3d.txt"));
        sc = new Scanner(System.in);

        int figures = sc.nextInt();
        int maxDays = figures;

        for (int i = 0; i < figures; i++) {
            int totalPrinters = (int) Math.pow(2, i);
            int daysLeft = (int) Math.ceil(( (float)figures / totalPrinters));
            int totalDays = daysLeft + i;

            if (totalDays < maxDays) {
                maxDays = totalDays;
            }
        }
        System.out.println(maxDays);

    }


}
