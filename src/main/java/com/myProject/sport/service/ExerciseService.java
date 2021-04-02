package com.myProject.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.sport.entity.Exercise;
import com.myProject.sport.repository.ExerciseRepository;

@Service
public class ExerciseService extends CrudImpl<Exercise> {
	public ExerciseRepository repository;

	@Autowired
	public ExerciseService(ExerciseRepository repository) {
		super(repository);
		this.repository = repository;
	}
}
