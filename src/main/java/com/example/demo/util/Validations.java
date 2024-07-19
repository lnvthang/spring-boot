package com.example.demo.util;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static com.example.demo.util.Constants.EMAIL_PATTERN;

@Component
public class Validations {
    public boolean isEmailValid(String email) {
        return email == null || !Pattern.matches(EMAIL_PATTERN, email);
    }
}
