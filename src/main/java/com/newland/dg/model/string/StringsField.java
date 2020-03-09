package com.newland.dg.model.string;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.FieldType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangxw
 * @date 2019-11-17
 */
public class StringsField extends AbstractFieldInfo {
    private static final Logger logger = LoggerFactory.getLogger(StringsField.class);

    /**
     * 自定义的字符集合。
     */
    @Getter
    @Setter
    private String userDefineString;

    // 内部属性
    @Getter
    private String[] arrDefineString;
    private int size;

    public StringsField() {
        super.type = FieldType.STRINGS.getName();
    }

    @Builder(toBuilder = true)
    public StringsField(String userDefineString) {
        this.userDefineString = userDefineString;
        super.type = FieldType.STRINGS.getName();
    }

    @Override
    public void init() {
        checkFixedLength();
        buildStringArray();
    }

    @Override
    public String handle() {
        int i = RandomUtils.nextInt(0, size);
        return arrDefineString[i];
    }

    private void checkFixedLength() {
        if (StringUtils.isEmpty(userDefineString)) {
            throw new RuntimeException("userDefineString为空");
        }
    }

    private void buildStringArray() {
        if (StringUtils.isEmpty(userDefineString)) {
            throw new RuntimeException("自定义的字符集合为空.");
        }

        arrDefineString = StringUtils.split(userDefineString, ",");
        size = arrDefineString.length;
    }
}
