package learn.threadcallback;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: 返回定义
 * @date 2019/1/18 10:45
 */
public enum TaskResultType {
	//方法成功执行并返回了业务人员需要的结果
	Success,
	//方法成功执行但是返回的是业务人员不需要的结果
	Failure,
	//方法执行抛出了Exception
	Exception;
}
