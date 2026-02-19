package com.javarush.yachnyy.utils;

import com.javarush.yachnyy.exceptions.AppException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

    public static String readFile(String path) {
        System.out.println("Path для файла:" + Path.of(path) + " // " + Path.of(path).getRoot());
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            throw new AppException("Ошибка чтения файла: " + path, e);
        }
    }

    public static void writeFile(String path, String text) {
        try {
            Files.writeString(Path.of(path), text);
        } catch (IOException e) {
            throw new AppException("Ошибка записи в файл: " + path, e);
        }
    }
}
