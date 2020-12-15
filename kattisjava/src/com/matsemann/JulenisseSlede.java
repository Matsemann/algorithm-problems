package com.matsemann;

import java.util.Arrays;
import java.util.Random;

public class JulenisseSlede {

    static final int capacity = 1000;

    public static void main(String[] args) throws Exception {

        int[] in3 = new int[]{550, 400, 900, 110, 890};
        int[] in1 = new int[]{550, 450, 900, 110};
        int[] in2 = new int[]{550, 450, 900, 100};
        int[] in4 = new int[]{100, 200, 300, 400, 500, 600, 700, 800, 900, 200};
/*
        System.out.println(solve(in1) + " = 3");
        System.out.println(solve(in2) + " = 2");
        System.out.println(solve(in3) + " = 3");
        System.out.println(solve(in4) + " = 7");
*/
        int[] random = random();
        System.out.println(Arrays.toString(random));
        System.out.println(solve(random));
    }

    static int[] random() {
        int seed = new Random().nextInt();
        seed = 1982687677;
        System.out.println(seed);
        Random random = new Random(seed);

        int[] weights = new int[200];

        for (int i = 0; i < weights.length; i++) {
            weights[i] = random.nextInt(100) * 10;
        }

        return weights;
    }

    static int solve(int[] weights) {
        int loads = 1;
        int sum = 0;

        for (int weight : weights) {
            if (sum + weight > capacity) {
                loads++;
                sum = weight;
            } else {
                sum += weight;
            }
        }

        return loads;
    }

}
