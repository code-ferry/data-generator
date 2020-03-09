package com.newland.dg.field;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.date.UTCField;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangxw
 * @date 2019-11-19
 */
public class UTCFieldTest {
    private static final Logger logger = LoggerFactory.getLogger(UTCFieldTest.class);

    @Test
    public void basicTest() {
        UTCField utcField = UTCField.builder().build();
        AbstractFieldInfo abstractFieldInfo = utcField;
        abstractFieldInfo.init();

        for (int i = 0; i < 10; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }

    @Test
    public void rangeTest() {
        UTCField utcField = UTCField.builder().startTime("2019-10-12 00:00:00").endTime("2019-10-12 00:00:01").build();
        AbstractFieldInfo abstractFieldInfo = utcField;
        abstractFieldInfo.init();

        for (int i = 0; i < 10; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }
}
