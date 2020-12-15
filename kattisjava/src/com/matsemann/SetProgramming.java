package com.matsemann;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SetProgramming {


    static Scanner sc;

    static Random random;

    public SetProgramming(String[] cardDefs) {
        List<Card> cards = new ArrayList<>();
        for (String cardDef : cardDefs) {
            cards.add(new Card(cardDef));
        }

        int count = 0;

        for (int i = 0; i < cards.size(); i += 3) {
            if (checkIfSet(cards.get(i), cards.get(i + 1), cards.get(i + 2))) {
                count++;
            }
        }

        System.out.println(count);
    }

    public static boolean checkIfSet(Card c1, Card c2, Card c3) {
        for (int i = 0; i < 4; i++) {
            if (!checkFeature(c1.features[i], c2.features[i], c3.features[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkFeature(String c1, String c2, String c3) {
        if (c1.equals(c2) && c2.equals(c3)) {
            return true;
        }
        if (!c1.equals(c2) && !c1.equals(c3) && !c2.equals(c3)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("inputs/setprogramming.txt"));

        sc = new Scanner(System.in);

        new SetProgramming(sc.nextLine().split("\\|"));

        int seed = new Random().nextInt();
        //seed = 1530431419;
        System.out.println(seed);
        random = new Random(seed);

        //generateCards();
    }

    static void generateCards() {
        int sets = 0;
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Card c1 = randomCard();
            Card c2 = randomCard();
            Card c3 = randomCard();

            if (random.nextInt(10) < 7) {
                sets++;
                System.out.println("generating set");
                while (!checkIfSet(c1, c2, c3)) {
                    c3 = randomCard();
                }
            }

            cards.add(c1);
            cards.add(c2);
            cards.add(c3);
        }

        System.out.println("sets generated: " + sets);
        System.out.println();
        System.out.println();
        for (Card card : cards) {
            System.out.print(card + "|");
        }

        System.out.println();
        System.out.println();
        System.out.println();
    }

    static String[][] choices = {
            {"red", "purple", "green"},
            {"ovals", "squiggles", "diamonds"},
            {"solid", "striped", "outlined"},
            {"one", "two", "three"}
    };

    public static Card randomCard() {
        return new Card(
                new String[]{
                    choices[0][random.nextInt(3)],
                    choices[1][random.nextInt(3)],
                    choices[2][random.nextInt(3)],
                    choices[3][random.nextInt(3)]
                }
        );
    }

    static class Card {
        String[] features;

        public Card(String card) {
            features = card.split(",");
        }

        public Card(String[] features) {
            this.features = features;
        }

        @Override
        public String toString() {
            return features[0] + "," + features[1] + "," + features[2] + "," + features[3];
        }
    }
}
