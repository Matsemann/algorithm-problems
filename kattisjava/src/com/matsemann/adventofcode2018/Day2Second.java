package com.matsemann.adventofcode2018;

import java.io.FileInputStream;
import java.util.*;

public class Day2Second {

    static Scanner sc;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("inputs/adventofcode/day2.txt"));
        sc = new Scanner(System.in);

        List<String> words = new ArrayList<>();
        while (sc.hasNext()) {
            words.add(sc.nextLine());
        }

        for (int i = 0; i < words.size() - 1; i++) {
            for (int j = i + 1; j < words.size(); j++) {
                String common = common(words.get(i), words.get(j));
                if (common.length() == words.get(i).length() - 1) {
                    System.out.println(common);
                }
            }
        }

    }

    public static String common(String s1, String s2) {
        String common = "";
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                common += s1.charAt(i);
            }
        }
        return common;
    }
}
