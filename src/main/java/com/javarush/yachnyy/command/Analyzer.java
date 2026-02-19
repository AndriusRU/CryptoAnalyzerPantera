package com.javarush.yachnyy.command;

import com.javarush.yachnyy.entity.Result;

public class Analyzer implements Action{

    public Result execute(String[] parameters) {


        return new Result(true, "Пока не работает");
    }
}
