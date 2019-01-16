package learn;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author 0217319
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * @date 2019/1/5 14:39
 */
public class mainTest {
    public static void main(String[] args) {
        BigDecimal val = new BigDecimal(2).divide(new BigDecimal(3),2, RoundingMode.HALF_UP);
        System.out.println(val.doubleValue());
    }
}
