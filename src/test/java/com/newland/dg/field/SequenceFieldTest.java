package com.newland.dg.field;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.number.SequenceField;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangxw
 * @date 2019-11-19
 */
public class SequenceFieldTest {
    private static final Logger logger = LoggerFactory.getLogger(SequenceFieldTest.class);

    @Test
    public void basicTest() {
        SequenceField sequenceField = SequenceField.builder().minValue(10L).maxValue(100L).stepValue(2).build();
        AbstractFieldInfo abstractFieldInfo = sequenceField;
        abstractFieldInfo.init();

        for (int i = 0; i < 100; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }
}
