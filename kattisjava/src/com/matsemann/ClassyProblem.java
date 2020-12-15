package com.matsemann;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ClassyProblem {

    static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("inputs/classy.txt"));

        sc = new Scanner(System.in);
        int cases = sc.nextInt(); sc.nextLine();

        while (cases --> 0) {
            new ClassyProblem();
        }
    }

    public ClassyProblem() {
        int nrPeople = sc.nextInt(); sc.nextLine();

        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < nrPeople; i++) {

            String[] person = sc.nextLine().split(": ");
            String name = person[0];
            String[] classNames = person[1].substring(0, person[1].length() - 6).split("-");

            String[] classes = new String[10];

            for (int j = 0, k = classNames.length - 1; j < classes.length; j++, k--) {
                String val = "1";

                if (k >= 0) {
                    if (classNames[k].equals("upper")) {
                        val = "2";
                    } else if (classNames[k].equals("lower")) {
                        val = "0";
                    }
                }
                classes[j] = val;
            }

            String rank = String.join("", classes);

            persons.add(new Person(name, rank));

        }

        Collections.sort(persons);

        persons.forEach(p -> System.out.println(p.name));
        System.out.println("==============================");

    }

    static class Person implements Comparable<Person>{
        String name;
        String rank;

        public Person(String name, String rank) {
            this.name = name;
            this.rank = rank;
        }


        @Override
        public int compareTo(Person o) {
            if (o.rank.equals(rank)) {
                return name.compareTo(o.name);
            } else {
                return o.rank.compareTo(rank);
            }
        }
    }
}
