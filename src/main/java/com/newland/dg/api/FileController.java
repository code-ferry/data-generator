package com.newland.dg.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.newland.dg.model.FileInfo;
import com.newland.dg.model.RespInfo;
import com.newland.dg.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author huangxw
 * @date 2019-11-18
 */
@RestController
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Resource
    private FileService fileService;

    @PostMapping(value = "/file")
    public RespInfo createFile(@RequestBody FileInfo fileInfo) {
        logger.info("☆☆☆ fileInfo = \r\n{}", JSON.toJSONString(fileInfo, SerializerFeature.PrettyFormat));
        fileService.createFile(fileInfo);

        return new RespInfo();
    }

    @DeleteMapping(value = "/file")
    public String stopFile(@RequestParam String id) {
        return "building!";
    }

    @GetMapping(value = "/json")
    public String json() {
        return "building!";
    }
}
