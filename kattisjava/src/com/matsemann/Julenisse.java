package com.matsemann;

public class Julenisse {



    public static void main(String[] args) throws Exception {

        int[] blocks = new int[]{111, 222, 333, 444};

        //find();

        //int[] d = decode(new int[]{249, 98, 13, 237});
        int[] d = decode(new int[]{159, 144, 64, 97});
        System.out.println("[" + d[0] + ", " + d[1] + ", " + d[2] + ", " + d[3] +"]");
        //d = decode(new int[]{255, 255, 255, 255});
        //System.out.println("[" + d[0] + ", " + d[1] + ", " + d[2] + ", " + d[3] +"]");
        //d = decode(new int[]{255, 255, 255, 255});


    }

    public static void find() {
        long sum = 0;

        for (int i = 224; i < 256; i++) {

            for (int j = 0; j < 256; j++) {


                for (int k = 0; k < 256; k++) {


                    for (int l = 95; l < 98; l++) {
                        int[] d = decode(new int[]{i, j, k, l});
                        sum++;
                        if (d[0] == 192 && d[1] == 168 && d[2] == 1 && d[3] == 1) {
                            System.out.println("[" + i + ", " + j + ", " + k + ", " + l +"]");
                            return;
                        }
                    }
                }
            }
            System.out.println(i + "\t" + sum);
        }

        System.out.println("ingen match");
    }
// gave = 35;
    public static int[] decode(int[] blocks) {

        for (int i = 0; i < 32; i++) {
            blocks[0] += 'g' - 'a' + 1;
            blocks[1] += 'a' - 'a' + 1;
            blocks[2] += 'v' - 'a' + 1;
            blocks[3] += 'e' - 'a' + 1;
            blocks[1] += blocks[0];
        }

        blocks[0] %= 256;
        blocks[1] %= 256;
        blocks[2] %= 256;
        blocks[3] %= 256;

        /*blocks[1] += blocks[0];
        blocks[2] += blocks[1];
        blocks[3] += blocks[2];
        blocks[0] += blocks[3];*/
        //blocks[0] = (35 * blocks[0]) % 256;

        return blocks;
    }
}
