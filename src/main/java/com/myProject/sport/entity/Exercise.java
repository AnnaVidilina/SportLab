package com.myProject.sport.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NonNull;

@Entity
@Table
@Data
public class Exercise extends AbstractEntity {

	@Column(length = 100)
	@NonNull
	String name;		
	
	@Column(length = 1000)
	@NonNull
	String instruction;
	
	@Column
	int countOfRepeat;

	// количество подходов
	@Column
	int numberOfApproaches;	
	
	// рабочие веса	
	@Column
	int workingWeights;
	
	@Column
	int secondToRelax;

	@ManyToOne
	@JoinColumn(name = "typeExercise_id", nullable = false)
	TypeExercise type;
	
	@JsonIgnore
	@ManyToMany	
	List<TrainingPerDay> trainings = new ArrayList<>();
	
	public Exercise() {}

	@Override
	public String toString() {
		return "Exercise [name=" + name + ", countOfRepeat=" + countOfRepeat + ", type=" + type + "]";
	}


	
	
}
