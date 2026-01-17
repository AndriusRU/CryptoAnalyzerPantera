package com.javarush.yachnyy;

import com.javarush.yachnyy.entity.Result;

public class ConsoleRunner {
    public static void main(String[] args) {
        Application app = new Application();
        Result result = app.run(args);

    }
}
