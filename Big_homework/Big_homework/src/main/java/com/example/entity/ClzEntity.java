package com.example.entity;
public class ClzEntity {
    private String clzno;
    private String clzname;

    public String getClzno() {
        return clzno;
    }

    public void setClzno(String clzno) {
        this.clzno = clzno;
    }

    public String getClzname() {
        return clzname;
    }

    public void setClzname(String clzname) {
        this.clzname = clzname;
    }

    @Override
    public String toString() {
        return "ClzEntity{" +
                "clzno='" + clzno + '\'' +
                ", clzname='" + clzname + '\'' +
                '}';
    }
}
