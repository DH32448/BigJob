package com.example.entity;

public class Largefile {
    private String id;
    private String fileName;
    private Object content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Largefile{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", content=" + content +
                '}';
    }
}
