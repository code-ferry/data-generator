package com.newland.dg.field;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.business.ChineseNameField;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangxw
 * @date 2019-11-19
 */
public class ChineseNameFieldTest {
    private static final Logger logger = LoggerFactory.getLogger(ChineseNameFieldTest.class);

    @Test
    public void basicTest() {
        ChineseNameField chineseNameField = ChineseNameField.builder().build();
        AbstractFieldInfo abstractFieldInfo = chineseNameField;
        abstractFieldInfo.init();

        for (int i = 0; i < 10000; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }
}
