package com.matsemann;

import java.util.Arrays;
import java.util.Scanner;

public class GrassSeed {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);

        double cost = Double.parseDouble(sc.nextLine());

        int lawns = Integer.parseInt(sc.nextLine());

        double price = 0;

        while (lawns-- > 0) {
            String[] ins = sc.nextLine().split(" ");
            double l = Double.parseDouble(ins[0]);
            double w = Double.parseDouble(ins[1]);

            price += (l * w) * cost;
        }

        System.out.printf("%.8f", price);


    }
}
