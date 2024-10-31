package com.example.entity;

public class TaskEntity {
    private int kid;
    private int tid;
    private String clzno;
    private String cno;


    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getClzno() {
        return clzno;
    }

    public void setClzno(String clzno) {
        this.clzno = clzno;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "kid=" + kid +
                ", tid=" + tid +
                ", clzno='" + clzno + '\'' +
                ", cno='" + cno + '\'' +
                '}';
    }
}
