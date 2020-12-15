package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class T9Spelling {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("inputs/t9spelling.txt"));
        sc = new Scanner(System.in);

        int cases = Integer.parseInt(sc.nextLine());
        int c = 1;
        while (cases-- > 0) {
            t9(c++);
        }

    }

    static void t9(int casenr) {
        String[] letters = {
                "2",
                "22",
                "222",
                "3",
                "33",
                "333",
                "4",
                "44",
                "444",
                "5",
                "55",
                "555",
                "6",
                "66",
                "666",
                "7",
                "77",
                "777",
                "7777",
                "8",
                "88",
                "888",
                "9",
                "99",
                "999",
                "9999",
                "0"
        };

        StringBuffer sb = new StringBuffer();

        String word = sc.nextLine();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int pos;
            if (c == ' ') {
                pos = letters.length - 1;
            } else {
                pos = c - 'a';
            }
            String ap = letters[pos];
            if (sb.length() > 0 && ap.charAt(0) == sb.charAt(sb.length() - 1)) {
                sb.append(" ");
            }
            sb.append(ap);
        }

        System.out.println("Case #" + casenr + ": " + sb.toString());
    }


}
