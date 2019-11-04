package com.mtxsoftware.spring.validators;

import com.mtxsoftware.spring.annotation.NotContainsSpecialSymbols;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class NotContainsSpecialSymbolsValidator implements ConstraintValidator<NotContainsSpecialSymbols, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.contains("!")) return false;
        if (value.contains("\"")) return false;
        if (value.contains("№")) return false;
        if (value.contains(";")) return false;
        if (value.contains("%")) return false;
        if (value.contains(":")) return false;
        if (value.contains("?")) return false;
        if (value.contains("*")) return false;
        if (value.contains("(")) return false;
        if (value.contains(")")) return false;
        return true;
    }
}