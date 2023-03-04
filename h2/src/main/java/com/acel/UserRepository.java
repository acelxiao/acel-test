package com.acel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.userName=:userName or u.userId=:userId")
    public List<User> getUsers(@Param("userName") String userName, @Param("userId") int userId);

}
