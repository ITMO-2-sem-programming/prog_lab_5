package ru.itmo.classes;

import java.util.Arrays;

public enum Country {
    GERMANY,
    CHINA,
    ITALY;

    public static String showValues() {
        return Arrays.toString(Country.values());
    }

}
