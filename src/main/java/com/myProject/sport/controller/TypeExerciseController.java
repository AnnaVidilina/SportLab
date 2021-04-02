package com.myProject.sport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myProject.sport.entity.TypeExercise;
import com.myProject.sport.exception.RecordNotFoundException;
import com.myProject.sport.service.TypeExerciseService;

@Controller
@RequestMapping("/typeExercise")
public class TypeExerciseController {
	@Autowired
	TypeExerciseService service;

//	@RequestMapping
//	public String getAll(Model model) {
//		List<TypeExercise> list = service.getAll();
//
//		model.addAttribute("list", list);
//		return "typeExercise-list";
//	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name="id",required = false) Long id) throws RecordNotFoundException {
		if (id!=null) {
			TypeExercise entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new TypeExercise());
		}
		return "typeExercise-add-edit";
	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return "redirect:/exercise";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(TypeExercise entity) throws Exception {
		service.create(entity);
		return "redirect:/exercise";
	}
}