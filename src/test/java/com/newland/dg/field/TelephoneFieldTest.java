package com.newland.dg.field;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.business.TelephoneField;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangxw
 * @date 2019-11-19
 */
public class TelephoneFieldTest {
    private static final Logger logger = LoggerFactory.getLogger(TelephoneFieldTest.class);

    @Test
    public void basicTest() {
        TelephoneField telephoneField = TelephoneField.builder().build();
        AbstractFieldInfo abstractFieldInfo = telephoneField;
        abstractFieldInfo.init();

        for (int i = 0; i < 10; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }

}
