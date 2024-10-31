package com.example.dao;

import com.example.entity.Largefile;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LargeFileDao {
    @Insert(value = "insert into t_largefile "
            + "(id,filename,content) "
            + "values(#{id}, #{filename},"
            + " #{content, javaType=byte[], jdbcType=BLOB, " +
            "typeHandler=org.apache.ibatis.type.BlobTypeHandler})")
    public Integer add(Largefile largefile);
    @Select(value = "select id,filename,content from t_largefile where id=#{id}")
    public Largefile findOne(String id);


    @Delete(value = "delete from t_largefile where id=#{id}")
    void delete(String id);
}
