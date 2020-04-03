package com.enjoy.cap8.bean;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import javax.annotation.PostConstruct;

/***
 * MessageSource实现国际化
 */
public class MessageLoadAwareTest implements MessageSourceAware {
    private MessageSource messageSource;
    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @PostConstruct
    public void init(){

    }
}
