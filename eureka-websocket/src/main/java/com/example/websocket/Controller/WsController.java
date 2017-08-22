package com.example.websocket.Controller;

import com.example.websocket.service.RequestMessage;
import com.example.websocket.service.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @ProjectName springcloudroot
 * @PackageName com.example.websocket.Controller
 * @Author tanjianglong
 * @CreatedTime 2017/8/22.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
@Controller
public class WsController {
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage say(RequestMessage message) {
        System.out.println(message.getName());
        return new ResponseMessage("welcome," + message.getName() + " !");
    }
}
