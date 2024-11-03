package com.example;

import com.example.dao.*;
import com.example.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaml.snakeyaml.error.Mark;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BigHomeworkApplicationTests {
@Autowired
    UserDao userDao;
@Autowired
    TaskDao taskDao;
@Autowired
    MarkDao markDao;
@Autowired
    ClzDao clzDao;
@Autowired
    CourseDao courseDao;
    @Test
    void contextLoads() {
        UserEntity user = new UserEntity();
        user.setUid(2);
        user.setPwd("696666");
        System.out.println(userDao.updatePwd(user));
    }
    @Test
    void add(){
        UserEntity user = new UserEntity();
        user.setUname("大王");
        user.setPwd("123457");
        user.setPhone("110110");
        user.setClzno("B111");  //- > 后期处理clz不输
        System.out.println(userDao.add(user));
    }
    @Test//知识点一
    void update(){
        UserEntity user = new UserEntity();
        user.setUid(3);
        user.setUname("泼猴");
        //user.setPwd("696666");
        //user.setPhone("852654");
        user.setClzno("B666");
        //user.setRole(1);
        System.out.println(userDao.update(user));
    }
    @Test
    void selectByUid(){
        System.out.println(userDao.findById(2));
    }

    @Test
    void selectByRole(){
        List<UserEntity> list = userDao.findByRole(1);
        list.forEach(System.out::println);
    }
    @Test
    void delete(){
        System.out.println(userDao.del(2023060615));
    }
    @Test
    void selectUserByPhone(){
        UserEntity byUserByPhone = userDao.findByUserByPhone("131019");
        System.out.println(byUserByPhone);

    }


    //Task
    @Test
    void findAllTask(){
        List<TaskEntity> list =  taskDao.findAll();
        list.forEach(System.out::println);
    }
    @Test
    void findByClznoTask() {
        List<TaskEntity> list = taskDao.findByClzno("B01");
        list.forEach(System.out::println);
    }
    @Test
    void findByTidTask() {
        List<TaskEntity> list = taskDao.findByTid(2023060608);
        list.forEach(System.out::println);
    }
    @Test
    void deleteByKidTask(){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setKid(8);
        System.out.println(taskDao.remove(taskEntity));
    }
    @Test
    void findBySnoCnoTask(){
        Map<String,Object> map = new HashMap<>();
        map.put("sno","131001");
        map.put("cno","001");
        MarkEntity markEntity = markDao.findBySnoCno(map);
        System.out.println(markEntity);
    }

    @Test
    void updateMarkTask(){
        MarkEntity markEntity = new MarkEntity();
        markEntity.setId(1);
        markEntity.setSno("131001");
        markEntity.setCno("001");
        markEntity.setScore(BigDecimal.valueOf(100.00));
        System.out.println(markDao.update(markEntity));
    }
    @Test
    void addMarkTask() {
        MarkEntity markEntity = new MarkEntity();
        markEntity.setSno("131001");
        markEntity.setCno("003");
        markEntity.setTpost(new Date());
        markEntity.setScore(BigDecimal.valueOf(100.00));
        System.out.println(markDao.add(markEntity));
    }
    @Test
    void findAllClz(){
        List<ClzEntity> list = clzDao.findAll();
        list.forEach(System.out::println);
    }
    @Test
    void addClz(){
        ClzEntity clzEntity = new ClzEntity();
        clzEntity.setClzno("666");
        clzEntity.setClzname("6大版");
        System.out.println(clzDao.add(clzEntity));
    }
    @Test
    void removeClz() {
        ClzEntity clzEntity = new ClzEntity();
        clzEntity.setClzno("K1");
        clzEntity.setClzname("K班级1");
        System.out.println(clzDao.remove(clzEntity));
    }
    @Test
    void updateClz() {
        ClzEntity clzEntity = new ClzEntity();
        clzEntity.setClzno("K1");
        clzEntity.setClzname("666大版");
        System.out.println(clzDao.update(clzEntity));
    }



    @Test
    void findAllCourse(){
        List<CourseEntity> list = courseDao.findAll();
        list.forEach(System.out::println);
    }
    @Test
    void addCourse(){
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCno("666");
        courseEntity.setCname("6大版");
        System.out.println(courseDao.add(courseEntity));
    }
    @Test
    void removeCourse() {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCno("666");
        courseEntity.setCname("6大版");
        System.out.println(courseDao.remove(courseEntity));
    }
    @Test
    void updateCourse() {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCno("001");
        courseEntity.setCname("666大版");
        System.out.println(courseDao.update(courseEntity));
    }
    @Test
    void findByCnoCourse(){
        MarkEntity markEntity = new MarkEntity();
        markEntity.setSno("13120");
        markEntity.setCno("001");
        markEntity.setTpost(new Date());
        markEntity.setTid(2023060608);
        markEntity.setScore(BigDecimal.valueOf(90.00));


        markDao.add(markEntity);
    }
    @Test
    void findAllUser() {
        List<MarkEntity> list = markDao.findByClznoCno("B02","002");
        list.forEach(System.out::println);
    }
}
