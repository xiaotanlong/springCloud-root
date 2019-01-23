package learn.java8;

import learn.java8.examples.Artist;

import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: PerformanceFixed (用一句话描述该文件做什么)
 * @date 2018/12/5 14:50
 */

/**
 * ，添加getAllMusicians 方法，该方法返回包
 含所有艺术家名字的Stream，如果对象是乐队，则返回每个乐队成员的名字。例如，如
 果getMusicians 方法返回甲壳虫乐队，则getAllMusicians 方法返回乐队名和乐队成员，
 如约翰· 列侬、保罗· 麦卡特尼等。
 */
public interface PerformanceFixed {
    public String getName();

    public Stream<Artist> getMusicians();

    public default Stream<Artist> getAllMusicians() {
        return getMusicians()
                .flatMap(artist -> concat(Stream.of(artist), artist.getMembers()));
    }
}
