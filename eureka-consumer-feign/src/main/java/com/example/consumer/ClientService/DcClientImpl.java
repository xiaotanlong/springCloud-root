package com.example.consumer.ClientService;

import org.springframework.stereotype.Component;

/**
 * @author 0217319
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * @date
 */
@Component
public class DcClientImpl implements DcClient {
    @Override
    public String consumer() {
        return "error -----";
    }
}
