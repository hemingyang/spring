
package com.example.demoplus.web;


import com.example.demoplus.domain.User;
import com.example.demoplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserWeb {


    @Autowired
    private UserService userService;


    @GetMapping("/getUsre/{id}")
    public User  getUser( @PathVariable Long id){

        return  userService.getUser(id);
    }

    @GetMapping("/findAll")
    public List<User> findAll( ){
        return  userService.findAll();
    }
    @DeleteMapping("/deleteUser/{id}")
    public void  deleteUser( @PathVariable Long id){
       userService.deleteUser(id);
    }

    @PostMapping("/insertUser")@ResponseBody
    public Integer insertUser(User user){

       return userService.insertUser(user);
    }
    @PostMapping("/updateUser")
    public Integer updateUser(User user){
        return userService.updateUser(user);
    }

}
