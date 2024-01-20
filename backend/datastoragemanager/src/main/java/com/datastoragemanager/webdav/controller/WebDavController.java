package com.datastoragemanager.webdav.controller;

import com.datastoragemanager.webdav.service.WebDavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webDav")
public class WebDavController {

    private final WebDavService webDavService;

    @Autowired
    public WebDavController(WebDavService webDavService) {
        this.webDavService = webDavService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String>getPing(){
        return new ResponseEntity<>("Ping", HttpStatus.OK);
    }

    @PostMapping("/create/folder")
    public ResponseEntity createFolder(@RequestBody String path){
        if(webDavService.folderExist(path)){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        webDavService.createFolder(path);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/create/file")
    public ResponseEntity createFile(@RequestBody String path){
        if(webDavService.fileExist(path)){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        webDavService.createFile(path);
        return new ResponseEntity(HttpStatus.OK);
    }

}
