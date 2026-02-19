package com.javarush.yachnyy.core;

import com.javarush.yachnyy.alphabet.Alphabet;

public class CipherService {

    public String process(String text, int key) {
        int normalKey = normalizeKey(key);
        StringBuilder sb = new StringBuilder();

        for(char ch : text.toCharArray()) {
            Integer idx = Alphabet.indexOf(ch);

            if (idx != null) {
                int newIndex = (idx + normalKey) % Alphabet.size();
                sb.append(Alphabet.getChar(newIndex));
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    private int normalizeKey(int key) {
        int size = Alphabet.size();
        return ((key % size) + size) % size;
    }
}
