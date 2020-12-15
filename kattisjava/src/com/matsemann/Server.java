package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class Server {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("inputs/server.txt"));
        sc = new Scanner(System.in);

        String[] l1 = sc.nextLine().split(" ");
        int n = Integer.parseInt(l1[0]);
        int T = Integer.parseInt(l1[1]);

        String[] tasks = sc.nextLine().split(" ");

        int c = 0;
        for (int i = 0; i < tasks.length; i++) {
            int length = Integer.parseInt(tasks[i]);

            if (c + length > T) {
                System.out.println(i);
                return;
            }

            c += length;
        }

        System.out.println(n);
    }
}
