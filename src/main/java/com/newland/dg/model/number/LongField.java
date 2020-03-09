package com.newland.dg.model.number;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.FieldType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangxw
 * @date 2019-11-17
 */
@ToString(exclude = {"isPadding"})
public class LongField extends AbstractFieldInfo {
    private static final Logger logger = LoggerFactory.getLogger(LongField.class);

    @Getter
    @Setter
    private Long minValue;

    @Getter
    @Setter
    private Long maxValue;

    @Getter
    @Setter
    private Integer fixedLength;

    @Getter
    @Setter
    private String paddingPosition = "l";

    @Getter
    @Setter
    private String paddingChar = " ";

    /**
     * 下面都是内部属性
     */
    private boolean isPadding = false;

    public LongField() {
        super.type = FieldType.LONG.getName();
    }

    @Builder(toBuilder = true)
    public LongField(Long minValue, Long maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        super.type = FieldType.LONG.getName();
    }

    @Override
    public void init() {
        if (fixedLength == null || fixedLength == 0) {
            fixedLength = 0;
            isPadding = false;
        } else {
            isPadding = true;
        }

        if (fixedLength < 0) {
            throw new RuntimeException("固定长度不能为负数!");
        }

        if (isPadding) {
            int maxLength = maxValue.toString().length();
            if (maxLength > fixedLength) {
                throw new RuntimeException("最大值的长度已经超过固定长度值的设置!");
            }
        }
    }

    @Override
    protected String handle() {
        Long rndNum = RandomUtils.nextLong(minValue, maxValue);

        if (isPadding) {
            if (StringUtils.equalsIgnoreCase(paddingPosition, "l")) {
                return StringUtils.leftPad(rndNum.toString(), fixedLength, paddingChar);
            } else if (StringUtils.equalsIgnoreCase(paddingPosition, "r")) {
                return StringUtils.rightPad(rndNum.toString(), fixedLength, paddingChar);
            } else {
                throw new RuntimeException("paddingPosition参数只能是'r'或者'l'");
            }
        } else {
            return rndNum.toString();
        }
    }
}
