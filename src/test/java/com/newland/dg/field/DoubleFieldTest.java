package com.newland.dg.field;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.number.DoubleField;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangxw
 * @date 2019-11-19
 */
public class DoubleFieldTest {
    private static final Logger logger = LoggerFactory.getLogger(DoubleFieldTest.class);

    @Test
    public void basicTest() {
        DoubleField doubleField = DoubleField.builder().minValue(10.0).maxValue(10000.5).decimalLength(3).build();
        AbstractFieldInfo abstractFieldInfo = doubleField;
        abstractFieldInfo.init();

        for (int i = 0; i < 10; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }

}
