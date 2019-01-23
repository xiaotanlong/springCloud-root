package learn.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: MapUsingReduce(只用reduce 和Lambda 表达式写出实现Stream 上的map 操作的代码，如果不想返回
        Stream，可以返回一个List。)
 * @date 2018/12/5 9:31
 */
public class MapUsingReduce {

    public static <I, O> List<O> map(Stream<I> stream, Function<I, O> mapper) {
        return stream.parallel().reduce(new ArrayList<O>(), (acc, x) -> {
            List<O> newAcc = new ArrayList<>(acc);
            newAcc.add(mapper.apply(x));
            return newAcc;
        }, (List<O> left, List<O> right) -> {
            // We are copying left to new list to avoid mutating it.
            List<O> newLeft = new ArrayList<>(left);
            newLeft.addAll(right);
            return newLeft;
        });
    }

    public static void main(String[] args) {
        List<Integer> ss = map(Stream.of(1,2,3,4,5,6,7,8),s1-> s1 * s1);
        System.out.printf(ss.toString());
    }

}
