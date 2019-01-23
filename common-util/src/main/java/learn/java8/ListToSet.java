package learn.java8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: 获取  List<Map<String, String>>  中 map key中等号后面的值
 * map和reduce
        map用来归类，结果一般是一组数据，比如可以将list中的学生分数映射到一个新的stream中。
        reduce用来计算值，结果是一个值，比如计算最高分.
 * @date 2019/1/16 15:01
 */
public class ListToSet {

    public static void main(String[] args) {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("=7dasd8","dasda");
        map1.put("11=72118","sdasd");
        Map<String, String> map2 = new HashMap<>();
        map2.put("ww=7228","dasdwwa");
        map2.put("15=7www","sdasdwww");
        list.add(map1);
        list.add(map2);
        //不好的实现
        Set<String> ss= list.stream()
                .filter(item-> item != null)
                .reduce(new HashMap<>(),(all,item)->{all.putAll(item); return all;})
                .keySet()
                .stream().map(s->s.substring(s.lastIndexOf("=")+1,s.length()))
                .collect(Collectors.toSet());


        //较好的实现
        Set<String> sss = list.stream().flatMap(map -> map.keySet().stream())
                .map(s->s.substring(s.lastIndexOf("=")+1,s.length()))
                .collect(Collectors.toSet());

        ss.forEach(System.out::println);
        sss.forEach(System.out::println);
    }
}
