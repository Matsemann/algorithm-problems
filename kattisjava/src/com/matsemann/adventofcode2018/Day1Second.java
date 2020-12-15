package com.matsemann.adventofcode2018;

import java.io.FileInputStream;
import java.util.*;

public class Day1Second {

    static Scanner sc;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("inputs/adventofcode/day1_test.txt"));
        sc = new Scanner(System.in);

        Set<Integer> seen = new HashSet<>();

        List<Integer> nrs = new ArrayList<>();

        while (sc.hasNext()) {
            nrs.add(sc.nextInt());
        }

        int nr = 0;
        int counter = 0;

        while (!seen.contains(nr)) {
            seen.add(nr);
            nr += nrs.get(counter);
            counter = (counter + 1) % nrs.size();
        }

        System.out.println(nr);

    }
}
