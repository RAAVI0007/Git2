package com.myexpertmedics.repository;
 
import org.springframework.data.repository.CrudRepository;

import com.myexpertmedics.beans.UserPOJO;
 

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<UserPOJO, Integer> {

}