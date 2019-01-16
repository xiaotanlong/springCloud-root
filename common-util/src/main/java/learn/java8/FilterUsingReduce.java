package learn.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author 0217319
 * @version V1.0
 * @Description: FilterUsingReduce(只用reduce 和Lambda 表达式写出实现Stream 上的filter 操作的代码，如果不想返回
        Stream，可以返回一个List。)
 * @date 2018/12/5 11:21
 */
public class FilterUsingReduce {

    public static <I> List<I> filter(Stream<I> stream, Predicate<I> predicate) {
        List<I> initial = new ArrayList<>();
        return stream.reduce(initial,
                (List<I> acc, I x) -> {
                    if (predicate.test(x)) {
                        List<I> newAcc = new ArrayList<>(acc);
                        newAcc.add(x);
                        return newAcc;
                    } else {
                        return acc;
                    }
                },
                FilterUsingReduce::combineLists);
    }

    private static <I> List<I> combineLists(List<I> left, List<I> right) {
        // We are copying left to new list to avoid mutating it.
        List<I> newLeft = new ArrayList<>(left);
        newLeft.addAll(right);
        return newLeft;
    }

    public static void main(String[] args) {
        List<Integer> ss = filter(Stream.of(1,2,3,4,5,6,7,8),s1-> s1 > 5);
        System.out.printf(ss.toString());


    }
}
