package com.atm.respository;

import com.atm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "select exists(select 1 from users where id = ?1)")
    public boolean userExist(Long userId);
}
