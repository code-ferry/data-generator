package com.newland.dg.field;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.business.LoadNameField;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangxw
 * @date 2019-11-19
 */
public class LoadNameFieldTest {
    private static final Logger logger = LoggerFactory.getLogger(LoadNameFieldTest.class);

    @Test
    public void basicTest() {
        LoadNameField loadField = LoadNameField.builder().build();
        AbstractFieldInfo abstractFieldInfo = loadField;
        abstractFieldInfo.init();

        for (int i = 0; i < 10000; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }
}
