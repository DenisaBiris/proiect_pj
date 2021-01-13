package com.company;

public enum CoffeeType {


    ARABICA("Arabica"),
    ROBUSTA("Robusta");


    private final String label;

    CoffeeType(String label) {
        this.label = label;
    }

}
