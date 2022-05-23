package com.na.bilabonnement.models;

import org.springframework.core.io.Resource;

public class FileReply {
    private Resource resource;
    private String fileName;

    public FileReply(Resource resource, String fileName) {
        this.resource = resource;
        this.fileName = fileName;
    }

    public Resource getResource() {
        return resource;
    }

    public String getFileName() {
        return fileName;
    }
}
