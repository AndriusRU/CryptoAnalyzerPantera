package com.javarush.yachnyy.alphabet;

import java.util.HashMap;
import java.util.Map;

public class Alphabet {
    private static final char[] CHARS;
    private static final Map<Character, Integer> ALPHABET;

    private static final String RUS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ";
    private static final String ENG = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "~!@#$%^&*()_+\"№;:?-=.,/\\|{}[]";

    static {
        CHARS = (ENG + ENG.toLowerCase() + RUS + RUS.toLowerCase() + NUMBERS + SYMBOLS).toCharArray();

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < CHARS.length; i++) {
            map.put(CHARS[i], i);
        }
        ALPHABET = Map.copyOf(map);
    }

    private Alphabet() {}

    public static int size() {
        return CHARS.length;
    }

    public static char getChar(int index) {
        return CHARS[index];
    }

    public static Integer indexOf(char c) {
        return ALPHABET.get(c);
    }

}
