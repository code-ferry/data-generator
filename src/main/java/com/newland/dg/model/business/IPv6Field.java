package com.newland.dg.model.business;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.FieldType;
import lombok.Builder;
import lombok.ToString;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangxw
 * @date 2019-11-17
 */
@ToString
public class IPv6Field extends AbstractFieldInfo {
    private static final Logger logger = LoggerFactory.getLogger(IPv6Field.class);

    private String[] arr = new String[8];

    @Builder(toBuilder = true)
    public IPv6Field() {
        super.type = FieldType.IP.getName();
    }

    @Override
    public void init() {
    }

    @Override
    protected String handle() {
        StringBuffer sb = new StringBuffer(40);
        for (int i = 0; i < 8; ++i) {
            int rnd = RandomUtils.nextInt(0, 65536);
            String strHex = Integer.toHexString(rnd);
            sb.append(strHex);

            if (i != 7) {
                sb.append(":");
            }
        }

        return sb.toString();
    }
}
