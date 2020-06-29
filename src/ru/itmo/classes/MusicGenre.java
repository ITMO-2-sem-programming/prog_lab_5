package ru.itmo.classes;

import java.util.Arrays;

public enum MusicGenre {
    PROGRESSIVE_ROCK,
    HIP_HOP,
    POST_ROCK,
    PUNK_ROCK,
    POST_PUNK;

    public static String showValues() {
        return Arrays.toString(MusicGenre.values());
    }

}
