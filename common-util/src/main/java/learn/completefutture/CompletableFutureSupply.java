package learn.completefutture;

/**
 * @author tjl
 * @version V1.0
 * @Title: 创造和获取CompletableFuture
 * @Package learn
 * @Description: 创造和获取CompletableFuture learn(用一句话描述该文件做什么)
 * @date 2018-4-3
 */
/*
CompletableFuture类实现了CompletionStage和Future接口。

Future是Java 5添加的类，用来描述一个异步计算的结果，但是获取一个结果时方法较少,要么通过轮询isDone，
    确认完成后，调用get()获取值，要么调用get()设置一个超时时间。
    但是这个get()方法会阻塞住调用线程，这种阻塞的方式显然和我们的异步编程的初衷相违背。
为了解决这个问题，JDK吸收了guava的设计思想，加入了Future的诸多扩展功能形成了CompletableFuture。

CompletionStage是一个接口，从命名上看得知是一个完成的阶段，它里面的方法也标明是在某个运行阶段得到了结果之后要做的事情。
 */
public class CompletableFutureSupply {


}
