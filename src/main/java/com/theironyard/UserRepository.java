package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by vajrayogini on 3/9/16.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findFirstByName(String userName);

}
