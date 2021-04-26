package io.springAssignment.users.controller;

import io.springAssignment.users.dto.UserPatch;
import io.springAssignment.users.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private io.springAssignment.users.service.userService userService;

    @GetMapping("/token")
    public ResponseEntity<String> token(@RequestHeader(name = "Authorization") String token){
        return new ResponseEntity("The Authorization is " + token.toString(),HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAllUsers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize
    ){
            List<UserEntity> allUsers = userService.getAllUser(pageNo,pageSize);
            return new ResponseEntity<>(allUsers, HttpStatus.OK);

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable long id){
        UserEntity userData =  userService.getUser(id);
        if(userData != null){
            return new  ResponseEntity<>(userData,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/users")
    public  ResponseEntity<UserEntity> addNewUser(@RequestBody UserEntity newUser){
      try {
          UserEntity userData = userService.addNewUser(newUser);
          return new ResponseEntity<>(userData,HttpStatus.CREATED);
      }catch (Exception e){
          return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable long id, @RequestBody UserEntity updateUserData){
        UserEntity userData = userService.updateUser(id,updateUserData);
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
