package com.example.mongodb.demo.repository;

import com.example.mongodb.demo.model.Model;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


    public interface UserRepo extends
            MongoRepository<Model, String>,
            PagingAndSortingRepository<Model, String> {

        Optional<Model> findById(long id);




    }


