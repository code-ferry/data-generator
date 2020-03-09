package com.newland.dg.field;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.string.StringField;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @author huangxw
 * @date 2019-11-19
 */
public class StringFieldTest {
    private static final Logger logger = LoggerFactory.getLogger(StringFieldTest.class);

    @Test
    public void basicTest() {
        List<String> lstPreDefine = Arrays.asList(new String[]{"1", "2", "3"});
        StringField stringField = StringField.builder().fixedLength(10).preDefineChars(lstPreDefine).build();
        AbstractFieldInfo abstractFieldInfo = stringField;
        abstractFieldInfo.init();

        for (int i = 0; i < 10; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }

    @Test
    public void minMaxLengthTest() {
        List<String> lstPreDefine = Arrays.asList(new String[]{"1", "2", "3"});
        StringField stringField = StringField.builder().minLength(6).maxLength(10).preDefineChars(lstPreDefine).build();
        AbstractFieldInfo fieldInfo = stringField;
        fieldInfo.init();

        for (int i = 0; i < 10; ++i) {
            String ret = fieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }

    @Test
    public void basicWithUserDefineTest() {
        List<String> lstPreDefine = Arrays.asList(new String[]{"1", "2", "3"});
        String userDefineChars = ",|#";
        StringField stringField = StringField.builder()
                                             .minLength(6)
                                             .maxLength(10)
                                             .preDefineChars(lstPreDefine)
                                             .userDefineChars(userDefineChars)
                                             .build();
        AbstractFieldInfo fieldInfo = stringField;
        fieldInfo.init();

        for (int i = 0; i < 10; ++i) {
            String ret = fieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }
}
