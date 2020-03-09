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
@ToString
public class BooleanField extends AbstractFieldInfo {
    private static final Logger logger = LoggerFactory.getLogger(BooleanField.class);

    @Setter
    @Getter
    private boolean useNumber = false;

    @Setter
    @Getter
    private String caseType = "lower";

    public BooleanField() {
        super.type = FieldType.BOOLEAN.getName();
    }

    @Builder(toBuilder = true)
    public BooleanField(Boolean useNumber, String caseType) {
        this.useNumber = useNumber;
        this.caseType = caseType;
        super.type = FieldType.BOOLEAN.getName();
    }

    @Override
    public void init() {
    }

    @Override
    protected String handle() {
        boolean b = RandomUtils.nextBoolean();

        if (useNumber) {
            return b ? "1" : "0";
        } else {
            if (StringUtils.equalsIgnoreCase(caseType, "upper")) {
                return Boolean.toString(b).toUpperCase();
            } else if (StringUtils.equalsIgnoreCase(caseType, "capital")) {
                return StringUtils.capitalize(Boolean.toString(b));
            } else {
                return Boolean.toString(b);
            }
        }
    }
}
