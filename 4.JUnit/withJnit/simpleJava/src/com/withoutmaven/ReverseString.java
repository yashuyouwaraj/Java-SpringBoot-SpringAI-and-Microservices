package com.withoutmaven;

public class ReverseString {
    public String reverseString(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }
}
