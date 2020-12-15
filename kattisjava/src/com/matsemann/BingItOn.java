package com.matsemann;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BingItOn {


    static Scanner sc;


    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("inputs/bingiton.txt"));
        sc = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(System.out));


        int cases = Integer.parseInt(sc.nextLine());

        Trie trie = new Trie();

        while (cases-- > 0) {
            char[] key = sc.nextLine().toCharArray();
            Trie.Node insert = trie.insert(key);
            System.out.println(insert.val);
        }

    }

    static class Trie {

        static class Node {
            Map<Character, Node> children = new HashMap<>();
            int val = 0;
        }

        Node root;

        public Trie() {
            root = new Node();
        }

        public Node find(char[] key) {
            Node node = root;

            for (int i = 0; i < key.length; i++) {
                if (node.children.containsKey(key[i])) {
                    node = node.children.get(key[i]);
                } else {
                    return null;
                }
            }
            return node;
        }

        public Node insert(char[] key) {
            Node node = root;

            for (int i = 0; i < key.length; i++) {
                if (node.children.containsKey(key[i])) {
                    node = node.children.get(key[i]);
                    node.val += 1;
                } else {
                    Node newNode = new Node();
                    node.children.put(key[i], newNode);
                    node = newNode;
                }
            }

            return node;


        }

    }
}
