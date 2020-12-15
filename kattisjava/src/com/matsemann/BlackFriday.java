package com.matsemann;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class BlackFriday {

    static Scanner sc;

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);

        sc.nextLine();

        List<Integer> rolls = asList(sc.nextLine().split(" "))
                .stream()
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        Map<Integer, Long> counts = rolls.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        final long[] largestUnique = {-1};

        counts.forEach((roll, count) -> {
            if (count == 1 && roll > largestUnique[0]) {
                largestUnique[0] = roll;
            }
        });

        if (largestUnique[0] == -1) {
            System.out.println("none");
        } else {
            System.out.println(rolls.indexOf((int)largestUnique[0]) + 1);
        }

    }
}
