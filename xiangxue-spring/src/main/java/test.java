/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * @date 2018/11/7 14:48
 */
public class test {
    private static Integer a = new Integer(1);
    private static Integer b = new Integer(2);

    static {
        System.out.println(a.getClass());
    }
    public void swap(Integer a,Integer b){
        Integer s = b;
        b = a;
        a = s;
    }
    public static void main(String[] args) {
        /*Integer a = 1;
        Integer b = 2;*/
        test t = new test();
        System.out.println(a.getClass());
        t.swap(a,b);
        System.out.println(a + "   " + b);
    }
}
