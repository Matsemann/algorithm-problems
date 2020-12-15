package com.matsemann;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class NoDuplicates {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/noduplicates.txt"));

        sc = new Scanner(System.in);

        List<String> words = asList(sc.nextLine().split(" "));

        HashSet<String> wordSet = new HashSet<>(words);

        if (words.size() == wordSet.size()) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
