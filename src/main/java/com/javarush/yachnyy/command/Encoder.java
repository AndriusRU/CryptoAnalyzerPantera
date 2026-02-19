package com.javarush.yachnyy.command;

import com.javarush.yachnyy.core.CipherService;
import com.javarush.yachnyy.entity.Result;
import com.javarush.yachnyy.exceptions.AppException;
import com.javarush.yachnyy.utils.FileManager;

public class Encoder implements Action{

    private final CipherService cipherService = new CipherService();

    @Override
    public Result execute(String[] parameters) {
        if (parameters.length < 3) {
            throw new AppException("Не хватает параметров для шифрования");
        }

        String inputPath = parameters[0];
        String outputPath = parameters[1];
        int key = Integer.parseInt(parameters[2]);

        String text = FileManager.readFile(inputPath);
        String encrypted = cipherService.process(text, key);

        FileManager.writeFile(outputPath, encrypted);

        return new Result(true, "Текст успешно зашифрован.");
    }


}
