package com.newland.dg;

import com.newland.dg.utils.GroovyUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huangxw
 * @date 2019-11-21
 */
public class GroovyUtilsTests {

    @Test
    public void dateVarTest() throws Exception {
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

        String strExpression = "testfile-${YYYYMMDD}-${DD}.txt";
        String result = groovyUtils.run(strExpression);
        System.out.println("result = " + result);

        // check whether contains placerholder
        Pattern compile = Pattern.compile("\\$\\{(.*?)\\}");
        Matcher matcher = compile.matcher(result);
        Assert.assertFalse(matcher.find());
    }
}
