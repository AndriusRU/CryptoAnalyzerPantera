package com.javarush.yachnyy.command;

import com.javarush.yachnyy.alphabet.Alphabet;
import com.javarush.yachnyy.core.CipherService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BruteForce {
    private static final Set<String> DICTIONARY = new HashSet<String>(
        Arrays.asList(
            "И","В","НЕ","НА","Я","ОН","ОНА","ОНИ","ЭТО","К",
            "С","У","ПО","А","НО","ДА","ЧТО","КАК","ЕСЛИ","ТО",
            "ДЛЯ","БЫЛ","БЫЛА","БЫЛО","БЫТЬ","МОЖЕТ","КОТОРЫЙ","ЖЕ","ЛИ","ЕГО",
            "ЕЕ","ИХ","ТАК","УЖЕ","ТЕ","МЫ","ТЫ","МНЕ","ТЕБЕ","НАС",
            "ОТ","ДО","ИЗ","ЗА","ПРИ","ПОСЛЕ","ПЕРЕД","ТУТ","ТАМ","ТОЛЬКО",
            "ЕЩЕ","СЕЙЧАС","ВРЕМЯ","ЧЕЛОВЕК","МОЙ","ТВОЙ","СВОЙ","ЗДЕСЬ","ТАМ","ТАКОЙ",
            "НОВЫЙ","СТАРЫЙ","ХОРОШО","ПЛОХО","ГОД","ДЕНЬ","РУКА","НОГА","ЛЮДИ","ДЕТИ",
            "СЕЙ","ТОТ","ТА","ТО","ВСЕ","НИКТО","НИЧТО","КОМУ","ЧТОБЫ","ПОТОМ",
            "СЕГОДНЯ","ВЧЕРА","СИЛА","РАБОТА","ГЛАЗ","СЛОВО","ЖИЗНЬ","ДУША","МЫСЛЬ",
            "ЧАСТЬ","ПРОБЛЕМА","ВОПРОС","ДОМ","ГОРОД","СТРАНА","ПРОЕКТ","МЕСТО","РАЗ"
        )
    );

    private static final String[] BAD_BIGRAMS = new String[] {
        "ЪЪ","ЬЬ","ЫЫ","ЙЙ","ЭЭ","ЁЁ","ЮЮ","ЯЯ",
        "ЬЪ","ЪЬ","ЫЬ","ЬЫ","ЙЪ","ЪЙ","ЙЬ","ЬЙ",
        "ЭЪ","ЪЭ","ЭЬ","ЬЭ","ЮЪ","ЪЮ","ЯЪ","ЪЯ"
    };

    private static final Set<Character> RUS_VOWELS = new HashSet<>(
        Arrays.asList(
            'А','О','У','Э','Ы','Е','Ё','И','Ю','Я'
        )
    );

    private static final Set<Character> FREQUENT_RUS_LETTERS = new HashSet<>(
        Arrays.asList(
            'О','Е','А','И','Н','Т','С','Р','В','Л','К'
        )
    );

    public String process(String encryptedText) {
        CipherService cipherService = new CipherService();

        String bestText = "";
        int bestScore = Integer.MIN_VALUE;
        int bestKey = 0;

        for (int key = 0; key < Alphabet.size(); key++) {
            String possibleText = cipherService.process(encryptedText, -key);

            int currentScore = scoreText(possibleText);

            if (currentScore > bestScore) {
                bestScore = currentScore;
                bestKey = key;
                bestText = possibleText;
            }

        }

        System.out.println("Наилучший ключ - " + bestKey);
        return bestText;
    }

    private int scoreText(String text) {
        return 0;
    }


}
