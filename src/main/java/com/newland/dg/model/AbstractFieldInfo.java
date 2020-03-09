package com.newland.dg.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.newland.dg.model.business.ChineseNameField;
import com.newland.dg.model.business.IPField;
import com.newland.dg.model.business.IdCardField;
import com.newland.dg.model.business.LoadNameField;
import com.newland.dg.model.business.TelephoneField;
import com.newland.dg.model.business.UUIDField;
import com.newland.dg.model.date.DateField;
import com.newland.dg.model.date.NowField;
import com.newland.dg.model.date.NowUTCField;
import com.newland.dg.model.date.UTC1000Field;
import com.newland.dg.model.date.UTCField;
import com.newland.dg.model.number.BooleanField;
import com.newland.dg.model.number.DoubleField;
import com.newland.dg.model.number.LongField;
import com.newland.dg.model.number.SequenceField;
import com.newland.dg.model.string.StringField;
import com.newland.dg.model.string.StringsField;
import lombok.Getter;
import lombok.Setter;

/**
 * @author huangxw
 * @date 2019-11-16
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(name = "string", value = StringField.class),
        @JsonSubTypes.Type(name = "strings", value = StringsField.class),
        @JsonSubTypes.Type(name = "long", value = LongField.class),
        @JsonSubTypes.Type(name = "double", value = DoubleField.class),
        @JsonSubTypes.Type(name = "sequence", value = SequenceField.class),
        @JsonSubTypes.Type(name = "boolean", value = BooleanField.class),
        @JsonSubTypes.Type(name = "date", value = DateField.class),
        @JsonSubTypes.Type(name = "utc", value = UTCField.class),
        @JsonSubTypes.Type(name = "utc1000", value = UTC1000Field.class),
        @JsonSubTypes.Type(name = "now", value = NowField.class),
        @JsonSubTypes.Type(name = "nowutc", value = NowUTCField.class),
        @JsonSubTypes.Type(name = "ip", value = IPField.class),
        @JsonSubTypes.Type(name = "uuid", value = UUIDField.class),
        @JsonSubTypes.Type(name = "telephone", value = TelephoneField.class),
        @JsonSubTypes.Type(name = "idcard", value = IdCardField.class),
        @JsonSubTypes.Type(name = "chinesename", value = ChineseNameField.class),
        @JsonSubTypes.Type(name = "loadname", value = LoadNameField.class),
        @JsonSubTypes.Type(name = "empty", value = EmptyField.class)})
public abstract class AbstractFieldInfo implements IFieldInfo {
    /**
     * 字段类型
     */
    @Getter
    protected String type;
    /**
     * 前缀
     */
    @Getter
    @Setter
    private String prefix = "";
    /**
     * 后缀
     */
    @Getter
    @Setter
    private String suffix = "";

    /**
     * 返回的最终生成的内容，包括前缀与后缀
     *
     * @return 返回的内容包括前缀与后缀
     */
    public final StringBuffer run() {
        // 调用模板方法handle
        StringBuffer sb = new StringBuffer(500).append(handle());

        if (sb == null) {
            return new StringBuffer(prefix).append(suffix);
        }
        return sb.insert(0, prefix).append(suffix);
    }

    /**
     * 继承类的初始化过程
     */
    public abstract void init();

    /**
     * 继承类要实现的逻辑
     *
     * @return 返回具体实现类完成的随机数
     */
    protected abstract String handle();
}
