package com.enjoy.cap8.bean;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 解析配置文件  获取值
 * 读取sping加载的配置文件中的属性    和jvm系统变量
 * @date 2018/11/19 16:50
 */
@Component
public class EmbeddedValueResolverAwareAchieve implements EmbeddedValueResolverAware{
    private StringValueResolver resolver;
    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.resolver = stringValueResolver;
    }

    /**
     * 获取属性，直接传入属性名称即可
     * @param key
     * @return
     */
    public String getPropertiesValue(String key) {
        StringBuilder name = new StringBuilder("${").append(key).append("}");
        return resolver.resolveStringValue(name.toString());
    }
}
