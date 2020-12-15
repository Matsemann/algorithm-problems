package com.matsemann;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


public class PizzaHawaii {


    static class Ingredient {
        String name;
        List<Pizza> pizzas = new ArrayList<>();

        List<String> possibleNames = new ArrayList<>();
    }

    static class Pizza {
        List<String> foreign;
        List<String> english;
    }

    List<Pizza> pizzas = new ArrayList<>();
    SortedMap<String, Ingredient> ingredients = new TreeMap<>();

    static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("inputs/pizzahawaii.txt"));

        sc = new Scanner(System.in);

        sc = new Scanner(System.in);
        int cases = sc.nextInt();
        sc.nextLine();

        while (cases-- > 0) {
            new PizzaHawaii();
            if (cases != 0) {
                System.out.println("");
            }
        }

    }

    public PizzaHawaii() {
        int nrOfPizzas = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < nrOfPizzas; i++) {
            readPizza();
        }

        for (Ingredient ingredient : ingredients.values()) {
            Pizza forstePizza = ingredient.pizzas.get(0);
            ingredient.possibleNames.addAll(forstePizza.english);

            for (int i = 1; i < ingredient.pizzas.size(); i++) {
                Pizza pizza = ingredient.pizzas.get(i);

                ingredient.possibleNames = ingredient.possibleNames.stream()
                        .filter(ing -> pizza.english.contains(ing))
                        .collect(Collectors.toList());

            }

            for (Pizza pizza : pizzas) {
                if (ingredient.pizzas.contains(pizza)) {
                    continue;
                }
                ingredient.possibleNames = ingredient.possibleNames.stream()
                        .filter(ing -> !pizza.english.contains(ing))
                        .collect(Collectors.toList());
            }
        }

        for (Ingredient ingredient : ingredients.values()) {
            String ing = ingredient.name;
            Collections.sort(ingredient.possibleNames, String::compareTo);

            for (String possibleName : ingredient.possibleNames) {
                System.out.println("(" + ing + ", " + possibleName + ")");
            }
        }

    }

    public void readPizza() {
        sc.nextLine();
        String[] foreign = sc.nextLine().split(" ", 2)[1].split(" ");
        String[] english = sc.nextLine().split(" ", 2)[1].split(" ");

        Pizza pizza = new Pizza();
        pizza.foreign = Arrays.asList(foreign);
        pizza.english = Arrays.asList(english);
        pizzas.add(pizza);

        for (String ing : foreign) {
            Ingredient ingredient = ingredients.getOrDefault(ing, new Ingredient());
            ingredient.name = ing;
            ingredient.pizzas.add(pizza);
            ingredients.put(ing, ingredient);
        }
    }

}
