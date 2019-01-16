package learn.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author 0217319
 * @version V1.0
 * @Description: java8 stream api
 * @date 2018/12/4 10:45
 */
public class StreamTest {
    public static void main(String[] args) {
        List<String> ss = new ArrayList();
        ss.add("aa");
        ss.add("aa1");
        ss.add("aa12");
        ss.add("aa23");
        Long count = ss.stream().filter(s -> {System.out.println("-----filter true");
            return s.contains("1");}).count();
        System.out.println(count);

        List<String> sss = ss.stream().map(s -> s + s).collect(toList());
        List<String> collected = Stream.of("a", "b", "c").collect(toList());


        //合并两个list
        List<String> together = Stream.of(ss,collected).flatMap(numbers -> numbers.stream()).collect(toList());
        together.stream().forEach(s -> System.out.println("s is : " + s));

        //查找max String
        String maxString = together.stream().max(Comparator.comparing(s -> s.length())).get();
        System.out.println(maxString);

        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);

        //reduce 使用
        int count2 = Stream.of(1, 2, 3)
                .reduce(0, (acc, element) -> acc + element);
        String ssss = together.stream().reduce("ssss is :",(acc,element)-> acc + element);
        System.out.printf(ssss);


        together.stream().forEach(s -> System.out.println("together : "+s));
        together.stream().limit(3).skip(1).forEachOrdered(s -> System.out.println("limit &  skip : "+s));

    }


}
