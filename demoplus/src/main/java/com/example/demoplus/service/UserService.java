package com.example.demoplus.service;

import com.example.demoplus.domain.User;
import com.example.demoplus.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {



    private UserMapper userMapper;
    private Logger LOG = LoggerFactory.getLogger(UserMapper.class);

    @Autowired
    public  void setUserMapper(UserMapper userMapper){
        this.userMapper=userMapper;
    }



    public User  getUser(@Param("id")Long id){
        return userMapper.selectById(id);
    }


    public void deleteUser(@Param("id")Long id){
         userMapper.deleteById(id);
    }


    public List<User> findAll(){
        return userMapper.selectList(null);
    }

    public Integer updateUser(User user){
            return  userMapper.updateById(user);
    }

    public Integer insertUser(User user){
       return userMapper.insert(user);
    }

}
