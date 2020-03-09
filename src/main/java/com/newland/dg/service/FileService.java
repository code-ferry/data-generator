package com.newland.dg.service;

import com.newland.dg.model.FileInfo;
import com.newland.dg.work.WriteMainWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

/**
 * @author huangxw
 * @date 2019-11-18
 */
@Service
public class FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    /**
     * 统一一个线程池来处理
     */
    private static ExecutorService executorService = ForkJoinPool.commonPool();

    public void createFile(FileInfo fileInfo) {
        WriteMainWorker writeMainWorker = new WriteMainWorker(executorService, fileInfo);
        Thread t = new Thread(writeMainWorker, "MainWorker");
        t.start();
    }
}
