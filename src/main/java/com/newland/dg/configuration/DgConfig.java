package com.newland.dg.configuration;

import com.newland.dg.utils.GroovyUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huangxw
 * @date 2019-11-21
 */
@Configuration
public class DgConfig {
    
    @Bean
    public GroovyUtils gExpUtils() {
        Map<String, String> mapVariable = new HashMap<>();
        // 日期
        mapVariable.put("YYYYMM", "toChar(now(), 'yyyyMM')");
        mapVariable.put("YYYYMMDD", "toChar(now(), 'yyyyMMdd')");
        mapVariable.put("YYYYMMDDHH", "toChar(now(), 'yyyyMMddHH')");
        mapVariable.put("YYYYMMDDHHmm", "toChar(now(), 'yyyyMMddHHmm')");
        mapVariable.put("YYYYMMDDHHmmss", "toChar(now(), 'yyyyMMddHHmmss')");

        mapVariable.put("MMDDHH", "toChar(now(), 'MMddHH')");
        mapVariable.put("DDHHmm", "toChar(now(), 'DDHHmm')");

        mapVariable.put("MMDD", "toChar(now(), 'MMdd')");
        mapVariable.put("DDHH", "toChar(now(), 'ddHH')");

        // 时间
        mapVariable.put("HHmmss", "toChar(now(), 'HHmmss')");

        mapVariable.put("HHmm", "toChar(now(), 'HHmm')");
        mapVariable.put("mmss", "toChar(now(), 'mmss')");

        // 独立
        mapVariable.put("YYYY", "toChar(now(), 'yyyy')");
        mapVariable.put("MM", "toChar(now(), 'MM')");
        mapVariable.put("DD", "toChar(now(), 'dd')");
        mapVariable.put("HH", "toChar(now(), 'HH')");
        mapVariable.put("mm", "toChar(now(), 'mm')");
        mapVariable.put("ss", "toChar(now(), 'ss')");

        GroovyUtils groovyUtils = new GroovyUtils(mapVariable);

        return groovyUtils;
    }
}
