package com.myProject.sport.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myProject.sport.entity.Measurement;
import com.myProject.sport.entity.User;

@Repository
public interface MeasurementRepository  extends  JpaRepository<Measurement, Long>{
	public List<Measurement> findAllByUser(User user);
	
}
