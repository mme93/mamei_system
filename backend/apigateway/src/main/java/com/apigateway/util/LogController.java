package com.apigateway.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/log")
public class LogController {

    private final String USER_LOG_PATH = "backend/apigateway/src/main/resources/logs/user_"+
            LocalDate.now(ZoneId.of("Europe/Berlin")).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
            +".log";

    @GetMapping("/user")
    public ResponseEntity<byte[]> getUserLog() {

        try {
            Path path = Paths.get(USER_LOG_PATH);
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
