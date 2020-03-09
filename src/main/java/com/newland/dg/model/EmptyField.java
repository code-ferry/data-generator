package com.newland.dg.model;

import lombok.Builder;

/**
 * @author huangxw
 * @date 2019-11-17
 */
public class EmptyField extends AbstractFieldInfo {

    @Builder(toBuilder = true)
    public EmptyField() {
        super.type = FieldType.EMPTY.getName();
    }

    @Override
    public void init() {
    }

    @Override
    protected String handle() {
        return "";
    }
}
