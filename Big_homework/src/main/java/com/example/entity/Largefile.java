package com.example.entity;

public class Largefile {
    private String id;
    private String filename;
    private Object content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "Largefile{" +
                "id='" + id + '\'' +
                ", filename='" + filename + '\'' +
                ", content=" + content +
                '}';
    }
}
