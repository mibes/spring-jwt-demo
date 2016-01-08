package com.mibesco.spring.jwt.validation;

import jersey.repackaged.com.google.common.base.Preconditions;

public class StringConditions {
    private StringConditions() { }

    public static String checkNotBlank(String string) {
        Preconditions.checkArgument(string != null && string.trim().length() > 0);
        return string;
    }
}
