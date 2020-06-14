package com.techprimers.mybatis.springbootmybatis.mapper;

import com.techprimers.mybatis.springbootmybatis.model.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UsersMapper {

    @Select("select * from users")
    List<Users> findAll();

    @Insert("insert into users(name,salary) values(#{name},#{salary})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id",
            before = false, resultType = Integer.class)
    void insert(Users users);

    @Delete("delete from user where id = #{id}")
    int delete(Integer id);


    @Update("update user set name=#{name},salary=#{salary}" +
            "where id=#{userId}")
    int updateUser(Users user);


    @Select("select *from users where id=#{id}")
    Users  findByone(Integer id);

}
