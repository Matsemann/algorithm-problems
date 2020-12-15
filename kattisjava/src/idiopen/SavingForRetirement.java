package idiopen;


import com.matsemann.ClassyProblem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SavingForRetirement {

    static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("inputs/retirement.txt"));

        sc = new Scanner(System.in);
        new SavingForRetirement(sc.nextLine());
    }

    public SavingForRetirement(String input) {
        String[] ins = input.split(" ");

        int bobAge = Integer.parseInt(ins[0]);
        int bobRetireAge = Integer.parseInt(ins[1]);
        int bobSaving = Integer.parseInt(ins[2]);
        int aliceAge = Integer.parseInt(ins[3]);
        int aliceSave = Integer.parseInt(ins[4]);

        float bobRetireMoney = (bobRetireAge - bobAge) * bobSaving;

        int yearsToSave = (int) Math.ceil(bobRetireMoney / aliceSave);

        if (yearsToSave * aliceSave == bobRetireMoney) {
            yearsToSave++;
        }

        System.out.println(aliceAge + yearsToSave);
    }
}
