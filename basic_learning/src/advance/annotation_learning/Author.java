package advance.annotation_learning;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 自定义注解  (描述一个作者)
 *
 */
// 元注解 - 1. 定义注解的可用范围
@Target({TYPE,FIELD , METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
//@Target({METHOD,FIELD,TYPE})   指定只能在方法、字段、类上用；

// 元注解 - 2. 指定注解的声明周期
@Retention(RetentionPolicy.RUNTIME)   // 字节码级别有效
public @interface Author {
    String authorName() default "Jet";      //带默认值的注解，使用的时候就可以不写此属性值
    int age() default 30;
    String remark();

    // 如果注解名称为value,使用时候可以省略名称，直接给值
    // (且注解只有一个属性时候才可以省略名称)
    //String value();

}
