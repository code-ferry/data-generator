package com.newland.dg.field;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.number.BooleanField;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangxw
 * @date 2019-11-19
 */
public class BooleanFieldTest {
    private static final Logger logger = LoggerFactory.getLogger(BooleanFieldTest.class);

    @Test
    public void basicTest() {
        // upper, capital
        BooleanField booleanField = BooleanField.builder().useNumber(true).caseType("upper").build();
        AbstractFieldInfo abstractFieldInfo = booleanField;
        abstractFieldInfo.init();

        for (int i = 0; i < 10; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }
}
