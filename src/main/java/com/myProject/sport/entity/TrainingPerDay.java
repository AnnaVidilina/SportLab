package com.myProject.sport.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Table
@Data
public class TrainingPerDay extends AbstractEntity {
	
	@Column
	@Temporal(TemporalType.DATE)
	Date dateTraining;
	
	
	@JsonIgnore
	@ManyToMany
	List <Exercise> excersices= new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany
	List <Product> products= new ArrayList<>();

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	User user;
}
