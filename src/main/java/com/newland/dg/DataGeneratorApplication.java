package com.newland.dg;

import com.newland.dg.utils.SpringContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DataGeneratorApplication {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(DataGeneratorApplication.class, args);
        SpringContextUtils.setApplicationContext(ac);
    }
}
