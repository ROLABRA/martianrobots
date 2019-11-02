package com.guidesmiths.martianrobots.util.validators;

import com.guidesmiths.martianrobots.annotations.ValidEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class EnumValidator implements ConstraintValidator<ValidEnum, String > {
    private Field[] fields;
    @SuppressWarnings({
            "unchecked",
            "rawtypes"
    })
    @Override
    public void initialize(ValidEnum targetEnum) {
        Class < ? extends Enum > enumSelected = targetEnum.targetClassType();

        fields = enumSelected.getFields();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for(Field field : fields){
            if (field.getName().equals(value)) return true;
        }
        return false;
    }
}