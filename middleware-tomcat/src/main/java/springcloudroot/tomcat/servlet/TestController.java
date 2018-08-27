package springcloudroot.tomcat.servlet;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 测试使用SpringController 暴露接口
 * @date
 */
//@RestController
public class TestController {
    //@GetMapping("/testCon")
    public String dc() throws InterruptedException{
        return "my is testCon";
    }

}
