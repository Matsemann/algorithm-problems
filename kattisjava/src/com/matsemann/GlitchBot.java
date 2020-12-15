package com.matsemann;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.matsemann.GlitchBot.Dir.*;
import static java.util.Arrays.asList;

public class GlitchBot {


    static Scanner sc;

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);

        String[] ins = sc.nextLine().split(" ");
        int goalX = Integer.parseInt(ins[0]);
        int goalY = Integer.parseInt(ins[1]);

        int numInstructions = Integer.parseInt(sc.nextLine());

        String[] instructions = new String[numInstructions];
        for (int i = 0; i < numInstructions; i++) {
            instructions[i] = sc.nextLine();
        }

        List<String> validInstructions = asList("Forward", "Left", "Right");

        for (int i = 0; i < numInstructions; i++) {
            String originalInstruction = instructions[i];

            for (String validInstruction : validInstructions) {
                if (validInstruction.equals(originalInstruction)) {
                    continue;
                }

                String[] clone = instructions.clone();
                clone[i] = validInstruction;

                if (run(clone, goalX, goalY)) {
                    System.out.println((i+1) + " " + validInstruction);
                    return;
                }
            }

        }
    }

    enum Dir {
        UP, LEFT, DOWN, RIGHT
    }

    private static boolean run(String[] instructions, int goalX, int goalY) {
        Dir dir = UP;
        int x = 0,y = 0;

        for (String instruction : instructions) {
            if (instruction.equals("Forward")) {
                if (dir == UP) {
                    y += 1;
                } else if (dir == DOWN) {
                    y -= 1;
                } else if (dir == LEFT) {
                    x -= 1;
                } else {
                    x += 1;
                }
            } else if (instruction.equals("Left")) {
                if (dir == UP) {
                    dir = LEFT;
                } else if (dir == LEFT) {
                    dir = DOWN;
                } else if (dir == DOWN) {
                    dir = RIGHT;
                } else {
                    dir = UP;
                }
            } else if (instruction.equals("Right")) {
                if (dir == UP) {
                    dir = RIGHT;
                } else if (dir == RIGHT) {
                    dir = DOWN;
                } else if (dir == DOWN) {
                    dir = LEFT;
                } else {
                    dir = UP;
                }
            }
        }

        return (x == goalX && y == goalY);
    }


}
