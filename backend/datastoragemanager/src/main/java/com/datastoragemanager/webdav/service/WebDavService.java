package com.datastoragemanager.webdav.service;

import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
            sardine.createDirectory(this.url+"mamei/systems");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void createFile(String path) {
    }

    public boolean fileExist(String path) {
        return false;
    }

    public boolean folderExist(String path) {
        return false;
    }
}
