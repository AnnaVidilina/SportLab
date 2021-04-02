package com.myProject.sport.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table
public class Product extends AbstractEntity {
	@Column(length = 50)
	@NonNull
	String name;

	@Column
	float kkal;

	// белки
	@Column
	float protein;

	// жиры
	@Column
	float fat;

	// углеводы
	@Column
	float carbohydrates;

	// граммы
	@Column
	float gramms;

	@JsonIgnore
	@ManyToMany
	List<TrainingPerDay> trainings = new ArrayList<>();

	public Product() {
	}

	@Override
	public String toString() {
		return name + ", kkal=" + kkal;
	}

}
