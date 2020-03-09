package com.newland.dg.model.business;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.FieldType;
import lombok.Builder;
import lombok.ToString;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;

/**
 * @author huangxw
 * @date 2019-11-17
 */
@ToString
public class TelephoneField extends AbstractFieldInfo {
    private static final Logger logger = LoggerFactory.getLogger(TelephoneField.class);
    private static final String[] telPrefix = new String[]{"134", "135", "136", "137", "138", "139", "150", "151", "152", "157", "158", "159", "130", "131", "132", "155", "156", "133", "153", "189"};

    // 内部属性
    private int prefixSize;
    private NumberFormat numberFormat;

    @Builder(toBuilder = true)
    public TelephoneField() {
        super.type = FieldType.TELEPHONE.getName();
    }

    @Override
    public void init() {
        prefixSize = telPrefix.length;

        numberFormat = NumberFormat.getIntegerInstance();
        numberFormat.setGroupingUsed(false);
        numberFormat.setMaximumIntegerDigits(8);
        numberFormat.setMinimumIntegerDigits(8);
    }

    @Override
    protected String handle() {
        long l = RandomUtils.nextInt(1, 99999999);
        int prefixIndex = RandomUtils.nextInt(0, prefixSize);
        return telPrefix[prefixIndex] + numberFormat.format(l);
    }
}
