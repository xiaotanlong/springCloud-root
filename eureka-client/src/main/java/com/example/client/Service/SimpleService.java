package com.example.client.Service;

import com.example.log.annotation.Log;
import org.springframework.stereotype.Service;

/**
 * @ProjectName springcloudroot
 * @PackageName com.example.client.Service
 * @Author tanjianglong
 * @CreatedTime 2017/9/21.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
@Service
public class SimpleService {

    @Log
    public void test(int num) {
        System.out.println("----test---- " + num);
    }

    @Log
    public void core(int num) {
        System.out.println("----core---- " + num);
    }

    public void work(int num) {
        System.out.println("----work---- " + num);
    }

}
