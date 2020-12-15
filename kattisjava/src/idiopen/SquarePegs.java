package idiopen;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SquarePegs {

    static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("inputs/squarepegs.txt"));

        sc = new Scanner(System.in);
        new SquarePegs(sc.nextLine());
    }

    public SquarePegs(String input) {
        String[] ins = input.split(" ");

        int nrPlots = Integer.parseInt(ins[0]);
        int nrCircles = Integer.parseInt(ins[1]);
        int nrSquares = Integer.parseInt(ins[2]);

        ArrayList<Integer> plots = new ArrayList<>();
        //int circles[] = new int[nrCircles];
        //float squares[] = new float[nrSquares];

        ArrayList<Float> houses = new ArrayList<>();

        for (int i = 0; i < nrPlots; i++) {
            plots.add(sc.nextInt());
        }
        sc.nextLine();


        for (int i = 0 ; i < nrCircles; i++) {
            houses.add((float) sc.nextInt());
        }
        sc.nextLine();


        for (int i = 0; i < nrSquares; i++) {
            int sideLength = sc.nextInt();

            double r = Math.sqrt(Math.pow(sideLength, 2) / 2);
            houses.add((float) r);

            //System.out.println("square l=" + sideLength + " gives r=" + r);
        }

        Collections.sort(plots);
        Collections.sort(houses);

        Collections.reverse(plots);
        Collections.reverse(houses);

        int filledPlots = 0;

        for (Float house : houses) {

            if (filledPlots >= plots.size()) {
                break;
            }

            if (house < plots.get(filledPlots)) {
                filledPlots++;
            }

        }

        System.out.println(filledPlots);


    }
}
