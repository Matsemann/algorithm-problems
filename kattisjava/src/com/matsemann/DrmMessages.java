package com.matsemann;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class DrmMessages {


    static Scanner sc;

    public DrmMessages(String input) {
        int index = input.length() / 2;
        String s1 = input.substring(0, index);
        String s2 = input.substring(index);

        String rotate1 = rotate(s1);
        String rotate2 = rotate(s2);

        String result = rotateBy(rotate1, rotate2);

        System.out.println(result);
    }

    String rotate(String s) {
        int val = stringValue(s);

        int rotateVal = val % 26;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = wrapChar(chars[i], rotateVal);
        }

        return new String(chars);

    }

    String rotateBy(String s1, String s2) {
        char[] chars = s1.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            chars[i] = wrapChar(chars[i], value(s2.charAt(i)));
        }

        return new String(chars);
    }

    char wrapChar(char c, int howMuch) {
        return (char) (((howMuch + value(c)) % 26) + 'A');
    }

    int stringValue(String s) {
        int sum = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            sum += value(c);
        }
        return sum;
    }

    int value(char c) {
        return c - 'A';
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/drmmessages.txt"));

        sc = new Scanner(System.in);

        new DrmMessages(sc.nextLine());
    }
}
