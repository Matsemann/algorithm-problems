package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Autori {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);

        String input = sc.nextLine();

        Stream.of(input.split("-"))
                .map(e -> e.substring(0, 1))
                .forEach(System.out::print);
    }
}
