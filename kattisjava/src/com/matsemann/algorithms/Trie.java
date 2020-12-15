package com.matsemann.algorithms;

import java.util.HashMap;
import java.util.Map;

public class Trie {

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
            } else {
                Node newNode = new Node();
                node.children.put(key[i], newNode);
                node = newNode;
            }
        }

        return node;


    }

}