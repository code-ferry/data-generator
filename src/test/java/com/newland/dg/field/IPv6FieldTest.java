package com.newland.dg.field;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.business.IPv6Field;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangxw
 * @date 2019-11-19
 */
public class IPv6FieldTest {
    private static final Logger logger = LoggerFactory.getLogger(IPv6FieldTest.class);

    @Test
    public void basicTest() {
        IPv6Field ipv6Field = IPv6Field.builder().build();
        AbstractFieldInfo abstractFieldInfo = ipv6Field;
        abstractFieldInfo.init();

        for (int i = 0; i < 10; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }
}
