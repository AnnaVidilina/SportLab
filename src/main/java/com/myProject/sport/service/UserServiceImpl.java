package com.myProject.sport.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myProject.sport.entity.Measurement;
import com.myProject.sport.entity.Role;
import com.myProject.sport.entity.User;
import com.myProject.sport.repository.UserRepository;

@Service
public class UserServiceImpl extends CrudImpl<User> {
	
	private final static String DEFAULT_ROLE = "ROLE_USER";

	@Autowired
	private RoleServiceImpl roleService;
	
	@Autowired
	private MeasurementService measurementService;

	public UserRepository repository;


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository repository) {
		super(repository);
		this.repository=repository;
    }

    public User findByUsername(String username) {
        return repository.findByUsernameIgnoreCase(username);
    }
//registration
    public boolean userRegistration(User entity, Measurement measurement) {

        if (repository.findByUsernameIgnoreCase(entity.getUsername()) != null)
            return false;
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        try {
            if(roleService.findByName(DEFAULT_ROLE)==null)
                roleService.create(new Role(DEFAULT_ROLE));
            // просто создание
            create(entity);
            
            //добавление роли и сохранение
            Role role = roleService.findByName(DEFAULT_ROLE);
            entity=repository.findByUsernameIgnoreCase(entity.getUsername());
            entity.getRoles().add(role);
            update(entity);
            
            //сохранение в списке ролей пользователя
            role.getUsers().add(entity);
            roleService.update(role);
            
            
            //сохранения замера
            entity=repository.findByUsernameIgnoreCase(entity.getUsername());
            Date dateGirt = new Date(System.currentTimeMillis());
            measurement.setDateGirt(dateGirt);
            measurement.setUser(entity);
            measurementService.create(measurement);
            
            //добавление замера пользователю и сохранение
            entity.getMeasurements().add(measurement);
            update(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
