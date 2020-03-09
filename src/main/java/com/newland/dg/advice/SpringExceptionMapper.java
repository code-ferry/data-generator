package com.newland.dg.advice;

import com.newland.dg.model.RespInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常的处理器, 基于springmvc的情况
 */
@ControllerAdvice
public class SpringExceptionMapper implements EnvironmentAware {
    private static final Logger logger = LoggerFactory.getLogger(SpringExceptionMapper.class);

    /**
     * 得到异常日志级别, 以及是否开启异常日志内容
     *
     * @param env 环境变量
     */
    @Override
    public void setEnvironment(Environment env) {
    }

    /**
     * springmvc时, 错误的统一处理.
     *
     * @param e 捕获到的异常信息, 包括unchecked与checked
     * @return 错误访问的结构, 系统统一规定
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RespInfo handleError(Exception e) {
        logger.error(e.getMessage(), e);
        return new RespInfo("error", e.getMessage());
    }
}