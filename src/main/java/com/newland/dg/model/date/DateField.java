package com.newland.dg.model.date;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.FieldType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author huangxw
 * @date 2019-11-17
 */
@ToString
public class DateField extends AbstractFieldInfo {
    private static final Logger logger = LoggerFactory.getLogger(DateField.class);
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Setter
    @Getter
    private String startTime = "2019-01-01 00:00:00";

    @Setter
    @Getter
    private String endTime = "2019-12-31 23:59:59";

    @Setter
    @Getter
    private String dateFormat = "yyyy-MM-dd hh:mm:ss";

    // 内部属性
    private SimpleDateFormat outputSimpleDateFormat;
    private long lStartTime;
    private long lEndTime;

    public DateField() {
        super.type = FieldType.DATE.getName();
    }

    @Builder(toBuilder = true)
    public DateField(String startTime, String endTime, String dateFormat) {
        this.startTime = StringUtils.isEmpty(startTime) ? this.startTime : startTime;
        this.endTime = StringUtils.isEmpty(endTime) ? this.endTime : endTime;
        this.dateFormat = StringUtils.defaultString(dateFormat, this.dateFormat);
        super.type = FieldType.DATE.getName();
    }

    @Override
    public synchronized void init() {
        try {
            Date dateStartTime = simpleDateFormat.parse(startTime);
            Date dateEndTime = simpleDateFormat.parse(endTime);
            lStartTime = dateStartTime.getTime();
            lEndTime = dateEndTime.getTime();
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("startTime或者endTime时间格式解析有误!");
        }

        outputSimpleDateFormat = new SimpleDateFormat(dateFormat);
    }

    @Override
    public synchronized String handle() {
        long l = RandomUtils.nextLong(lStartTime, lEndTime + 1000);
        Date d = new Date(l);

        return outputSimpleDateFormat.format(d);
    }
}
