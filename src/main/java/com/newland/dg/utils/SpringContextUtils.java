package com.newland.dg.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class SpringContextUtils {
    private static final Logger logger = LoggerFactory.getLogger(SpringContextUtils.class);
    private static ApplicationContext ac;

    /**
     * SpringContextUtils构造, 避免通过new来构造.
     */
    private SpringContextUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 初始化, 调置application context
     *
     * @param applicationContext spring context object
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        ac = applicationContext;
    }

    /**
     * 得到ioc容器中的, beanId对应的信息.
     *
     * @param beanId beanId 名称
     * @return beanId 对象
     */
    public static Object getBean(String beanId) {
        if (ac == null) {
            throw new RuntimeException("ApplicationContext is null!");
        }
        return ac.getBean(beanId);
    }

    /**
     * 得到ioc容器中的, beanId对应的信息.
     *
     * @param requiredType 访问的对象的类, class类
     * @param <T>          the parameter of the class, 也就是对象类型的泛型
     * @return beanId 对象
     */
    public static <T> T getBean(Class<T> requiredType) {
        if (ac == null) {
            throw new RuntimeException("ApplicationContext is null!");
        }

        return ac.getBean(requiredType);
    }

    /**
     * 打印bean的名称, 包括bean的数量
     */
    public static void printBean() {
        String[] beanNames = ac.getBeanDefinitionNames();
        logger.info("☆☆☆ beanNames length = {}", beanNames.length);

        for (String bn : beanNames) {
            logger.info(bn);
        }
    }
}