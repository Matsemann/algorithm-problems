package com.matsemann;

import java.util.Scanner;

public class RunlengthEncodingRun {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);

        String[] in = sc.nextLine().split(" ");

        if (in[0].equals("E")) {
            encode(in[1]);
        } else {
            decode(in[1]);
        }
    }

    private static void encode(String s) {

        char current = s.charAt(0);
        int currentcount = 1;
        String encoded = "";

        for (int i = 1; i < s.length() + 1; i++) {
            if (i < s.length() && s.charAt(i) == current) {
                currentcount++;
                continue;
            } else {
                encoded += current + ("" + currentcount);
                if (i < s.length()) {
                    currentcount = 1;
                    current = s.charAt(i);
                }
            }
        }

        System.out.println(encoded);
    }

    private static void decode(String s) {
        String decoded = "";

        for (int i = 0; i < s.length(); i++) {
            try {
                int num = Integer.parseInt(s.charAt(i) + "");
                char c = s.charAt(i - 1);
                for (int j = 0; j < num; j++) {
                    decoded += c;
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        System.out.println(decoded);
    }
}
