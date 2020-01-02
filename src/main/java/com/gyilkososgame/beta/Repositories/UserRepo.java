package com.gyilkososgame.beta.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gyilkososgame.beta.entities.Users;

public interface UserRepo extends CrudRepository<Users,Long>{
	
	Users findByName(String name);
}
