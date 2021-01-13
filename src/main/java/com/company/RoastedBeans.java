package com.company;

public enum RoastedBeans {


    LIGHT("Light"),
    MEDIUM("Medium"),
    DARK("Dark");


    private final String label;

    RoastedBeans(String label) {
        this.label = label;
    }

}