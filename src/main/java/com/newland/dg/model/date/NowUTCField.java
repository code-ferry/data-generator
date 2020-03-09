package com.newland.dg.model.date;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.FieldType;
import lombok.Builder;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangxw
 * @date 2019-11-17
 */
@ToString
public class NowUTCField extends AbstractFieldInfo {
    private static final Logger logger = LoggerFactory.getLogger(NowUTCField.class);

    @Builder(toBuilder = true)
    public NowUTCField() {
        super.type = FieldType.NOWUTC.getName();
    }

    @Override
    public synchronized void init() {
    }

    @Override
    public synchronized String handle() {
        long utc = System.currentTimeMillis();
        return String.valueOf(utc);
    }
}
