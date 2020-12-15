package idiopen;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Whatsinit {

    static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("inputs/whatsinit2.txt"));

        sc = new Scanner(System.in);
        new Whatsinit(sc.nextLine());
    }

    public Whatsinit(String input) {
        int nrItems = Integer.parseInt(input);

        List<Item> allItems = new ArrayList<>();
        List<Item> unknownItems = new ArrayList<>();

        int knownSum = 0;

        for (int i = 0; i < nrItems; i++) {
            String[] ins = sc.nextLine().split(" ");

            Item item = new Item();
            item.name = ins[0];

            if (ins[1].equals("?")) {
                item.unknown = true;
                unknownItems.add(item);
            } else {
                int value = Integer.parseInt(ins[1]);
                item.lower = value;
                item.upper = value;
                knownSum += value;
                item.unknown = false;
            }

            allItems.add(item);
        }

        for (int i = 0; i < allItems.size(); i++) {
            Item item = allItems.get(i);
            if (!item.unknown) {
                continue;
            }

            int upper = 100;
            int parent = i - 1;
            while (parent >= 0) {
                Item p = allItems.get(parent--);
                if (!p.unknown) {
                    upper = p.upper;
                    break;
                }
            }

            item.upper = upper;

            int lower = 0;
            int desc = i + 1;

            while (desc < allItems.size()) {
                Item d = allItems.get(desc++);
                if (!d.unknown) {
                    lower = d.upper;
                    break;
                }
            }

            item.lower = lower;
        }


        int rest = 100 - knownSum;

        while(iterate(unknownItems, rest)) {}


        for (Item item : allItems) {
            System.out.println(item.name + " " + item.lower + " " + item.upper);
        }
    }

    private boolean iterate(List<Item> unknownItems, int rest) {
        boolean hasChanged = false;
        int lowerSum = 0;

        for (Item unknownItem : unknownItems) {
            lowerSum += unknownItem.lower;
        }

        for (Item unknownItem : unknownItems) {
            int upper = rest - (lowerSum - unknownItem.lower);
            if (upper < unknownItem.upper) {
                hasChanged = true;
                unknownItem.upper = upper;
            }
        }

        int upperSum = 0;

        for (Item unknownItem : unknownItems) {
            upperSum += unknownItem.upper;
        }

        for (Item unknownItem : unknownItems) {
            int lower = rest  - (upperSum - unknownItem.upper);
            if (lower > unknownItem.lower) {
                hasChanged = true;
                unknownItem.lower = lower;
            }
        }

        return hasChanged;
    }


    static class Item {
        String name;
        boolean unknown;
        int lower, upper;
    }
}
