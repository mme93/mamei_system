package com.datastoragemanager.webdav.service;

import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@Service
public class WebDavService {

    @Value("${webDav.user}")
    private String user;

    @Value("${webDav.password}")
    private String password;

    @Value("${webDav.url}")
    private String url;

    public boolean createFolder(String path) {
        try {
            Sardine sardine = SardineFactory.begin(this.user, this.password);
            List<DavResource> resources = sardine.list(this.url);
            for (DavResource res : resources) {
                System.err.println(res.getName());
            }
            sardine.createDirectory(this.url+"mamei/");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void loadFileToWebDav(){
        try {
            Sardine sardine = SardineFactory.begin();
            sardine.setCredentials(this.user, this.password);
            File localFile = x();
            System.err.println(localFile.exists());
           if(localFile.exists()){
               FileInputStream fileInputStream = new FileInputStream(localFile);
               sardine.put("http://212.227.165.166/webdav/anleitungs.txt" , fileInputStream.readAllBytes());
               fileInputStream.close();
           }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public File x() throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("anleitung.txt");
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        } else {
            return new File(resource.toURI());
        }
    }

    public void createFile(String path) {
        loadFileToWebDav();
    }

    public boolean fileExist(String path) {
        return false;
    }

    public boolean folderExist(String path) {
        return false;
    }
}
