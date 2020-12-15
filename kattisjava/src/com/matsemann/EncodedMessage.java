package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class EncodedMessage {


    static Scanner sc;

    public EncodedMessage(String encoded) {
        int square = (int) Math.sqrt(encoded.length());


        for (int j = square - 1; j >= 0; j--) {
            for (int i = 0; i < square; i++) {
                int index = i * square + j;
                System.out.print(encoded.charAt(index));
            }
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/encodedmessage.txt"));

        sc = new Scanner(System.in);

        int nr = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < nr; i++) {
            new EncodedMessage(sc.nextLine());
        }

    }
}
