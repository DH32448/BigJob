package com.test;

import com.test.mapper.TestMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.session.SqlSession;

import java.io.FileNotFoundException;

/**
 * @author X
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(new FileInputStream("mybatis-config.xml"));
        //try(SqlSession session = factory.openSession(true)){
        //    List<User> users = session.selectList("selectAllUser");
        //    users.forEach(System.out::println);
        //}

        //try(SqlSession session = MybatisUtils.openSession(true)){
        //    User user = session.selectOne("selectAllUserById", 2);
        //    System.out.println(user);
        //}

        //try(SqlSession session = MybatisUtils.openSession(true)){
        //    Map<String,Object> user = session.selectOne("selectAllUserById2", 1);
        //    System.out.println(user);
        //}
        //try(SqlSession session = MybatisUtils.openSession(true)){
        //    User user = session.selectOne("selectUserByIdAndAge", Map.of("id", 1, "age", 0));
        //    System.out.println(user);
        //}
        //try(SqlSession session = MybatisUtils.openSession(true)){
        //    User user = session.selectOne("selectUserByIdAndAge", new Param(1, 7));
        //    System.out.println(user);
        //}

        //try(SqlSession session = MybatisUtils.openSession(true)){
        //    Map<String, Objects> user = session.selectMap("selectAllUser","id");
        //    System.out.println(user);
        //}
        //try(SqlSession session = MybatisUtils.openSession(true)){
        //    Cursor<User> user = session.selectCursor("selectAllUser");
        //    user.forEach(System.out::println);
        //    System.out.println(user.getCurrentIndex());
        //    System.out.println(user.isConsumed());
        //    System.out.println(user.isOpen());
        //}
        //try(SqlSession session = MybatisUtils.openSession(true)){
        //    session.select("selectAllUser", context -> System.out.println(context.getResultObject()));
        //}
        //try(SqlSession session = MybatisUtils.openSession(true)){
        //    session.select("selectAllUserById3", context -> System.out.println(context.getResultObject()));
        //}

        //try(SqlSession session = MybatisUtils.openSession(true)){
        //    TestMapper mapper = session.getMapper(TestMapper.class);
        //    System.out.println(mapper.selectAllUser());
        //}

        //try(SqlSession session = MybatisUtils.openSession(true)){
        //        List<User> user= session.selectList("selectAllUserById4", 1);
        //        System.out.println(user);
        //    }

        //try(SqlSession session = MybatisUtils.openSession(true)){
        //    List<User> user= session.selectList("selectAllUserById5", 2);
        //    System.out.println(user);
        //}
        //try(SqlSession session = MybatisUtils.openSession(true)){
        //    List<User> user= session.selectList("selectAllUserById6", 1);
        //    System.out.println(user);
        //}
        try(SqlSession session = MybatisUtils.openSession(true)){
           TestMapper user= session.getMapper(TestMapper.class);
           user.selectAllUser().forEach(System.out::println);
        }
    }
    @Data
    @AllArgsConstructor
    static class Param{
        int id;
        int age;
    }
}
