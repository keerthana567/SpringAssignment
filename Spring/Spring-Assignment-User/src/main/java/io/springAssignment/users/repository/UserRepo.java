package io.springAssignment.users.repository;

import io.springAssignment.users.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends
        CrudRepository<UserEntity, String>,
        PagingAndSortingRepository<UserEntity, String> {

    Optional<UserEntity> findById(long id);




}
