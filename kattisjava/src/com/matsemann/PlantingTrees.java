package com.matsemann;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;


public class PlantingTrees {


    static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("inputs/plantingtrees.txt"));

        sc = new Scanner(System.in);
        sc.nextLine();
        List<Integer> seeds = Arrays.stream(sc.nextLine().split(" "))
                .map(Integer::parseInt)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        int max = 0;
        for (int i = 0; i < seeds.size(); i++) {
            Integer s = seeds.get(i);

            if (s + i + 1 > max) {
                max = s + i + 1;
            }

        }

        System.out.println(max + 1);

    }
}
