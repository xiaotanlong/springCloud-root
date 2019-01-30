package springcloudroot.xiangxuexxljob.xxljob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * @date 2019/1/29 21:10
 */
public class GlueJobHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.大萨达撒大");
        System.out.println("测试  xxl大萨达所  job  -----parm is:" + param);
        return ReturnT.SUCCESS;
    }

}
