package org.malred.iset.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author malguy-wang sir
 * @create ---
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface iset {
    // 注入单个属性
    String setName() default "";//要设置的属性名
    String setData() default "";//要设置的属性值
    // 注入多个属性
    // 两个数组一一对应,到时候逐个赋值
    String[] setNames() default {};
    String[] setDatas() default {};
}
