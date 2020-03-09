package com.newland.dg.utils;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huangxw
 * @date 2019-11-19
 */
public class GroovyUtils {
    private static final Logger logger = LoggerFactory.getLogger(GroovyUtils.class);
    private Binding binding = new Binding();
    private GroovyShell shell;
    private Script script;

    // 匹配变量的表达式
    private String regex = "\\$\\{(.+?)\\}";
    private Pattern pattern = Pattern.compile(regex);

    public GroovyUtils(Map<String, String> mapVariable) {
        if (mapVariable != null) {
            for (Map.Entry<String, String> entry : mapVariable.entrySet()) {
                binding.setVariable(entry.getKey(), entry.getValue());
            }
        }

        this.shell = new GroovyShell(this.binding, FunctionUtils.getConfiguration());
    }

    public String run(String expression) throws Exception {
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            String variableWithPlaceholder = matcher.group(0);
            String variable = matcher.group(1);
            // 计算中变量所代表的表达式的计算结果。
            String valueByVariable = calculateVariable(variable);

            logger.debug("☆☆☆ variableWithPlaceholder = {}", variableWithPlaceholder);
            logger.debug("☆☆☆ variable = {}", variable);
            logger.debug("☆☆☆ valueByVariable = {}", valueByVariable);

            expression = StringUtils.replaceOnce(expression, variableWithPlaceholder, valueByVariable);
        }

        return expression;
    }

    /**
     * 得到变量的实际值（变量->变量表达式->运算结果）
     *
     * @param variable 变量
     * @return 变量的运算结果
     */
    private String calculateVariable(String variable) {
        Object variableExpression = shell.evaluate(variable);
        if (variableExpression == null) {
            throw new RuntimeException("变量'" + variable + "'非法。");
        }

        Object result = shell.evaluate(variableExpression.toString());
        if (result == null) {
            throw new RuntimeException("变量表达式'" + variable + "'异常。");
        }

        return result.toString();
    }
}
