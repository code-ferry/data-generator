package com.newland.dg.model.number;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.FieldType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
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
public class DoubleField extends AbstractFieldInfo {
    private static final Logger logger = LoggerFactory.getLogger(DoubleField.class);

    @Getter
    @Setter
    private Double minValue;

    @Getter
    @Setter
    private Double maxValue;

    @Getter
    @Setter
    private Integer decimalLength = 2;

    public DoubleField() {
        super.type = FieldType.DOUBLE.getName();
    }

    @Builder(toBuilder = true)
    public DoubleField(Double minValue, Double maxValue, Integer decimalLength) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        super.type = FieldType.DOUBLE.getName();
    }

    @Override
    public void init() {
        if (decimalLength < 1) {
            throw new RuntimeException("小数位数不能小于1!");
        }
    }

    @Override
    protected String handle() {
        Double rndNum = RandomUtils.nextDouble(minValue, maxValue);
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(decimalLength);
        numberFormat.setMinimumFractionDigits(decimalLength);
        numberFormat.setGroupingUsed(false);

        return numberFormat.format(rndNum);
    }
}
