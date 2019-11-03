package com.guidesmiths.martianrobots.util.validators;

import com.guidesmiths.martianrobots.annotations.ValidEnum;
import com.guidesmiths.martianrobots.model.Robot;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class EnumValidator implements ConstraintValidator<ValidEnum, String > {
    private Field[] fields;
    private boolean orientationType;//In this case we should split the input string as it comes as "RFRLRF..."
    @SuppressWarnings({
            "unchecked",
            "rawtypes"
    })
    @Override
    public void initialize(ValidEnum targetEnum) {
        Class < ? extends Enum > enumSelected = targetEnum.targetClassType();

        orientationType = enumSelected.equals(Robot.MOVEMENTS.class);
        fields = enumSelected.getFields();
    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext context) {
        String[] values;
        if(orientationType){
            values = input.split("");
        }else{
            values = input.split(" ");
        }

        boolean matched;
        for(String value : values){
            matched = false;
            for (Field field : fields){
                if (field.getName().equals(value)) matched = true;
            }
            if(!matched) return false;
        }

        return true;
    }
}