package ru.itmo.classes;

import java.util.Arrays;

public enum Color {
    GREEN,
    RED,
    ORANGE,
    WHITE;

    public static String showValues() {
        return Arrays.toString(Color.values());
    }
}
