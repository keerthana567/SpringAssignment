package com.example.mongodb.demo.service;

import com.example.mongodb.demo.dto.UserPatch;
import com.example.mongodb.demo.model.Model;
import com.example.mongodb.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<Model> getAllUser(Integer pageNo, Integer pageSize){
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Model> pagedResult = userRepo.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Model>();
        }
    }


    public Model addNewUser(Model newUserData){
        return userRepo.save(newUserData);
    }

    public Model updateUser(long id, Model userData){
        Optional<Model> DBData =  userRepo.findById(id);
        if (DBData.isPresent()) {
            Model _dbData = DBData.get();
            _dbData.setName(userData.getName());
            _dbData.setEmail(userData.getEmail());
            _dbData.setLocation(userData.getLocation());
            return userRepo.save(_dbData);
        }
        return null;
    }

    public Model getUser(long id) {
        Optional<Model> DBData =  userRepo.findById(id);
        if (DBData.isPresent()) {
            return DBData.get();
        }
        return null;
    }

    public boolean deleteUser(long id) {
        Optional<Model> DBData =  userRepo.findById(id);
        if (DBData.isPresent()) {
            userRepo.delete(DBData.get());
            return true;
        }
        return false;
    }

    public boolean findUserById(long id){
        Optional<Model> DBData =  userRepo.findById(id);
        if (DBData.isPresent()) {
            return true;
        }
        return false;
    }

    public List<String> patchCall(List<UserPatch> patchData,long id){
        List<String> statusList = new ArrayList<>() ;
        for (int i = 0; i < patchData.size() ; i++) {
            statusList.add(patchAction(patchData.get(i),id));
        }
        return statusList;
    }

    public String patchAction(UserPatch newPatchData, long id) {
        String action = newPatchData.getAction();
        if (action .equals("replace") ){
            Optional<Model> DBData = userRepo.findById(id);
            if (DBData.isPresent()) {
                Model _dbData = DBData.get();
                if (newPatchData.getFieldName().equals("Name")) {
                    _dbData.setName(newPatchData.getValue());
                    userRepo.save(_dbData);
                    return " " + newPatchData.getAction() + " " + newPatchData.getFieldName() + " Done";
                }
                if (newPatchData.getFieldName().equals("email")) {
                    _dbData.setEmail(newPatchData.getValue());
                    userRepo.save(_dbData);
                    return " " + newPatchData.getAction() + " " + newPatchData.getFieldName() + " Done";
                }
                if (newPatchData.getFieldName().equals("location")) {
                    _dbData.setEmail(newPatchData.getValue());
                    userRepo.save(_dbData);
                    return " " + newPatchData.getAction() + " " + newPatchData.getFieldName() + " Done";
                }

            }
        } else if (action .equals("del") ){
            Optional<Model> DBData = userRepo.findById(id);
            if (DBData.isPresent()) {
                Model _dbData = DBData.get();
                if (newPatchData.getFieldName().equals("Name")) {
                    _dbData.setName(newPatchData.getValue());
                    userRepo.save(_dbData);
                    return " " + newPatchData.getAction() + " " + newPatchData.getFieldName() + " Done";
                }
                if (newPatchData.getFieldName().equals("email")) {
                    _dbData.setEmail(newPatchData.getValue());
                    userRepo.save(_dbData);
                    return " " + newPatchData.getAction() + " " + newPatchData.getFieldName() + " Done";
                }
                if (newPatchData.getFieldName().equals("location")) {
                    _dbData.setEmail(newPatchData.getValue());
                    userRepo.save(_dbData);
                    return " " + newPatchData.getAction() + " " + newPatchData.getFieldName() + " Done";
                }

            }
        } else if (action .equals( "add")) {
            Optional<Model> DBData = userRepo.findById(id);
            if (DBData.isPresent()) {
                Model _dbData = DBData.get();
                if (newPatchData.getFieldName().equals("Name")) {
                    _dbData.setName(newPatchData.getValue());
                    userRepo.save(_dbData);
                    return " " + newPatchData.getAction() + " " + newPatchData.getFieldName() + " Done";
                }
                if (newPatchData.getFieldName().equals("email")) {
                    _dbData.setEmail(newPatchData.getValue());
                    userRepo.save(_dbData);
                    return " " + newPatchData.getAction() + " " + newPatchData.getFieldName() + " Done";
                }
                if (newPatchData.getFieldName().equals("location")) {
                    _dbData.setEmail(newPatchData.getValue());
                    userRepo.save(_dbData);
                    return " " + newPatchData.getAction() + " " + newPatchData.getFieldName() + " Done";
                }

            }
        }
        return "Action is not Available";
    }
}


