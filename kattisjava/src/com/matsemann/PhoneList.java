package com.matsemann;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class PhoneList {

    static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("inputs/phonelist.txt"));

        sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine());

        while (cases-- > 0) {
            new PhoneList();
        }
    }

    public PhoneList() {
        int nrNumbers = Integer.parseInt(sc.nextLine());

        List<String> nrs = new ArrayList<>();
        Map<Integer, Set<String>> nrsOfSize = new HashMap<>();


        for (int i = 0; i <= 10; i++) {
            nrsOfSize.put(i, new HashSet<>());
        }

        for (int i = 0; i < nrNumbers; i++) {

            String nr = sc.nextLine();

            nrs.add(nr);
            nrsOfSize.get(nr.length()).add(nr);
        }

        for (String nr : nrs) {
            for (int j = 1; j <= nr.length() - 1; j++) {

                String substr = nr.substring(0, j);

                if (nrsOfSize.get(j).contains(substr)) {
                    System.out.println("NO");
                    return;
                }
            }
        }



        System.out.println("YES");

    }

}
