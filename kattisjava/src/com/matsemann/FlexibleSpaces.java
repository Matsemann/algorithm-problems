package com.matsemann;

import java.io.FileInputStream;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class FlexibleSpaces {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("inputs/flexiblespaces.txt"));
        sc = new Scanner(System.in);

        int W = sc.nextInt();
        int p = sc.nextInt();
        sc.nextLine();

        List<Integer> ps = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(toList());

        ps.add(0, 0);
        ps.add(W);

        Set<Integer> spaces = new HashSet<>();


        for (int i = 0; i < ps.size(); i++) {
            for (int j = i + 1; j < ps.size(); j++) {
                spaces.add(ps.get(j) - ps.get(i));
            }
        }

        Object[] objects = spaces.toArray();
        Arrays.sort(objects);
        for (int i = 0; i < objects.length; i++) {
            Object object = objects[i];
            System.out.print(object);
            if (i + 1 < objects.length) {
                System.out.print(" ");
            }
        }
    }

}
