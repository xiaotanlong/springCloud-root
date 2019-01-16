package learn.java8;

import learn.java8.interfaces.BufferedReaderProcessor;

import javax.swing.text.DateFormatter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description:
 * @date
 */
public class test {

    public final static ThreadLocal<DateFormatter> formatter =
            ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));

    static void ss  (Predicate<test> test){

    }
    //函数式接口测试
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("D:\\data.txt"))) {
            return p.process(br);
        }
    }



    public static void main(String[] args) throws Exception {

        String oneLine = processFile((BufferedReader br) -> br.readLine());
        System.out.println(oneLine);

        String twoLine = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println(twoLine);

        BinaryOperator<Long> addExplicit = (Long x, Long y) -> x * y;

        System.out.println("---" + addExplicit.apply(1l,2l));

        Predicate<Integer> atLeast5 = x -> x > 5;

        System.out.println("---" + atLeast5.test(2));

        Thread thread = new Thread(() -> {System.out.println("hello world");System.out.println("hello world---");});
        thread.start();

        Set<Integer> numbers = new HashSet<>(asList(4, 3, 2, 1));
        List<Integer> sameOrder = numbers.stream()
                .collect(toList());
        // 该断言有时会失败
        //assertEquals(asList(4, 3, 2, 1), sameOrder);
        System.out.println(new Date().getTime());

        System.out.println(System.currentTimeMillis() / 1000);
    }


}

