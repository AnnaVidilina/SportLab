package com.myProject.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.sport.entity.TypeExercise;
import com.myProject.sport.repository.TypeExerciseRepository;

@Service
public class TypeExerciseService extends CrudImpl<TypeExercise> {
	public TypeExerciseRepository repository;

	@Autowired
	public TypeExerciseService(TypeExerciseRepository repository) {
		super(repository);
		this.repository = repository;
	}
}
