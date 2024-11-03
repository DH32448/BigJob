package com.example.dao;

import com.example.entity.Largefile;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LargeFileDao {
    @Insert("insert into t_largefile "
            + "(id,filename,content) "
            + "values(#{id}, #{filename},"
            + " #{content})")
    public Integer add(Largefile largefile);
    @Select("select id,filename,content from t_largefile where id=#{id}")
    public Largefile findOne(String id);


    @Delete("delete from t_largefile where id=#{id}")
    void delete(String id);
}
