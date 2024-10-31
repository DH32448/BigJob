package com.example.entity;

import com.example.dao.CourseDao;

import java.math.BigDecimal;
import java.util.Date;

public class MarkEntity {
    private int id;
    private String sno;
    private String cno;
    private BigDecimal score;
    private Date tpost;
    private int tid;
    private UserEntity userEntity;
    private CourseEntity courseEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Date getTpost() {
        return tpost;
    }

    public void setTpost(Date tpost) {
        this.tpost = tpost;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public CourseEntity getCourseEntity() {
        return courseEntity;
    }

    public void setCourseEntity(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
    }

    @Override
    public String toString() {
        return "MarkEntity{" +
                "id=" + id +
                ", sno='" + sno + '\'' +
                ", cno='" + cno + '\'' +
                ", score=" + score +
                ", tpost=" + tpost +
                ", tid=" + tid +
                ", userEntity=" + userEntity +
                ", courseEntity=" + courseEntity +
                '}';
    }
}
