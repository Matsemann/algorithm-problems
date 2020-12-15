package com.matsemann;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


public class MessagesFromOuterSpace {

    static Scanner sc;
    static Map<Character, List<String>> dictionary;

    String message;
    int[] cache;

    public MessagesFromOuterSpace(String message) {
        this.message = message;

        cache = new int[message.length()];
        Arrays.fill(cache, -1);

        System.out.println(solve(0));
    }

    private int solve(int position) {
        // end of string
        if (position == message.length()) {
            return 0;
        }

        // memoization as we reach same sub-problem often
        if (cache[position] != -1) {
            return cache[position];
        }

        int currentBest = 0;

        /* check if we can use any words at this position, and what
           the score would be using it, e.g.

            animpeachableape
                ^pos
            return best of using peach, e.g. 1 + solve(ableeape)
                                             ... per word
                                 and         0 + solve(eachableeape)
         */
        char c = message.charAt(position);

        List<String> words = dictionary.get(c);
        if (words != null) {
            for (String word : words) {
                if (message.startsWith(word, position)) {
                    int result = 1 + solve(position + word.length());
                    if (result > currentBest) {
                        currentBest = result;
                    }
                }
            }
        }

        // check not using the character at all
        int result = solve(position + 1);
        if (result > currentBest) {
            currentBest = result;
        }

        cache[position] = currentBest;
        return currentBest;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("inputs/messagesouterspace.txt"));
        sc = new Scanner(System.in);

        buildDictionary();

        while (true) {
            String message = readMessage();
            if (message == null) {
                break;
            }
            //System.out.println(message);
            new MessagesFromOuterSpace(message);
        }
    }

    private static String readMessage() {
        StringBuilder sb = new StringBuilder();

        while (true) {
            String line = sc.nextLine();
            if (line.equals("#")) {
                return null;
            }
            sb.append(line);
            if (line.charAt(line.length() - 1) == '|') {
                break;
            }
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private static void buildDictionary() {
        dictionary = new HashMap<>();

        String word = null;
        while (!(word = sc.nextLine()).equals("#")) {
            if (!dictionary.containsKey(word.charAt(0))) {
                dictionary.put(word.charAt(0), new ArrayList<>());
            }

            dictionary.get(word.charAt(0)).add(word);
        }

        /*
        Debug print dictionary
        for (Map.Entry<Character, List<String>> entry : dictionary.entrySet()) {
            System.out.print(entry.getKey() + ":" );
            for (String s : entry.getValue()) {
                System.out.print(s + ", ");
            }
            System.out.println();
        }*/
    }
}
