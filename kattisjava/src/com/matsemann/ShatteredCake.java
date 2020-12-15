package com.matsemann;

import java.util.Scanner;

public class ShatteredCake {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);

        int W = Integer.parseInt(sc.nextLine());
        int numPieces = Integer.parseInt(sc.nextLine());

        int area = 0;

        while (numPieces-- > 0) {
            String[] ins = sc.nextLine().split(" ");
            int w = Integer.parseInt(ins[0]);
            int l = Integer.parseInt(ins[1]);

            area += w * l;
        }

        System.out.println(area / W);
    }


}
