package com.matsemann;

import java.util.Scanner;

public class IsItHalloween {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/bijele.txt"));

        sc = new Scanner(System.in);

        System.out.println(doStuff(sc.nextLine()));
    }

    public static String doStuff(String in) {
        if (in.equals("OCT 31") || in.equals("DEC 25")) {
            return "yup";
        } else {
            return "nope";
        }

    }
}
