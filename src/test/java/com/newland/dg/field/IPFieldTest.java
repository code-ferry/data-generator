package com.newland.dg.field;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.business.IPField;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangxw
 * @date 2019-11-19
 */
public class IPFieldTest {
    private static final Logger logger = LoggerFactory.getLogger(IPFieldTest.class);

    @Test
    public void basicTest() {
        IPField ipField = IPField.builder().build();
        AbstractFieldInfo abstractFieldInfo = ipField;
        abstractFieldInfo.init();

        for (int i = 0; i < 10; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }
}
