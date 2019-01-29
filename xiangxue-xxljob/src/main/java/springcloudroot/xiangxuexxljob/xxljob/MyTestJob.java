package springcloudroot.xiangxuexxljob.xxljob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Service;

/**
 * @author 0217319
 * @version V1.0
 * @Description:
 * @date 2019/1/29 15:13
 */
@JobHandler(value="enjoySimple") //value值对应的是调度中心新建任务的JobHandler
@Service
public class MyTestJob extends IJobHandler {
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        System.out.println("测试  xxl  job  -----parm is:" + s);
        return SUCCESS;
    }
}
