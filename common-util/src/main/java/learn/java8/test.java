package learn.java8;

import learn.java8.interfaces.BufferedReaderProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Predicate;

/**
 * @author 0217319
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * @date
 */
public class test {
    static void ss  (Predicate<test> test){

    }
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
    }
}
