package com.snow.snowcore.validation;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.var;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description 驗證管理器
 **/
@Component
public class ValidationManager {

    private LinkedHashMap<Validation, Object> hashMap = new LinkedHashMap(100);

    private Boolean stopWhenFailed;

    public ValidationManager() {
    }

    public ValidationManager add(Validation validation, Object dataContext) {
        this.hashMap.put(validation, dataContext);
        return this;
    }

    public ValidationSummary validate() {
        List<ValidationResult> results = Lists.newArrayList();
        Iterator it = this.hashMap.entrySet().iterator();

        while(it.hasNext()) {
            Map.Entry<Validation, Object> validation = (Map.Entry)it.next();
            ValidationResult ret = ((Validation)validation.getKey()).validate(validation.getValue());
            results.add(ret);
            if (!ret.getSuccess()) {
                break;
            }
        }

        return new ValidationSummary(results);
    }

    public Boolean getStopWhenFailed() {
        return this.stopWhenFailed;
    }

    public void setStopWhenFailed(final Boolean stopWhenFailed) {
        this.stopWhenFailed = stopWhenFailed;
    }
}
