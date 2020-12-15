package com.matsemann;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class ParsingHex {


    static Scanner sc;

    public ParsingHex() {
        List<String> lines = new ArrayList<>();
        List<String> nrs = new ArrayList<>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        Pattern pattern = Pattern.compile("(0[xX][0-9a-fA-F]{1,8})");
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                nrs.add(matcher.group());
            }
        }

        for (String nr : nrs) {
            System.out.println(nr + " " + hex2decimal(nr.substring(2)));
        }

    }

    public static BigInteger hex2decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        BigInteger val = BigInteger.valueOf(0);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = val.multiply(BigInteger.valueOf(16));
            val = val.add(BigInteger.valueOf(d));
        }
        return val;
    }
    public static int hex2decimalInt(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;


        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/parsinghex.txt"));
        sc = new Scanner(System.in);

        new ParsingHex();
    }
}
