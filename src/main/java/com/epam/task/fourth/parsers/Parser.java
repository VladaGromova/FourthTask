package com.epam.task.fourth.parsers;

import com.epam.task.fourth.entities.Tariff;

import java.util.List;

public interface Parser {
    List<Tariff> parse(String file) throws ParserException;
}
