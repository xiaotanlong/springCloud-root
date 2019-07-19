package com.example.rocketmq.rocketmq.listener;


import com.example.rocketmq.rocketmq.bean.CountDownTest;
import com.example.rocketmq.rocketmq.service.SendDemo;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * @date 2019/3/12 15:55
 *
 *
 * executeLocalTransaction方法中执行本地事务逻辑。

checkLocalTransaction方法执行事务回查逻辑。

UNKNOW状态：表示事务消息未确定，可能是业务方执行本地事务逻辑时间耗时过长或者网络原因等引起的，
    该状态会导致broker对事务消息进行回查，
    默认回查总次数是15次，
    第一次回查间隔时间是6s，
    后续每次间隔60s,
ROLLBACK状态，该状态表示该事务消息被回滚，因为本地事务逻辑执行失败导致
COMMIT状态，表示事务消息被提交，会被正确分发给消费者。


 回查可以将其他业务  事务状态放在  redis  或者其他  地方 存放一个标识  要是没有标识，在查db  在确认是回滚或者提交

 */
@RocketMQTransactionListener(txProducerGroup ="test")
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    Logger log = LoggerFactory.getLogger(TransactionListenerImpl.class);

     public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // ... local transaction process, return bollback, commit or unknown
         log.info("TransactionListenerImpl 2 ==============executeLocalTransaction message {}",msg.getPayload());
         String key = String.valueOf(arg);
         CountDownTest tt = CountDownTest.getObj(key);
         log.info("==============kkkk  {},{}",tt,key);
         try{
            Thread.sleep(8000);
          }catch (Exception e){

          }finally {
             try {
                 tt.countDown();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
       // int i = 1/0;
         tt.setResurt("ok");
         log.info("TransactionListenerImpl 2 ======COMMIT");
        return RocketMQLocalTransactionState.COMMIT;
     }


    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        // ... check transaction status and return bollback, commit or unknown
        log.info("============checkLocalTransaction message {}",msg.getPayload());
        return RocketMQLocalTransactionState.COMMIT;
    }
}
