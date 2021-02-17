package com.epam.task.fourth.parsers;

public class ParserException extends Exception{
    public ParserException(String message) {
        super(message);
    }

    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
