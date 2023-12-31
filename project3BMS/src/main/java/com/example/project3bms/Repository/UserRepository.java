package com.example.project3bms.Repository;

import com.example.project3bms.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByUsername(String username);
    User findUserById(Integer id);
}
