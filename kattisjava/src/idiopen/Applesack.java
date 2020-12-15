package idiopen;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Applesack {

    static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("inputs/applesack.txt"));

        sc = new Scanner(System.in);
        new Applesack(sc.nextLine());
    }

    public Applesack(String input) {
        String[] ins = input.split(" ");

        int apples = Integer.parseInt(ins[0]);
        int size = Integer.parseInt(ins[1]);

        int kms = 1;

        while (apples > 0) {
            int cost = (int) Math.ceil((float) apples / size);

            apples -= cost;
            kms++;
        }

        System.out.println(kms);

    }
}
