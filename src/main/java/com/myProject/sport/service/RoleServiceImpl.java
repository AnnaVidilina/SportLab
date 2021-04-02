package com.myProject.sport.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.sport.entity.Role;
import com.myProject.sport.repository.RoleRepository;
import com.myProject.sport.repository.TypeExerciseRepository;

@Service
public class RoleServiceImpl extends CrudImpl<Role>{
    
    public RoleRepository repository;

	@Autowired
	public RoleServiceImpl(RoleRepository repository) {
		super(repository);
		this.repository = repository;
	}
 
    public Role findByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

}
