package com.snow.snowweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.ValidationException;
import java.util.Set;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/28 09:11
 * @description
 **/
public class BeanValidator {
    private static final Logger log = LoggerFactory.getLogger(BeanValidator.class);
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public BeanValidator() {
    }

    public static <T> void validate(T object) throws ValidationException {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object, new Class[0]);
        ConstraintViolation<T> constraintViolation = null;
        if (constraintViolations != null && !constraintViolations.isEmpty()) {
            constraintViolation = (ConstraintViolation)constraintViolations.iterator().next();
        }

        if (constraintViolation != null) {
            throw new ValidationException(constraintViolation.getMessage());
        }
    }
}
