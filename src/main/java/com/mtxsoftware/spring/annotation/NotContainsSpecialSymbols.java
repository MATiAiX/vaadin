package com.mtxsoftware.spring.annotation;

import com.mtxsoftware.spring.validators.NotContainsSpecialSymbolsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotContainsSpecialSymbolsValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotContainsSpecialSymbols {

    String message() default "Поле не должно содержать спецсимволы !\"№;%:?*()";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}