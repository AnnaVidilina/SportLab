package com.myProject.sport.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Table
@Data
public class Measurement extends AbstractEntity {
		
	// обхват груди
	@Column
	@NotNull
	float chest;
	
	// обхват талии
	@Column
	@NotNull
	float girthWaist;
	
	// обхват живота
	@Column
	@NotNull
	float girthBelly;
	
	// обхват бедер
	@Column
	@NotNull
	float girthHip;
	
	// обхват ноги
	@Column
	@NotNull
	float girthLeg;
	
	// дата измерения
	@Column
	@NotNull
	Date dateGirt;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;

	@Override
	public String toString() {
		return "Measurement [chest=" + chest + ", girthWaist=" + girthWaist + ", girthBelly=" + girthBelly
				+ ", girthHip=" + girthHip + ", girthLeg=" + girthLeg + ", dateGirt=" + dateGirt + ", user=" + user.getId()
				+ "]";
	}
	
}
