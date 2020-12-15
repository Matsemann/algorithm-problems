package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class SimonSays {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/simonsays.txt"));

        sc = new Scanner(System.in);

        int cases = Integer.parseInt(sc.nextLine());
        while (cases --> 0) {
            String commando = sc.nextLine();
            if (commando.startsWith("Simon says ")) {
                System.out.println(commando.substring(11));
            }
        }
    }
}
