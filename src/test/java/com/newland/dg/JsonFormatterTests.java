package com.newland.dg;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.EmptyField;
import com.newland.dg.model.FileInfo;
import com.newland.dg.model.number.LongField;
import com.newland.dg.model.string.StringField;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonFormatterTests {
    private static final Logger logger = LoggerFactory.getLogger(JsonFormatterTests.class);

    @Test
    public void basicInfo() {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setRecordCount(1000);
        fileInfo.setFileName("testfile.txt");
        fileInfo.setFilePath(".");
        fileInfo.setFieldSeparator(",");
        fileInfo.setLineSeparator("/r/n");
        fileInfo.setIsAppend(false);
        fileInfo.setRunningTimes(1);
        fileInfo.setParallelDegree(1);

        String strFileInfo = JSON.toJSONString(fileInfo, SerializerFeature.PrettyFormat, SerializerFeature.UseSingleQuotes);
        logger.info("☆☆☆ strFileInfo = {}", strFileInfo);
    }

    @Test
    public void basicRowInfo() {
        // fieldInfos
        List<AbstractFieldInfo> fieldInfos = new ArrayList<>();
        AbstractFieldInfo fieldInfo1 = StringField.builder().fixedLength(5).preDefineChars(Arrays.asList("1")).build();
        AbstractFieldInfo fieldInfo2 = StringField.builder().fixedLength(3).preDefineChars(Arrays.asList("2")).build();
        AbstractFieldInfo fieldInfo3 = StringField.builder().fixedLength(2).preDefineChars(Arrays.asList("3")).build();
        fieldInfos.add(fieldInfo1);
        fieldInfos.add(fieldInfo2);
        fieldInfos.add(fieldInfo3);

        // fileInfo
        FileInfo fileInfo = new FileInfo();
        fileInfo.setRecordCount(1000);
        fileInfo.setFileName("testfile.txt");
        fileInfo.setFilePath(".");
        fileInfo.setFieldSeparator(",");
        fileInfo.setLineSeparator("/r/n");
        fileInfo.setIsAppend(false);
        fileInfo.setRunningTimes(1);
        fileInfo.setParallelDegree(1);
        fileInfo.setFieldInfos(fieldInfos);

        // SerializerFeature.UseSingleQuotes
        String strFileInfo = JSON.toJSONString(fileInfo, SerializerFeature.PrettyFormat);
        logger.info("☆☆☆ strFileInfo = {}", strFileInfo);
    }

    @Test
    public void mutilFieldInfo() {
        // fieldInfos
        List<AbstractFieldInfo> fieldInfos = new ArrayList<>();
        AbstractFieldInfo fieldInfo1 = StringField.builder().fixedLength(5).preDefineChars(Arrays.asList("1")).build();
        AbstractFieldInfo fieldInfo2 = StringField.builder().fixedLength(3).preDefineChars(Arrays.asList("2")).build();
        AbstractFieldInfo fieldInfo3 = LongField.builder().minValue(1L).maxValue(1000L).build();
        AbstractFieldInfo fieldInfo4 = EmptyField.builder().build();
        fieldInfos.add(fieldInfo1);
        fieldInfos.add(fieldInfo2);
        fieldInfos.add(fieldInfo3);
        fieldInfos.add(fieldInfo4);

        // fileInfo
        FileInfo fileInfo = new FileInfo();
        fileInfo.setRecordCount(1000);
        fileInfo.setFileName("testfile.txt");
        fileInfo.setFilePath(".");
        fileInfo.setFieldSeparator(",");
        fileInfo.setLineSeparator("/r/n");
        fileInfo.setIsAppend(false);
        fileInfo.setRunningTimes(1);
        fileInfo.setParallelDegree(1);
        fileInfo.setFieldInfos(fieldInfos);

        // SerializerFeature.UseSingleQuotes
        String strFileInfo = JSON.toJSONString(fileInfo, SerializerFeature.PrettyFormat);
        logger.info("☆☆☆ strFileInfo = {}", strFileInfo);
    }

    @Test
    public void abcTest() {
        List<String> preDefineChars = Arrays.asList("o1");
        StringField stringField = StringField.builder().fixedLength(5).preDefineChars(preDefineChars).build();
    }
}
