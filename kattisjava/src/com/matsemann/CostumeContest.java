package com.matsemann;

import java.util.*;

public class CostumeContest {

    static Scanner sc;

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);

        int numCases = Integer.parseInt(sc.nextLine());

        Map<String, Integer> cases = new HashMap<>();

        while (numCases-- > 0) {
            String costume = sc.nextLine();
            cases.compute(costume, (k, v) -> {if (v == null) return 1; else return v + 1;});
        }

        int smallest = cases.values().stream().mapToInt(v -> v).min().getAsInt();

        List<String> results = new ArrayList<>();

        cases.forEach((k, v) -> {
            if (v == smallest) {
                results.add(k);
            }
        });

        Collections.sort(results);

        results.stream().forEach(r -> System.out.println(r));

    }
}
