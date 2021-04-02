package com.myProject.sport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myProject.sport.entity.Exercise;
import com.myProject.sport.entity.Product;
import com.myProject.sport.entity.TrainingPerDay;
import com.myProject.sport.exception.RecordNotFoundException;
import com.myProject.sport.service.ExerciseService;
import com.myProject.sport.service.ProductService;
import com.myProject.sport.service.TrainingPerDayService;

@Controller
@RequestMapping("/trainingPerDay")
public class TrainingPerDayController {
	@Autowired
	TrainingPerDayService service;
	
	@Autowired
	ProductService serviceProduct;
	
	@Autowired
	ExerciseService serviceExercise;
	

	@RequestMapping
	public String getAll(Model model) {
		List<TrainingPerDay> list = service.getAll();

		model.addAttribute("list", list);
		return "trainingPerDay-list";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name="id",required = false) Long id) throws RecordNotFoundException {
		
		List<Product> listProduct = serviceProduct.getAll();
		model.addAttribute("listProduct", listProduct);

		List<Exercise> listExercise = serviceExercise.getAll();
		model.addAttribute("listExercise", listExercise);
		
		
		
		if (id!=null) {
			TrainingPerDay entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new TrainingPerDay());
		}
		return "trainingPerDay-add-edit";
	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return "redirect:/trainingPerDay";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(TrainingPerDay entity,
			@RequestParam(value = "exers", required = true) int[] exers,
			@RequestParam(value = "prods", required = true) int[] prods) throws Exception {
		Exercise ex;
		Product pd;
		if(exers!=null)
		for(int i=0; i<exers.length;i++) {
			System.out.println(exers[i]);
			ex=serviceExercise.read(exers[i]);
			if(!entity.getExcersices().contains(ex))
				entity.getExcersices().add(ex);
		}
		if(prods!=null)
		for(int i=0; i<prods.length;i++) {
			pd=serviceProduct.read(prods[i]);
			if(!entity.getProducts().contains(pd))
				entity.getProducts().add(pd);
		}
		service.create(entity);
		return "redirect:/trainingPerDay";
	}
}