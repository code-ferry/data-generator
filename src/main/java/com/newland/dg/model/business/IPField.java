package com.newland.dg.model.business;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.FieldType;
import lombok.Builder;
import lombok.ToString;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @author huangxw
 * @date 2019-11-17
 */
@ToString
public class IPField extends AbstractFieldInfo {
    private static final Logger logger = LoggerFactory.getLogger(IPField.class);

    private static final int[][] range = {{607649792, 608174079},//36.56.0.0-36.63.255.255
            {1038614528, 1039007743},//61.232.0.0-61.237.255.255
            {1783627776, 1784676351},//106.80.0.0-106.95.255.255
            {2035023872, 2035154943},//121.76.0.0-121.77.255.255
            {2078801920, 2079064063},//123.232.0.0-123.235.255.255
            {-1950089216, -1948778497},//139.196.0.0-139.215.255.255
            {-1425539072, -1425014785},//171.8.0.0-171.15.255.255
            {-1236271104, -1235419137},//182.80.0.0-182.92.255.255
            {-770113536, -768606209},//210.25.0.0-210.47.255.255
            {-569376768, -564133889}, //222.16.0.0-222.95.255.255
    };

    @Builder(toBuilder = true)
    public IPField() {
        super.type = FieldType.IP.getName();
    }

    @Override
    public void init() {
    }

    @Override
    protected String handle() {
        int index = RandomUtils.nextInt(0, 10);

        int offset = new Random().nextInt(range[index][1] - range[index][0]);
        return num2ip(range[index][0] + offset);
    }

    private String num2ip(int num) {
        int[] b = new int[4];
        b[0] = (int) ((num >> 24) & 0xff);
        b[1] = (int) ((num >> 16) & 0xff);
        b[2] = (int) ((num >> 8) & 0xff);
        b[3] = (int) (num & 0xff);

        StringBuffer sb = new StringBuffer(20).append(b[0]).append(".").append(b[1]).append(".").append(b[2]).append(".").append(b[3]);
        return sb.toString();
    }
}
