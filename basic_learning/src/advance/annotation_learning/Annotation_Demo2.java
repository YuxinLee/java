package advance.annotation_learning;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 自定义注解的语法
 */
public class Annotation_Demo2 {
    private String test;

    @Id
    @Author(remark = "保存信息！！！", age = 19)
    public void save() throws Exception {
        // 获取注解信息： name/age/remark

        // 1. 先获取代表方法的Method类型;
        Class clazz = Annotation_Demo2.class;
        Method m = clazz.getMethod("save");

        // 2. 再获取方法上的注解
        Author author = m.getAnnotation(Author.class);
        // 获取输出注解信息
        System.out.println(author.authorName());
        System.out.println(author.age());
        System.out.println(author.remark());

//        getAnnotation：返回指定的注解
//        isAnnotationPresent：判定当前元素是否被指定注解修饰
//        getAnnotations：返回所有的注解
//        getDeclaredAnnotation：返回本元素的指定注解
//        getDeclaredAnnotations：返回本元素的所有注解，不包含父类继承而来的
    }



    @Test
    public void testMain() throws Exception {
        save();
    }
}
