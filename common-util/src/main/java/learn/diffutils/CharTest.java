package learn.diffutils;

/**
 * @Auther: jianglong.Tan
 * @Date: 2019/4/30 16:19
 * @Description:
 */

public class CharTest {
    public static void main(String[] args) {
        String n1 = "支付ds苜1234561蓿款";
        String n2 = "支付ds1234567蓿款";

        int begin = 0,end1 = 0,end2 = 0;//开始位置，差异长度，差异长度
        int max = n1.length() >  n2.length() ? n1.length() : n2.length();
        int min = n1.length() <  n2.length() ? n1.length() : n2.length();

        for (int i = 0;i< min ;i++){
            if(n1.charAt(i) != n2.charAt(i)){
                begin = i;
                break;
            }
        }

        int y = 0;
        while (true){

            if(n1.charAt(n1.length() -1 - y) != n2.charAt(n2.length() -1 - y) || y == min){
                end1 = n1.length() - y - begin;
                end2 = n2.length() - y - begin;
                break;
            }
            y++;
        }

       /* if(m == n1.length()){
            m = n2.length();
        }*/
        System.out.println("begin:" + begin);
        System.out.println("end1:" + end1);
        System.out.println("end2:" + end2);
    }
}
