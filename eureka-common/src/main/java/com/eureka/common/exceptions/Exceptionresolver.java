package com.eureka.common.exceptions;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: 统一异常处理
 * @date
 */
public class Exceptionresolver implements HandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(Exceptionresolver.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception exception) {
        // 判断是否ajax请求
        if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
                .getHeader("X-Requested-With") != null && request.getHeader(
                "X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
            // 如果不是ajax，JSP格式返回
            // 为安全起见，只有业务异常我们对前端可见，否则否则统一归为系统异常
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", false);
            //这里可以区分是否是自定义异常
            if (exception instanceof IllegalArgumentException) {
                map.put("errorMsg", exception.getMessage());
            } else {
                map.put("errorMsg", "系统异常！");
            }
            //这里需要手动将异常打印出来，由于没有配置log，实际生产环境应该打印到log里面
            logger.info("--------------------exception------------------------" + exception);
            //对于非ajax请求，我们都统一跳转到error.jsp页面
            return new ModelAndView("/error", map);
        } else {
            // 如果是ajax请求，JSON格式返回
            try {
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                Result result = new Result();
                result.setCode(001);
                result.setStatus(false);
                // 为安全起见，只有业务异常我们对前端可见，否则统一归为系统异常
                if (exception instanceof IllegalArgumentException) {
                    result.setMsg(exception.getMessage());
                } else {
                    result.setMsg("系统异常");
                }
                writer.write(JSONObject.toJSONString(result));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 如果是ajax请求，JSON格式返回
        try {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            Result result = new Result();
            result.setCode(002);
            result.setStatus(false);
            // 为安全起见，只有业务异常我们对前端可见，否则统一归为系统异常
            if (exception instanceof IllegalArgumentException) {
                result.setMsg(exception.getMessage());
            } else {
                result.setMsg("系统异常");
            }
            writer.write(JSONObject.toJSONString(result));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            logger.info("--------------------exception------------------------" + exception);
        }
        return null;
    }
}
