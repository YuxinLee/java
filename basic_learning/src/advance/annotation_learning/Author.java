package advance.annotation_learning;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 自定义注解  (描述一个作者)
 * @author Jie.Yuan
 *
 */
// 元注解 - 1. 定义注解的可用范围
@Target({TYPE,FIELD , METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
//@Target({METHOD,FIELD,TYPE})   指定只能在方法、字段、类上用；

// 元注解 - 2. 指定注解的声明周期
@Retention(RetentionPolicy.RUNTIME)   // 字节码级别有效
public @interface Author {
    String authorName() default "Jet";
    int age() default 30;
    String remark();
}
