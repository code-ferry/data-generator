package com.newland.dg.field;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.date.UTC1000Field;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangxw
 * @date 2019-11-19
 */
public class UTC1000FieldTest {
    private static final Logger logger = LoggerFactory.getLogger(UTC1000FieldTest.class);

    @Test
    public void basicTest() {
        UTC1000Field utc1000Field = UTC1000Field.builder().build();
        AbstractFieldInfo abstractFieldInfo = utc1000Field;
        abstractFieldInfo.init();

        for (int i = 0; i < 10; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }

    @Test
    public void rangeTest() {
        UTC1000Field utc1000Field = UTC1000Field.builder().startTime("2019-10-12 00:00:00").endTime("2019-10-12 00:00:01").build();
        AbstractFieldInfo abstractFieldInfo = utc1000Field;
        abstractFieldInfo.init();

        for (int i = 0; i < 10; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }
}
