package com.matsemann.adventofcode2018;

import java.io.FileInputStream;
import java.util.*;

public class Day2 {

    static Scanner sc;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("inputs/adventofcode/day2.txt"));
        sc = new Scanner(System.in);

        List<String> words = new ArrayList<>();
        while (sc.hasNext()) {
            words.add(sc.nextLine());
        }

        long twos = words.stream().map(Day2::frequencies).filter(f -> f.values().contains(2)).count();
        long threes = words.stream().map(Day2::frequencies).filter(f -> f.values().contains(3)).count();

        System.out.println(twos * threes);
    }

    public static Map<String, Integer> frequencies(String word) {
        Map<String, Integer> freq = new HashMap<>();

        for (String letter : word.split("")) {
            freq.put(letter, freq.getOrDefault(letter, 0) + 1);
        }

        return freq;
    }
}
