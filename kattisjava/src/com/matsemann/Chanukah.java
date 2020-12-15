package com.matsemann;

import java.util.*;

public class Chanukah {

    static Scanner sc;

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);

        int numCases = Integer.parseInt(sc.nextLine());

        while (numCases-- > 0) {
            String[] s = sc.nextLine().split(" ");
            int index = Integer.parseInt(s[0]);
            int days = Integer.parseInt(s[1]);

            int sum = 0;
            for (int i = 1; i <= days; i++) {
                sum += i + 1;
            }

            System.out.println(index + " " + sum);
        }


    }
}
