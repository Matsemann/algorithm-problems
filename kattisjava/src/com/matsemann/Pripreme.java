package com.matsemann;

import java.io.FileInputStream;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Pripreme {


    static Scanner sc;

    public Pripreme() {
        sc.nextLine();
        String[] line = sc.nextLine().split(" ");

        List<Team> teams = new ArrayList<>();
        int total = 0;

        for (int i = 0; i < line.length; i++) {
            int time = parseInt(line[i]);
            total += time;
            teams.add(new Team(i+1, time));
        }

        teams.sort((o1, o2) -> o2.time - o1.time);

        int[] a = new int[total * 2];
        int[] g = new int[total * 2];

        int index = 0;
        for (Team team : teams) {
            for (int i = 0; i < team.time; i++) {
                a[index++] = team.nr;
            }
        }

        Set<Integer> usedTeams = new HashSet<>();

        int gIndex = 0;

        while (usedTeams.size() != teams.size()) {
            boolean found = false;

            for (int j = 0; j < teams.size(); j++) {
                Team team = teams.get(j);
                if (!usedTeams.contains(team.nr) && teamHarPlass(gIndex, team, a)) {
                    usedTeams.add(team.nr);

                    for (int k = 0; k < team.time; k++) {
                        g[gIndex++] = team.nr;
                    }

                    found = true;
                    break;
                }
            }
            if (!found) {
                gIndex++;
            }

        }

        System.out.println(gIndex);

    }

    private boolean teamHarPlass(int gIndex, Team team, int[] a) {
        for (int i = gIndex; i < gIndex + team.time; i++) {
            if (a[i] == team.nr) {
                return false;
            }
        }
        return true;
    }

    static class Team {
        int nr;
        int time;

        public Team(int nr, int time) {
            this.nr = nr;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("inputs/pripreme.txt"));
        sc = new Scanner(System.in);

        new Pripreme();
    }
}
