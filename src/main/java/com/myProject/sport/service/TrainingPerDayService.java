package com.myProject.sport.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.sport.entity.TrainingPerDay;
import com.myProject.sport.repository.TrainingPerDayRepository;

@Service
public class TrainingPerDayService extends CrudImpl<TrainingPerDay> {
	public TrainingPerDayRepository repository;

	@Autowired
	public TrainingPerDayService(TrainingPerDayRepository repository) {
		super(repository);
		this.repository = repository;
	}
	
	List<TrainingPerDay> findAllByDateTraining(Date date){
		return repository.findAllByDateTraining(date);
	}
}
