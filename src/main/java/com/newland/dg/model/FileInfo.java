package com.newland.dg.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @author huangxw
 * @date 2019-11-18
 */
@Data
public class FileInfo {
    /**
     * 单个文件的记录数
     */
    private Integer recordCount = 1000;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件名，支持表达式
     */
    private String fileName;
    /**
     * 文件编码
     */
    private String fileCharset = "UTF-8";
    /**
     * 字段分隔符
     */
    private String fieldSeparator = ",";
    /**
     * 行分隔符
     */
    private String lineSeparator = "/r/n";
    /**
     * 是否追加
     */
    private Boolean isAppend = false;
    /**
     * 运行次数，可以是大于0的正数，0或者负数表示
     */
    private Integer runningTimes = 1;
    /**
     * 运行时长，分钟做为单位。
     */
    private Integer runningDuration = 0;
    /**
     * 并行度
     */
    private Integer parallelDegree = 1;
    /**
     * 行记录中字段的信息
     */
    private List<AbstractFieldInfo> fieldInfos;
    public FileInfo() {
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
