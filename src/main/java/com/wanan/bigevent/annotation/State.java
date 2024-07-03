package com.wanan.bigevent.annotation;

import com.wanan.bigevent.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

// 可以放到什么位置
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
// 保留到什么时候
@Retention(RetentionPolicy.RUNTIME)
// 将一个 Java 注解标记为一个校验约束， 并指定一个校验器类来执行校验逻辑
@Constraint(validatedBy = {StateValidation.class})
public @interface State {

    // 提供校验失败的提示信息
    String message() default "state参数的值只能是已发布或者草稿";

    // 指定分组信息
    Class<?>[] groups() default {};

    // 负载 获取到State注解的附加信息
    Class<? extends Payload>[] payload() default { };
}
