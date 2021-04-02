package com.myProject.sport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myProject.sport.entity.Exercise;
import com.myProject.sport.entity.TypeExercise;
import com.myProject.sport.exception.RecordNotFoundException;
import com.myProject.sport.service.ExerciseService;
import com.myProject.sport.service.TypeExerciseService;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {
	@Autowired
	ExerciseService service;
	
	@Autowired
	TypeExerciseService typeExerciseService;

	@RequestMapping
	public String getAll(Model model) {
		List<Exercise> list = service.getAll();
		model.addAttribute("list", list);
		
		return "exercise-list";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name="id",required = false) Long id) throws RecordNotFoundException {
		List<TypeExercise> typeExerciselist = typeExerciseService.getAll();
		model.addAttribute("typeExercise", typeExerciselist);
		if (id!=null) {
			Exercise entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new Exercise());
		}
		return "exercise-add-edit";
	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return "redirect:/exercise";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(Exercise entity, @Param("typeId") Long typeId) throws Exception {
		if(typeId==null)return "redirect:/exercise";
		TypeExercise read = typeExerciseService.read(typeId);
		entity.setType(read);
		service.create(entity);
		return "redirect:/exercise";
	}
}