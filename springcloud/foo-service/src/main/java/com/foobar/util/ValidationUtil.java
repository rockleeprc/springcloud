package com.foobar.util;

import com.foobar.pojo.Person;

import javax.validation.*;
import java.util.Set;

public class ValidationUtil {

    public static void main(String[] args) {
        Person p = new Person();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Person>> violations = validator.validate(p);
        if(violations.size()>0){
            throw new ConstraintViolationException(violations);
        }
    }
}