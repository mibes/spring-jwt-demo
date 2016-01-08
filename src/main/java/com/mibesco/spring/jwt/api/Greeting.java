package com.mibesco.spring.jwt.api;

public class Greeting {
    private final String text;

    public Greeting() {
        text = "";
    }

    public Greeting(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
