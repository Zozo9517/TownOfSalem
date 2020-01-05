package com.gyilkososgame.beta.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.gyilkososgame.beta.entities.*;
public interface RoleRepo extends CrudRepository<Role,Long>{
	
	Role findByName(String name);
}
