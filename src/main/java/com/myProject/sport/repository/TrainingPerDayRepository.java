package com.myProject.sport.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myProject.sport.entity.TrainingPerDay;

@Repository
public interface TrainingPerDayRepository  extends  JpaRepository<TrainingPerDay, Long>{
	List<TrainingPerDay> findAllByDateTraining(Date date);
}
