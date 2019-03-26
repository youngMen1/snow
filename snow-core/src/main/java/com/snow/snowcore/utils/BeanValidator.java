package com.snow.snowcore.utils;

import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;
import javax.validation.*;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description Bean 驗證器
 **/
@Slf4j
public class BeanValidator {

    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();


    /**
     * 驗證對象，如果失敗，則返回第一個驗證失敗的對象
     *
     * @param object 對象
     * @param <T>    泛型
     * @throws ValidationException 驗證不通過，則拋出異常
     */
    public static <T> void validate(T object) throws ValidationException {

        Validator validator = factory.getValidator();
        var constraintViolations = validator.validate(object);

        ConstraintViolation<T> constraintViolation = null;

        if (constraintViolations != null && !constraintViolations.isEmpty()) {
            constraintViolation = constraintViolations.iterator().next();
        }

        if (constraintViolation != null) {
            throw new ValidationException(constraintViolation.getMessage());
        }
    }
}
