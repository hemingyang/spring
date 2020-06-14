package com.techprimers.mybatis.springbootmybatis.resource;

import com.techprimers.mybatis.springbootmybatis.mapper.UsersMapper;
import com.techprimers.mybatis.springbootmybatis.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UsersResource {

    @Autowired
    private UsersMapper usersMapper;

    public UsersResource(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }


    @GetMapping("/all")
    public List<Users> getAll() {
        return usersMapper.findAll();
    }

    @GetMapping("/update")
    private List<Users> update() {

        Users users = new Users();
        users.setName("Youtube");
        users.setSalary(2333L);

        usersMapper.insert(users);

        return usersMapper.findAll();
    }

    @RequestMapping("/one/{id}")
    public Users findByOne( @PathVariable Integer id){
        return usersMapper.findByone(id);
    }
    @DeleteMapping("/delete/{id}")
    public Integer deleteId(@PathVariable Integer id){
        return  usersMapper.delete(id);
    }

    @GetMapping("/updateuser")
    @ResponseBody
    public Integer findByUser(Users users){
        return  usersMapper.updateUser(users);
    }
}
