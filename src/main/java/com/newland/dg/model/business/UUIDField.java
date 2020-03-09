package com.newland.dg.model.business;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.FieldType;
import lombok.Builder;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * @author huangxw
 * @date 2019-11-17
 */
@ToString
public class UUIDField extends AbstractFieldInfo {
    private static final Logger logger = LoggerFactory.getLogger(UUIDField.class);

    @Builder(toBuilder = true)
    public UUIDField() {
        super.type = FieldType.UUID.getName();
    }

    @Override
    public void init() {
    }

    @Override
    protected String handle() {
        String s = UUID.randomUUID().toString();
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }
}
