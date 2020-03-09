package com.newland.dg.field;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.date.DateField;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangxw
 * @date 2019-11-19
 */
public class DateFieldTest {
    private static final Logger logger = LoggerFactory.getLogger(DateFieldTest.class);

    @Test
    public void basicTest() {
        DateField dateField = DateField.builder().dateFormat("yyyyMMdd").build();
        AbstractFieldInfo abstractFieldInfo = dateField;
        abstractFieldInfo.init();

        for (int i = 0; i < 10; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }

    @Test
    public void rangeTest() {
        DateField dateField = DateField.builder().startTime("2019-10-12 00:00:00").endTime("2019-10-13 23:59:59").dateFormat("yyyyMMdd hh").build();
        AbstractFieldInfo abstractFieldInfo = dateField;
        abstractFieldInfo.init();

        for (int i = 0; i < 10; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }

    @Test
    public void rangeATest() {
        DateField dateField = DateField.builder().startTime("2019-11-01 00:00:00").endTime("2019-11-06 00:00:00").dateFormat("yyyy/MM/dd hh:mm:ss").build();
        AbstractFieldInfo abstractFieldInfo = dateField;
        abstractFieldInfo.init();

        for (int i = 0; i < 10; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }

    @Test
    public void rangeBTest() {
        DateField dateField = DateField.builder().startTime("2019-11-30 00:00:00").endTime("2019-12-02 00:00:00").dateFormat("yyyy/MM/dd hh:mm:ss").build();
        AbstractFieldInfo abstractFieldInfo = dateField;
        abstractFieldInfo.init();

        for (int i = 0; i < 1000; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }

    @Test
    public void rangeCTest() {
        DateField dateField = DateField.builder().startTime("2019-01-01 00:00:00").endTime("2019-12-31 23:59:59").dateFormat("yyyy-MM-dd hh:mm:ss").build();
        AbstractFieldInfo abstractFieldInfo = dateField;
        abstractFieldInfo.init();

        for (int i = 0; i < 1000; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }

    @Test
    public void rangeDTest() {
        DateField dateField = DateField.builder().build();
        AbstractFieldInfo abstractFieldInfo = dateField;
        abstractFieldInfo.init();

        for (int i = 0; i < 1000; ++i) {
            String ret = abstractFieldInfo.run().toString();
            logger.info("☆☆☆ ret = {}", ret);
        }
    }
}
