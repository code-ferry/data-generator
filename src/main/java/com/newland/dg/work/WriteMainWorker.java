package com.newland.dg.work;

import com.newland.dg.model.FileInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author huangxw
 * @date 2019-11-20
 */
public class WriteMainWorker implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(WriteMainWorker.class);

    private ExecutorService executorService;
    private FileInfo fileInfo;

    public WriteMainWorker(ExecutorService executorService, FileInfo fileInfo) {
        this.executorService = executorService;
        this.fileInfo = fileInfo;
    }

    @Override
    public void run() {
        // 解析文件基础信息
        parseFileBasicInfo(fileInfo);

        // 分两种，一种按次数执行，一种按时长执行
        int runningTimes = fileInfo.getRunningTimes();
        int runningDuration = fileInfo.getRunningDuration();
        if (runningTimes >= 1) {
            executeTimes(fileInfo, runningTimes);
        } else {
            executeDuration(fileInfo, runningDuration);
        }

        logger.info("☆☆☆ 所有文件创建完成!");
    }

    private void executeTimes(FileInfo fileInfo, int runningTimes) {
        for (int i = 0; i < runningTimes; ++i) {
            logger.info("☆☆☆ 当前是第{}次的写入文件", (i + 1));
            int parallelDegree = fileInfo.getParallelDegree();
            List<Future> lstFuture = writeOnceFile(fileInfo, parallelDegree);

            // 同步等待
            for (Future future : lstFuture) {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    logger.error(e.getMessage(), e);
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
    }

    private void executeDuration(FileInfo fileInfo, int runningDuration) {
        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < runningDuration * 60 * 1000) {
            int parallelDegree = fileInfo.getParallelDegree();
            List<Future> lstFuture = writeOnceFile(fileInfo, parallelDegree);

            // 同步等待
            for (Future future : lstFuture) {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    logger.error(e.getMessage(), e);
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
    }

    private List<Future> writeOnceFile(FileInfo fileInfo, int parallelDegree) {
        List<Future> lstFuture = new ArrayList<>();

        if (parallelDegree == 1) {
            WriteWorker writeWorker = new WriteWorker(fileInfo);
            Future future = executorService.submit(writeWorker);
            lstFuture.add(future);
        } else {
            for (int i = 0; i < parallelDegree; ++i) {
                WriteWorker writeWorker = new WriteWorker(i + 1, fileInfo);
                Future future = executorService.submit(writeWorker);
                lstFuture.add(future);
            }
        }

        return lstFuture;
    }

    /**
     * 进行文件基本信息的解析与校验；
     * 这里没有解析文件记录信息
     *
     * @param fileInfo 文件信息
     */
    private void parseFileBasicInfo(FileInfo fileInfo) {
        String fileName = fileInfo.getFileName();
        String filePath = fileInfo.getFilePath();
        String fileCharset = fileInfo.getFileCharset();

        // 检查的部分
        if (StringUtils.isEmpty(fileName)) {
            throw new RuntimeException("文件名不能为空.");
        }

        boolean isValidCharset = StringUtils.equalsIgnoreCase(fileCharset, "gbk") || StringUtils.equalsIgnoreCase(fileCharset, "utf-8");
        if (!isValidCharset) {
            throw new RuntimeException("文件编码非法.");
        }

        if (Objects.isNull(fileInfo.getParallelDegree()) || fileInfo.getParallelDegree() < 1) {
            logger.warn("☆☆☆ parallelDegree参数为空或者非法.");
            fileInfo.setParallelDegree(1);
        }

        // 解析的部分
        if (StringUtils.isEmpty(filePath)) {
            File f = new File(this.getClass().getClassLoader().getResource("").getPath());
            fileInfo.setFilePath(f.toString() + File.separator);
            logger.info("☆☆☆ 文件保存的路径: '{}'", fileInfo.getFilePath());
        }

        String fieldSeparator = fileInfo.getFieldSeparator();
        if (StringUtils.startsWithIgnoreCase(fieldSeparator, "0x")) {
            int fieldSeparatorLength = fieldSeparator.length();
            if (fieldSeparatorLength != 4) {
                throw new RuntimeException("字段分隔符是16进制表示时, 长度应该为4.");
            }

            String fieldSeparatorHex = fieldSeparator.substring(2);
            int iFieldSeparator = Integer.parseInt(fieldSeparatorHex, 16);

            fileInfo.setFieldSeparator(String.valueOf((char) iFieldSeparator));
        }
    }
}
