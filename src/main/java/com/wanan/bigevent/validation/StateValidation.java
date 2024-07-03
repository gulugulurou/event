package com.wanan.bigevent.validation;

import com.wanan.bigevent.annotation.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }
        if (value.equals("草稿") || value.equals("已发布")) {
            return true;
        }
        // 提供校验逻辑
        return false;
    }

}
