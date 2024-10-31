package com.example.entity;

public class UserEntity {
    private int uid;
    private String uname;
    private String phone;
    private String pwd;
    private String clzno;
    private int role;
    private String pic;
    private Largefile largefile;
    private TaskEntity taskEntity;
    private MarkEntity markEntity;
    private CourseEntity courseEntity;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getClzno() {
        return clzno;
    }

    public void setClzno(String clzno) {
        this.clzno = clzno;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Largefile getLargefile() {
        return largefile;
    }

    public void setLargefile(Largefile largefile) {
        this.largefile = largefile;
    }

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }

    public MarkEntity getMarkEntity() {
        return markEntity;
    }

    public void setMarkEntity(MarkEntity markEntity) {
        this.markEntity = markEntity;
    }

    public CourseEntity getCourseEntity() {
        return courseEntity;
    }

    public void setCourseEntity(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", phone='" + phone + '\'' +
                ", pwd='" + pwd + '\'' +
                ", clzno='" + clzno + '\'' +
                ", role=" + role +
                ", pic='" + pic + '\'' +
                ", largefile=" + largefile +
                ", taskEntity=" + taskEntity +
                ", markEntity=" + markEntity +
                ", courseEntity=" + courseEntity +
                '}';
    }
}
