package com.endava.webfundamentals.signin.repository;

import com.endava.webfundamentals.signin.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by fstancu on 8/1/2016.
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
