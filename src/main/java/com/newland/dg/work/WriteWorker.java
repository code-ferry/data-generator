package com.newland.dg.work;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.FileInfo;
import com.newland.dg.utils.GroovyUtils;
import com.newland.dg.utils.SpringContextUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author huangxw
 * @date 2019-11-20
 */
public class WriteWorker implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(WriteWorker.class);
    private final int id;
    private final FileInfo fileInfo;

    // 内部初始化
    private List<AbstractFieldInfo> fieldInfos;
    private int fieldCount;
    private int recordCount;
    private String fieldSeparator;
    private String lineSeparator;
    private Charset charset;

    public WriteWorker(FileInfo fileInfo) {
        this.id = 0;
        this.fileInfo = fileInfo;
    }

    public WriteWorker(int id, FileInfo fileInfo) {
        this.id = id;
        this.fileInfo = fileInfo;
    }

    @Override
    public void run() {
        // 初始化 部分属性
        fieldInfos = fileInfo.getFieldInfos();
        fieldCount = fieldInfos.size();
        recordCount = fileInfo.getRecordCount();
        fieldSeparator = fileInfo.getFieldSeparator();
        lineSeparator = fileInfo.getLineSeparator();
        charset = getCharset(fileInfo);

        // 初始化 字段对象
        for (int i = 0; i < fieldCount; ++i) {
            AbstractFieldInfo fi = fieldInfos.get(i);
            fi.init();
        }

        // 生成文件目录，以及文件
        String fullFileName = createPathAndFile();

        // 生成记录，并写入文件
        writeLocalFile(fullFileName);
    }

    private void writeLocalFile(String fullFileName) {
        logger.info("☆☆☆ 写入文件'{}'开始。", fullFileName);

        FileWriterWithEncoding fw = null;
        long start = System.currentTimeMillis();
        try {
            fw = new FileWriterWithEncoding(fullFileName, charset, fileInfo.getIsAppend());

            for (int i = 0; i < recordCount; ++i) {
                String strRecord = createRecord().toString();
                logger.debug("☆☆☆ strRecord = {}", strRecord);
                fw.write(strRecord);
            }

            fw.flush();
            fw.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("写文件时出现问题: " + e.getMessage());
        } finally {
            IOUtils.closeQuietly(fw);
        }
        long end = System.currentTimeMillis();

        logger.info("☆☆☆ 写入文件'{}'完成，用时'{}'秒。", fullFileName, (end - start) / 1000);
    }

    /**
     * @param fieldInfos 字段集信息
     * @param fieldCount 字段数
     * @return
     */
    private StringBuffer createRecord() {
        StringBuffer sbRow = new StringBuffer(500);

        for (int i = 0; i < fieldCount - 1; ++i) {
            AbstractFieldInfo fi = fieldInfos.get(i);
            // 生成字段部分的随机数
            StringBuffer sbField = fi.run().append(fieldSeparator);
            sbRow.append(sbField);
        }

        // 最后一个字段
        {
            AbstractFieldInfo fi = fieldInfos.get(fieldCount - 1);
            StringBuffer sbField = fi.run().append(lineSeparator);

            sbRow.append(sbField);
        }

        sbRow.trimToSize();
        return sbRow;
    }

    private String createPathAndFile() {
        String fileName = fileInfo.getFileName();
        String filePath = fileInfo.getFilePath();

        // 1. 简单的校验过程
        if (!StringUtils.endsWith(filePath, File.separator)) {
            filePath = filePath + File.separator;
        }

        // 2. 解析文件目录或文件名中的表达式
        GroovyUtils groovyUtils = SpringContextUtils.getBean(GroovyUtils.class);
        try {
            fileName = groovyUtils.run(fileName);
            filePath = groovyUtils.run(filePath);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("文件目录或文件名的表达式解析出错!");
        }

        // 3. 判断目录是否存在，以及是否能创建
        {
            File fileDir = new File(filePath);
            if (!fileDir.exists()) {
                try {
                    FileUtils.forceMkdir(fileDir);
                } catch (IOException e) {
                    throw new RuntimeException("创建目录失败");
                }
            }

            if (!fileDir.isDirectory()) {
                throw new RuntimeException("提供的路径不是目录");
            }
        }

        // 4. 文件名生成，有并行时要加上id
        String fullFileName = filePath + fileName;
        if (fileInfo.getParallelDegree() > 1) {
            String strFilenameWithoutPrefix = StringUtils.substringBeforeLast(fullFileName, ".");
            String strPrefix = StringUtils.substringAfterLast(fullFileName, ".");

            fullFileName = strFilenameWithoutPrefix + "-" + id + "." + strPrefix;
        }

        return fullFileName;
    }

    private Charset getCharset(FileInfo fileInfo) {
        String fileCharset = fileInfo.getFileCharset();
        if (StringUtils.equalsIgnoreCase(fileCharset, "UTF-8")) {
            return StandardCharsets.UTF_8;
        } else if (StringUtils.equalsIgnoreCase(fileCharset, "gbk")) {
            return Charset.forName("GBK");
        } else {
            return StandardCharsets.UTF_8;
        }
    }
}
