package learn.java8.interfaces;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description:
 * @FunctionalInterface介绍：
 * 函数接口是只有一个抽象方法的接口，用作Lambda 表达式的类型。
 * unctionalInterface的接口被称为函数式接口，该接口只能有一个自定义方法
 * ，但是可以包括从object类继承而来的方法。
 * 如果一个接口只有一个方  法，则编译器会认为这就是一个函数式接口
 * @date
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;

    default void process2(BufferedReader b,String test) throws IOException{
        System.out.println("my is interface default methord===");
    }
}
