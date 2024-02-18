package com.massdatapool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
public class MassDataPoolController {

    private final String INFO_LOG_PATH = "backend/massdatapool/src/main/resources/logs/info.log";
    private final String EXCEPTION_LOG_PATH = "backend/massdatapool/src/main/resources/logs/exception.log";
    private final ResourceLoader resourceLoader;

    @Autowired
    public MassDataPoolController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/test")
    public void test() {
        int a = Integer.valueOf("Test");
    }

    @GetMapping("/exception-log")
    public ResponseEntity<byte[]> getExceptionLog() {

        try {
            Path path = Paths.get(EXCEPTION_LOG_PATH);
            byte[] content = Files.readAllBytes(path);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + path.getFileName().toString())
                    .body(content);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/info-log")
    public ResponseEntity<byte[]> getInfoLog() {

        try {
            Path path = Paths.get(INFO_LOG_PATH);
            byte[] content = Files.readAllBytes(path);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + path.getFileName().toString())
                    .body(content);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
