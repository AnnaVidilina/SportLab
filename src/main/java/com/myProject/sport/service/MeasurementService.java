package com.myProject.sport.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.sport.entity.Measurement;
import com.myProject.sport.entity.User;
import com.myProject.sport.repository.MeasurementRepository;

@Service
public class MeasurementService extends CrudImpl<Measurement> {
	public MeasurementRepository repository;

	@Autowired
	public MeasurementService(MeasurementRepository repository) {
		super(repository);
		this.repository = repository;
	}
	public List<Measurement> findByUser(User user){
		return repository.findAllByUser(user);
	}
	
}
