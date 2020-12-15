package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class Parking {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("inputs/parking.txt"));
        sc = new Scanner(System.in);

        int[] prices = {0, sc.nextInt(), sc.nextInt(), sc.nextInt()};

        int[] truck1 = {sc.nextInt(), sc.nextInt()};
        int[] truck2 = {sc.nextInt(), sc.nextInt()};
        int[] truck3 = {sc.nextInt(), sc.nextInt()};

        int price = 0;

        for (int i = 0; i <= 100; i++) {
            int parked = 0;
            if (isParked(truck1, i)) parked++;
            if (isParked(truck2, i)) parked++;
            if (isParked(truck3, i)) parked++;

            price += prices[parked] * parked;

        }

        System.out.println(price);
    }

    static boolean isParked(int[] truck, int i) {
        return (i >= truck[0] && i < truck[1]);
    }
}
