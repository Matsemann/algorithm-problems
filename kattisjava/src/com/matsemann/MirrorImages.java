package com.matsemann;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MirrorImages {


    static Scanner sc;


    public MirrorImages(int caseNr) {
        int rows = sc.nextInt();
        sc.nextLine();

        List<String> lines = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            lines.add(sc.nextLine());
        }

        Collections.reverse(lines);

        System.out.println("Test " + caseNr);
        for (String line : lines) {
            System.out.println(new StringBuilder(line).reverse());
        }
    }


    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/mirrorimages.txt"));

        sc = new Scanner(System.in);

        int cases = sc.nextInt();
        int test = 1;
        while (cases --> 0) {
            new MirrorImages(test++);
        }
    }
}
