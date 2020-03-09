package com.newland.dg.field;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.string.StringsField;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangxw
 * @date 2019-11-19
 */
public class StringsFieldTest {
    private static final Logger logger = LoggerFactory.getLogger(StringsFieldTest.class);

    @Test
    public void basicTest() {
        StringsField stringsField = StringsField.builder().userDefineString("aa,bb,cc,dd,ee,ff").build();
        AbstractFieldInfo abstractFieldInfo = stringsField;
        abstractFieldInfo.init();

        for (int i = 0; i < 10; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }
}
