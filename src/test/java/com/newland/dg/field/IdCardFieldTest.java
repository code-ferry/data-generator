package com.newland.dg.field;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.business.IdCardField;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangxw
 * @date 2019-11-19
 */
public class IdCardFieldTest {
    private static final Logger logger = LoggerFactory.getLogger(IdCardFieldTest.class);

    @Test
    public void basicTest() {
        IdCardField idCardField = IdCardField.builder().build();
        AbstractFieldInfo abstractFieldInfo = idCardField;
        abstractFieldInfo.init();

        for (int i = 0; i < 10; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }
}
