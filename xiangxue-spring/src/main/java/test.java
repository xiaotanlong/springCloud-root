/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * @date 2018/11/7 14:48
 */
public class test {
    public static void main(String[] args) {
        String ss = "/ds/dsda/dsds/dasddd";
        System.out.println(ss.substring(ss.lastIndexOf("/") + 1,ss.length()));
    }
}
