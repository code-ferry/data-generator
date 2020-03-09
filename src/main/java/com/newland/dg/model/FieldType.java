package com.newland.dg.model;

public enum FieldType {
    // 空类型
    EMPTY("00", "empty", "e"),              // 空类型
    // 数值类型
    LONG("01", "long", "l"),                // 长整型
    DOUBLE("02", "double", "d"),            // 浮点数
    SEQUENCE("03", "sequence", "seq"),      // 序列化数
    BOOLEAN("04", "boolean", "b"),          // 布尔类型
    // 字符串类型
    STRING("10", "string", "s"),            // 字符串
    STRINGS("11", "strings", "ss"),         // 基于特定集合的字符串
    // 时间类型
    DATE("20", "date", "d"),                // 时间类型
    UTC("21", "utc", "utc"),                // UTC
    UTC1000("22", "utc1000", "utc1000"),    // UTC，以秒做单位
    NOW("23", "now", "now"),                // now
    NOWUTC("24", "nowutc", "nowutc"),       // 当前UTC
    // 业务类型
    TELEPHONE("30", "telephone", "tel"),    // 电话
    IP("31", "ip", "ip"),                   // ip地址
    UUID("32", "uuid", "uuid"),             // UUID随机数
    IDCARD("33", "idcard", "ic"),           // 身份证号码
    FJAREA("34", "fjarea", "fa"),           // 福建省地市编码
    CHINESENAME("35", "chinesename", "cn"), // 中文名字
    LOADNAME("36", "loadname", "ln"),       // 常用中文路名
    ;
    private final String id;
    private final String name;
    private final String abbrName;

    private FieldType(String id, String name, String abbrName) {
        this.id = id;
        this.name = name;
        this.abbrName = abbrName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbbrName() {
        return abbrName;
    }

    @Override
    public String toString() {
        return "FieldType{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
    }
}
