package com.matsemann;

import java.util.Scanner;

public class Rainfall2 {

    static Scanner sc;

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);

        String[] nrs = sc.nextLine().split(" ");
        double leakHeightMm = Double.parseDouble(nrs[0]);
        double leakMmH = Double.parseDouble(nrs[1]);
        double rainH = Double.parseDouble(nrs[2]);
        double checkH = Double.parseDouble(nrs[3]);
        double waterMm = Double.parseDouble(nrs[4]);

        double min, max;

        /*
        max må ta hensyn til at det lekker mens det regner
        og når det faktisk nådde over kanten

        max-max må jo være at det har lekket full guffe fra start
        så 2 + 1,5 = 3,5h, *0,5k = 1,75 mm
        så max max = 81,75 mm

        men med 81,75 over 2 timer, nådde man L 80 etter

        81,75 / 2 = 40,875 mm/h
        80 / 40,875 = 1,9571865 h

        så da har det bare rekt lekke
        ((2 - 1,9571865) + 1,5) * 0,5 = 0,77140
        så 81,75 - 0,77140 = 80,97859 som er != 80
---
        måler 90
        90,869618 over 2 timer
        90,869618 / 2 = 45,434809 mm/h
        80 / 45,434809 = 1,760764 h
        så har da rekt å lekke
        (( 2 - 1,760764) + 1,5) * 0,5 = 0,869618 så stemmer jo
---
    100 leak, 429,813872
    / 2 = 214,906936
    80 / det = 0,372254, 2 det er 1,627745
    (det + 1,5) * 100 = 312,7746
---
        (2 - (80 / (x / 2)) + 1,5) * 0,5 = x - 80

        (T1 - (L / (x / T1)) + T2) * K = x - L


        x^2 + 80 = 81,75x
        x^2 - 81,75x + 80 = 0

        a = 1
        b = 80 + (2 + 1,5) * 0,5
        c = 80

        -b +- sqrt(b^2 - 4ac)
        ---------------------
               2 a


        en max er også at det vært masse men rukket lekke ut ned til leakheight
        så max = watermm + checkH * leakMmH
        eller er det dekket av det over? for da har det jo egentlig regnet enda mer

         */
/*
        double a = 1.0;
        double b = -(leakHeightMm + (rainH + checkH) * leakMmH);
        double c = leakHeightMm;

        double sqrt = Math.sqrt(b * b - 4 * a * c);
        double x = (-b + sqrt) / (2 * a);
*/
        double a = 1.0;
        double b = -(waterMm + (rainH + checkH) * leakMmH);
//        double c = leakHeightMm;
        double c = rainH * leakHeightMm * leakMmH;
        /*
        0.5     =>  80  = 1 * 80
        1       => 160  = 2 * 80
        2       => 320  = 4 * 80
        3       => 480  = 6 * 80

        c skulle vært 15 000, er 300

        0,5x = 80
        1x = 160
        2x = 320
        3x = 480

        80 * k^2

         */

        double sqrt = Math.sqrt(b * b - 4 * a * c);
        double x = (-b + sqrt) / (2 * a);
//        System.out.println(x);

        if (waterMm < leakHeightMm) {
            min = waterMm;
            max = waterMm;
        } else if (waterMm <= leakHeightMm) {
            min = waterMm;
            max = x;
        } else {
            min = x;
            max = x; // todo samme men med waterheight?
            // hva om renner ut kjemperaskt og regner sakte?
        }

        System.out.println(String.format("%.8f", min) + " " + String.format("%.8f", max));
    }
}
