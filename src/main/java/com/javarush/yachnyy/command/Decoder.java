package com.javarush.yachnyy.command;

import com.javarush.yachnyy.core.CipherService;
import com.javarush.yachnyy.entity.Result;
import com.javarush.yachnyy.exceptions.AppException;
import com.javarush.yachnyy.utils.FileManager;

public class Decoder implements Action{

    private final CipherService cipherService = new CipherService();

    @Override
    public Result execute(String[] parameters) {
        if (parameters.length < 3) {
            throw new AppException("Не хватает параметров для расшифровки.");
        }

        String inputPath = parameters[0];
        String outputPath = parameters[1];
        int key = Integer.parseInt(parameters[2]);

        String text = FileManager.readFile(inputPath);
        String decrypted = cipherService.process(text, -key);

        FileManager.writeFile(outputPath, decrypted);

        return new Result(true, "Текст успешно дешифрован.");
    }


}
