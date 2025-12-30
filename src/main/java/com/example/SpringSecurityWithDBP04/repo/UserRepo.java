package com.example.SpringSecurityWithDBP04.repo;

import com.example.SpringSecurityWithDBP04.entity.UserEntity;
import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity>findByUsername(String Username);

}
