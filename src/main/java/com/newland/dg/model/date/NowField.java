package com.newland.dg.model.date;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.FieldType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author huangxw
 * @date 2019-11-17
 */
@ToString
public class NowField extends AbstractFieldInfo {
    private static final Logger logger = LoggerFactory.getLogger(NowField.class);

    @Setter
    @Getter
    private String dateFormat = "yyyy-MM-dd hh:mm:ss.SSS";

    // 内部属性
    private SimpleDateFormat outputSimpleDateFormat;

    public NowField() {
        super.type = FieldType.NOW.getName();
    }

    @Builder(toBuilder = true)
    public NowField(String dateFormat) {
        this.dateFormat = StringUtils.defaultString(dateFormat, this.dateFormat);
        super.type = FieldType.NOW.getName();
    }

    @Override
    public synchronized void init() {
        outputSimpleDateFormat = new SimpleDateFormat(dateFormat);
    }

    @Override
    public synchronized String handle() {
        long utc = System.currentTimeMillis();
        Date d = new Date(utc);

        return outputSimpleDateFormat.format(d);
    }
}
