package learn.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: UseTestMain(用一句话描述该文件做什么)
 * @date 2018/12/6 10:49
 */
public class UseTestMain {
    public static void main(String[] args) {
        //testOne(System.out::println);
        //testTwo();
        //testThree();
        //testThreeOld();
        //testFour();
//        testFourBySupplier();
               testFive();

       // System.out.println("#1#2".split("#").length);

    }


    /**
     * 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢 例如，给定[1, 2, 3, 4,5]，
     * 应该返回[1, 4, 9, 16, 25]。
     */
    private static void testOne(IntConsumer consumer) {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        integerList.stream()
                .map(n -> n * n)
                .collect(Collectors.toList())
                .forEach(consumer::accept);
    }

    /**
     * 给定两个数字列表，如何返回所有的数对呢?例如，给定列表[1, 2, 3]和列表[3, 4]，应 该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见，你可以用有两个元素的数组来代 表数对。
     */
    private static void testTwo() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(8,6,7);
        list1.stream()
                .flatMap(
                        i -> list2.stream()
                                .map(j -> new int[]{i, j})
                )
                .collect(Collectors.toList()).forEach(s-> {Arrays.stream(s).forEach(value -> System.out.print(value));System.out.println();});
    }

    /**
     * 获取1-100 内的勾股数
     * ava.lang.Math.sqrt(double a) 返回正确舍入的一个double值的正平方根。特殊情况：

     如果参数是NaN或小于为零，那么结果是NaN.

     如果参数是正无穷大，那么结果为正无穷大.

     如果参数是正零或负零，那么结果是一样的参数.
     */
    private static void testThree() {
        long startTime = System.currentTimeMillis();
        Consumer consumer = (Object array) -> {
            double[] arr = (double[]) array;
            Arrays.asList(arr)
                    .forEach(data -> System.out.println((int) data[0] + "," + (int) data[1] + "," + (int) data[2]));
        };

        IntStream.rangeClosed(1, 10000).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 10000)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(arr -> arr[2] <= 10000 && arr[2] % 1 == 0)
                ).forEach(consumer::accept);
        System.out.println("消耗时间：------------------------" + (System.currentTimeMillis() - startTime));
    }

    private static void testThreeOld(){
        long startTime = System.currentTimeMillis();
        for (int i = 0 ; i< 10000 ;i++){
            for (int j = i+1;j<=10000;j++){
                double l = Math.sqrt(i * i + j * j);
                if(l % 1 ==0 && l <= 10000){
                    System.out.println((int)i+","+(int)j+","+(int)l);
                }
            }
        }
        System.out.println("消耗时间：------------------------" + (System.currentTimeMillis() - startTime));


    }

    /**
     * 斐波纳契数列,下面这个数列就是斐波纳契数列的一部分:
     * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55...数列中开始的两个数字是0和1，后续的每个数字都是前两个数字之和。
     * Stream API提供了两个静态方法来从函数生成流：
     * Stream.iterate和Stream.generate。
     *      这两个操作可以创建所谓的无限流：不像从固定集合创建的流那样有固定大小的流
     */
    private static void testFour() {
        String collect = Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(1000)
                .map(t -> String.valueOf(t[0]))
                .collect(Collectors.joining(","));
        System.out.println(collect);
    }


    /**
     * IntStream.generate  开启无限流
     */
    private static void testFourBySupplier() {
        IntSupplier intSupplier = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(intSupplier).limit(10).forEach(System.out::println);
    }

    private static void testFive() {
        List<Integer> baseList = Arrays.asList(1, 2, -3, 3, 3, -4, 5);
        final int baseData = 3;
        // 将
        List<Integer> list = IntStream.range(0, baseList.size())
                .flatMap(index -> IntStream.range(index + 1, baseList.size())
                        .flatMap(
                                index1 -> IntStream.range(index1 + 1, baseList.size())
                                        .map(index2 -> baseList.get(index) + baseList.get(index1) + baseList.get(index2))
                        )
                )
                //.sorted()//排序
                //.distinct()//返回由此流的不同元素组成的流。  去重
                .boxed()//按类型装入
                .collect(Collectors.toList());//收集
        System.out.println(list);
        int result = 0;
        if (baseData <= list.get(0)) {
            result = list.get(0);
        }else if (baseData >= list.get(list.size() - 1)) {
            result = list.get(list.size() - 1);
        }else {

        }

        System.out.println(result);
    }
}
