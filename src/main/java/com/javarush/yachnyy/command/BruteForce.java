package com.javarush.yachnyy.command;

import com.javarush.yachnyy.alphabet.Alphabet;
import com.javarush.yachnyy.core.CipherService;
import com.javarush.yachnyy.entity.Result;
import com.javarush.yachnyy.utils.FileManager;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class BruteForce implements Action{
    private static final Set<String> DICTIONARY = new HashSet<>(
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

    public Result execute(String[] parameters) {
        CipherService cipherService = new CipherService();

        String inputPath = parameters[0];
        String outputPath = parameters[1];

        String encryptedText = FileManager.readFile(inputPath);

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

        FileManager.writeFile(outputPath, bestText);

        return new Result(true, "Текст расшифрован. Возможный ключ - " + bestKey);
    }

    private int scoreText(String text) {
        int score = 0;

        String upperText = text.toUpperCase(Locale.ROOT);

        //Проверка слов по словарю
        String[] words = text.split("[^А-Яа-яЁё]+");
        for (String word : words) {
            if (DICTIONARY.contains(word.toUpperCase(Locale.ROOT))) {
                score += 5;
            }
        }

        // Проверка символов по часто встречающимся
        for (char ch : text.toCharArray()) {
            if (FREQUENT_RUS_LETTERS.contains(Character.toUpperCase(ch))) {
                score++;
            }
        }
        // Проверка биграмм
        for (String bigram : BAD_BIGRAMS) {
            if (upperText.contains(bigram)) {
                score -= 10;
            }
        }

        // Проверка пунктуации
        if (text.contains(". ")) score += 3;
        if (text.contains(", ")) score += 3;
        if (text.contains("! ")) score += 3;
        if (text.contains("!!! ")) score += 3;
        if (text.contains("? ")) score += 3;

        if (text.contains(" ,")) score -= 3;
        if (text.contains(" .")) score -= 3;

        // Количество согласных подряд не должно быть больше 5
        int countConsonant = 0;
        for (char ch : upperText.toCharArray()) {
            if (Character.isLetter(ch) && !RUS_VOWELS.contains(ch)) {
                countConsonant++;

                if (countConsonant >= 5) {
                    score -=3;
                }
            } else {
                countConsonant = 0;
            }
        }

        return score;
    }


}
