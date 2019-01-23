package learn.java8;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: Stream 练习题
 * @date 2018/12/4 19:51
 */
public class StreamExercise {
    public static void main(String[] args) {
        //a. 编写一个求和函数， 计算流中所有数之和。例如，int addUp(Stream<Integer>numbers)；
        Integer sum = Stream.of(1,2,3,4,5,6).reduce(0,(acc,element) -> acc + element);
        Integer sum2 = Stream.of(1,2,3,4,5,6).reduce(Integer::sum).get();
        System.out.println("sum is : " + sum + "   sum2 is :"+sum2);


        Map<Integer, Long> map = Stream.of(1,2,3,4,5,6).collect(Collectors.groupingBy(a->a*8%6,Collectors.counting()));

        map.forEach((key,value)-> System.out.println("key:value" + key +":" + value));
    }

    //b. 编写一个函数，接受艺术家列表作为参数，返回一个字符串列表，其中包含艺术家的姓名和国籍；
    //c. 编写一个函数，接受专辑列表作为参数，返回一个由最多包含3 首歌曲的专辑组成的列表。
}
