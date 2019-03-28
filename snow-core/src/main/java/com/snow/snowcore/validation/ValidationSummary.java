package com.snow.snowcore.validation;

import java.util.List;
import java.util.Optional;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description 匯總
 **/
public class ValidationSummary {

    private List<ValidationResult> resultList;

    public boolean success() {
        return !this.findFirst().isPresent();
    }

    public Optional<ValidationResult> findFirst() {
        return this.resultList.stream().filter((zw) -> {
            return !zw.getSuccess();
        }).findAny();
    }

    public List<ValidationResult> getResultList() {
        return this.resultList;
    }

    public void setResultList(final List<ValidationResult> resultList) {
        this.resultList = resultList;
    }

    public ValidationSummary() {
    }

    public ValidationSummary(final List<ValidationResult> resultList) {
        this.resultList = resultList;
    }
}
