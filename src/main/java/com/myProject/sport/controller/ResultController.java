package com.myProject.sport.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myProject.sport.entity.Exercise;
import com.myProject.sport.entity.Product;
import com.myProject.sport.entity.TrainingPerDay;
import com.myProject.sport.entity.User;
import com.myProject.sport.exception.RecordNotFoundException;
import com.myProject.sport.service.ExerciseService;
import com.myProject.sport.service.ProductService;
import com.myProject.sport.service.TrainingPerDayService;
import com.myProject.sport.service.UserServiceImpl;

@Controller

@RequestMapping("/result")
public class ResultController {

	@Autowired
	UserServiceImpl userService;

	@Autowired
	TrainingPerDayService trainingPerDayService;
	@Autowired
	TrainingPerDayService service;

	@Autowired
	ProductService serviceProduct;

	@Autowired
	ExerciseService serviceExercise;

	@GetMapping
	@RequestMapping(path = {"","/","/get/{id}" })
	public String getAllById(Model model, @PathVariable(name = "id", required = false) Long id, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		if (id != null) {
			TrainingPerDay read = trainingPerDayService.read(id);
			model.addAttribute("id", read.getId());
			model.addAttribute("training", read);
		} else {
			java.util.Date date = new java.util.Date(System.currentTimeMillis());
			List<TrainingPerDay> list = trainingPerDayService.repository.findAllByDateTraining(date);
			if(list==null || list.size()==0)
				model.addAttribute("id", -1);
			else {
				boolean ok=false;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getUser().getId() == user.getId()) {
						model.addAttribute("id", list.get(i).getId());
						model.addAttribute("training", list.get(i));
						ok=true;
					}
				}
				if(!ok)
					model.addAttribute("id", -1);
			}
		}
		return "result";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name = "id", required = true) Long id, Principal principal)
			throws RecordNotFoundException {

		List<Product> listProduct = serviceProduct.getAll();
		model.addAttribute("listProduct", listProduct);

		List<Exercise> listExercise = serviceExercise.getAll();
		model.addAttribute("listExercise", listExercise);

		TrainingPerDay entity = service.read(id);
		
		//exercise
		ArrayList<Long> myListExercise= new ArrayList<Long>();
		for(int i=0; i<entity.getExcersices().size();i++) {
			myListExercise.add(entity.getExcersices().get(i).getId());
		}		
		model.addAttribute("myListExercise", myListExercise);
		
		//product
		ArrayList<Long> myListProduct = new ArrayList<Long>();
		for(int i=0; i<entity.getProducts().size();i++) {
			myListProduct.add(entity.getProducts().get(i).getId());
		}
		
		model.addAttribute("myListProduct", myListProduct);
		
		model.addAttribute("id",id);

		return "result-edit";
	}
	
	
	@RequestMapping("/save/{id}")
	public String save(Model model, @Param("exercise[]") Long[] exercise,
						@Param("feedind[]") Long[] feeding,
						@PathVariable("id") Long id, Principal principal)
						throws Exception {

		TrainingPerDay entity = service.read(id);
		entity.getExcersices().clear();
		if(exercise!=null && exercise.length!=0) {
			for(int i=0;i<exercise.length;i++) {
				entity.getExcersices().add(serviceExercise.read(exercise[i]));}
		}
		
		entity.getProducts().clear();
		if(feeding !=null && feeding.length!=0) {
			for(int i=0;i<feeding.length;i++) {
				entity.getProducts().add(serviceProduct.read(feeding[i]));}
		}
		
		trainingPerDayService.update(entity);
		
		
		model.addAttribute("id",id);

		return "redirect:/result/get/"+id;
	}
	

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return "redirect:/result";
	}

	
	@RequestMapping(path = "/create", method = RequestMethod.GET)
	public String createOrUpdate(Principal principal) throws Exception {
		System.out.println("CREATING");
		User user = userService.findByUsername(principal.getName());
		java.util.Date date = new java.util.Date(System.currentTimeMillis());
		TrainingPerDay temp;
		List<TrainingPerDay> list = trainingPerDayService.repository.findAllByDateTraining(date);
		
		
		boolean isCreated=false;
		if(list!=null) {
			for(int i=0;i<list.size();i++)
				if(list.get(i).getUser().getId()==user.getId())
					isCreated=true;
		}
		//если ничего не нашли
		if(list==null || !isCreated) {
			temp = new TrainingPerDay();
			temp.setDateTraining(date);
			temp.setUser(user);
			trainingPerDayService.create(temp);
			List<TrainingPerDay> training = trainingPerDayService.repository.findAllByDateTraining(date);
			user.getTrainings().add(training.get(0));
			userService.update(user);
		}
//		если такое есть, делать ничего не нужно
		else {}
		
		return "redirect:/result";
	}

}
