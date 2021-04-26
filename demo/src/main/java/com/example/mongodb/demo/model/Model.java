package com.example.mongodb.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Model {
    @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private String id;
        private String Name;
        private String email;
        private String location;
}

