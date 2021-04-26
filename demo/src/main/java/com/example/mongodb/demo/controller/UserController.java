package com.example.mongodb.demo.controller;

import com.example.mongodb.demo.dto.UserPatch;
import com.example.mongodb.demo.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
  private UserService userService;

    @GetMapping("/token")
    public ResponseEntity<String> token(@RequestHeader(name = "Authorization") String token){
        return new ResponseEntity("The Authorization is " + token.toString(), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Model>> getAllUsers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize
    ){
        List<Model> allUsers = userService.getAllUser(pageNo,pageSize);
        return new ResponseEntity<>(allUsers, HttpStatus.OK);

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Model> getUser(@PathVariable long id){
        Model userData =  userService.getUser(id);
        if(userData != null){
            return new  ResponseEntity<>(userData,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/users")
    public  ResponseEntity<Model> addNewUser(@RequestBody Model newUser){
        try {
            Model userData = userService.addNewUser(newUser);
            return new ResponseEntity<>(userData,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Model> updateUser(@PathVariable long id, @RequestBody Model updateUserData){
        Model userData = userService.updateUser(id,updateUserData);
        if (userData != null) {
            return new ResponseEntity<>(userData,HttpStatus.OK);
        }
        return  new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        boolean deleteStatus = userService.deleteUser(id);
        if (deleteStatus){
            return new ResponseEntity<>("User Deleted Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Unable to Delete the user",HttpStatus.OK);
    }

    @PatchMapping("/users/{id}")
    public List<String> patchFunction(@RequestBody List<UserPatch> patchData, @PathVariable long id){
        try {
            if(userService.findUserById(id)){
                return userService.patchCall( patchData, id);
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }

}

