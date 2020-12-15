package com.matsemann;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ReverseRot {


    static Scanner sc;

    public ReverseRot(String line) {
        String[] split = line.split(" ");
        int rot = parseInt(split[0]);
        char[] chars = split[1].toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '_') {
                chars[i] = '[';
            } else if (chars[i] == '.') {
                chars[i] = '\\';
            }
        }

        for (int i = 0; i < chars.length; i++) {
            chars[i] = rot(chars[i], rot);
        }

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '[') {
                chars[i] = '_';
            } else if (chars[i] == '\\') {
                chars[i] = '.';
            }
        }


        String s = new String(chars);
        s = new StringBuilder(s).reverse().toString();
        System.out.println(s);

    }

    private char rot(char c, int rot) {
        char i = (char) (c - 'A');
        char i1 = (char) (i + rot);
        char newChar = (char) ((i1 % 28) + 'A');
        return newChar;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/reverserot.txt"));
        sc = new Scanner(System.in);

        while (true) {
            String line = sc.nextLine();
            if (line.equals("0")) {
                break;
            }

            new ReverseRot(line);
        }
    }
}
