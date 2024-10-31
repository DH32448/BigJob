package com.test.entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;//当字段名和列名不一致时，可以使用resultMap
    private int age;
    private Group group;
    //private UserDetall detall;//一对一查询
    //private List<Book> book;//一对多查询
    //private Date registerTime;


    //public User() {
    //    System.out.println("User 构造器被调用");
    //}
    //
    //public User(int id, String name, int age) {
    //    this.id = id;
    //    this.name = name;
    //    this.age = age = 10;
    //}
}
