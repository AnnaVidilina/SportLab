package com.myProject.sport.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class TypeExercise extends AbstractEntity {
	@Column(length = 50)
	String name;
	
	@OneToMany(mappedBy = "type")
	List<Exercise> exercises = new ArrayList<>();

	@Override
	public String toString() {
		return name;
	}
	
}
