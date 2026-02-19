package com.javarush.yachnyy.controllers;

import com.javarush.yachnyy.command.*;
import com.javarush.yachnyy.entity.Result;
import com.javarush.yachnyy.exceptions.AppException;

import java.util.HashMap;
import java.util.Map;

public class MainController {

    private final Map<String, Action> actions = new HashMap<>();

    public MainController() {
        actions.put("encrypt", new Encoder());
        actions.put("decrypt", new Decoder());
        actions.put("bruteforce", new BruteForce());
        actions.put("analyze", new Analyzer());
    }

    public Result runAction(String action, String[] parameters) {
        Action command = actions.get(action.toLowerCase());

        if (command == null) {
            throw new AppException("Неизвестная команда: " + action);
        }

        return command.execute(parameters);
    }


}
