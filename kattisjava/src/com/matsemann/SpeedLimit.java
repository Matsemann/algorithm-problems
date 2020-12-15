package com.matsemann;

import java.io.FileInputStream;
import java.util.Scanner;

public class SpeedLimit {


    static Scanner sc;

    public SpeedLimit(int nrOfLogs) {

        int distance = 0;
        int lastTime = 0;

        for (int i = 0; i < nrOfLogs; i++) {
            String[] line = sc.nextLine().split(" ");
            int speed = Integer.parseInt(line[0]);
            int time = Integer.parseInt(line[1]);

            distance += speed * (time - lastTime);
            lastTime = time;
        }

        System.out.println(distance + " miles");
    }


    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("inputs/speedlimit.txt"));

        sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine());

        while (cases != -1) {
            new SpeedLimit(cases);
            cases = Integer.parseInt(sc.nextLine());
        }
    }
}
