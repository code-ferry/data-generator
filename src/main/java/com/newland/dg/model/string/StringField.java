package com.newland.dg.model.string;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.FieldType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author huangxw
 * @date 2019-11-17
 */
@ToString(exclude = {"isFixedLength", "arrDefineChars"})
public class StringField extends AbstractFieldInfo {
    private static final Logger logger = LoggerFactory.getLogger(StringField.class);
    /**
     * 小写字母
     */
    private static final Character[] lowers = new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    /**
     * 大写字母
     */
    private static final Character[] uppers = new Character[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    /**
     * 数字
     */
    private static final Character[] digits = new Character[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    @Getter
    @Setter
    private Integer fixedLength = 5;
    @Getter
    @Setter
    private Integer minLength;
    @Getter
    @Setter
    private Integer maxLength;
    /**
     * 预定义字符集合，类型用逗号分隔。
     * 1. 小写字母；2. 大写字母；3. 数值
     */
    @Getter
    @Setter
    private List<String> preDefineChars;
    /**
     * 自定义的字符集合。
     */
    @Getter
    @Setter
    private String userDefineChars;
    /**
     * 下面都是内部属性
     */
    private boolean isFixedLength = true;
    private char[] arrDefineChars;

    public StringField() {
        super.type = FieldType.STRING.getName();
    }

    @Builder(toBuilder = true)
    public StringField(Integer fixedLength, Integer minLength, Integer maxLength, List<String> preDefineChars, String userDefineChars) {
        this.fixedLength = fixedLength;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.preDefineChars = preDefineChars;
        this.userDefineChars = userDefineChars;
        super.type = FieldType.STRING.getName();
    }

    @Override
    public void init() {
        checkFixedLength();
        buildChars();
    }

    @Override
    protected String handle() {
        if (isFixedLength) {
            return RandomStringUtils.random(fixedLength, arrDefineChars);
        }

        int randLength = RandomUtils.nextInt(minLength, maxLength + 1);
        return RandomStringUtils.random(randLength, arrDefineChars);
    }

    private void checkFixedLength() {
        fixedLength = checkNegative(fixedLength);
        minLength = checkNegative(minLength);
        maxLength = checkNegative(maxLength);

        if (fixedLength == 0 && maxLength == 0) {
            throw new RuntimeException("fixedLength与maxLength至少有填写1项.");
        } else if (maxLength > 0 && minLength > maxLength) {
            throw new RuntimeException("maxLength大于minLength.");
        }

        // 区间长度
        if (maxLength > 0) {
            fixedLength = maxLength;
            if (minLength == maxLength) {
                isFixedLength = true;
            } else {
                isFixedLength = false;
            }
            return;
        }
    }

    private Integer checkNegative(Integer i) {
        if (i == null) {
            i = 0;
        }
        return i < 0 ? 0 : i;
    }

    private void buildChars() {
        if (CollectionUtils.isEmpty(preDefineChars)) {
            throw new RuntimeException("预定义字符集合为空.");
        }

        // 1. 预定义字符集合
        List<Character> lstDefineChars = new ArrayList<>();
        Iterator<String> iterator = preDefineChars.iterator();
        while (iterator.hasNext()) {
            String strType = iterator.next();
            // 1：小写字母；2：大写字母；3：数字
            if (StringUtils.equals(strType, "1")) {
                lstDefineChars.addAll(Arrays.asList(lowers));
            } else if (StringUtils.equals(strType, "2")) {
                lstDefineChars.addAll(Arrays.asList(uppers));
            } else if (StringUtils.equals(strType, "3")) {
                lstDefineChars.addAll(Arrays.asList(digits));
            }
        }

        // 2. 自定义字符集合
        if (StringUtils.isNotEmpty(userDefineChars)) {
            char[] arrUserDefineChars = userDefineChars.toCharArray();
            Character[] arrObjectUserDefineChars = ArrayUtils.toObject(arrUserDefineChars);
            lstDefineChars.addAll(Arrays.asList(arrObjectUserDefineChars));
        }

        int charLength = lstDefineChars.size();
        arrDefineChars = new char[lstDefineChars.size()];

        // 3. 生成arrDefineChars
        for (int i = 0; i < charLength; ++i) {
            arrDefineChars[i] = lstDefineChars.get(i);
        }
    }
}
