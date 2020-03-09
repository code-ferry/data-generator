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

/**
 * @author huangxw
 * @date 2019-11-17
 */
@ToString
public class UTCField extends AbstractFieldInfo {
    private static final Logger logger = LoggerFactory.getLogger(UTCField.class);
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Setter
    @Getter
    private String startTime = "2019-01-01 00:00:00";

    @Setter
    @Getter
    private String endTime = "2019-12-31 23:59:59";

    // 内部属性
    private long lStartTime;
    private long lEndTime;

    public UTCField() {
        super.type = FieldType.UTC.getName();
    }

    @Builder(toBuilder = true)
    public UTCField(String startTime, String endTime) {
        this.startTime = StringUtils.isEmpty(startTime) ? this.startTime : startTime;
        this.endTime = StringUtils.isEmpty(endTime) ? this.endTime : endTime;
        super.type = FieldType.UTC.getName();
    }

    @Override
    public synchronized void init() {
        try {
            lStartTime = simpleDateFormat.parse(startTime).getTime();
            lEndTime = simpleDateFormat.parse(endTime).getTime();
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("startTime或者endTime时间格式解析有误!");
        }

        if (lStartTime > lEndTime) {
            throw new RuntimeException("startTime不能大于endTime!");
        }
    }

    @Override
    protected String handle() {
        long l = RandomUtils.nextLong(lStartTime, lEndTime + 1);
        return String.valueOf(l);
    }
}
