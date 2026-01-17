package com.javarush.yachnyy.command;

import com.javarush.yachnyy.entity.Result;

public interface Action {
    Result execute(String[] parameters);
}
