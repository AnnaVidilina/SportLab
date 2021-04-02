package com.myProject.sport.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myProject.sport.entity.User;
import com.myProject.sport.service.UserServiceImpl;

@Controller
@RequestMapping("/normCounter")
public class NormCounterController {
	@Autowired
	UserServiceImpl userService;
	
	@GetMapping
	String getNormCounter(Model model,Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("height",(int)user.getHeight());
		model.addAttribute("weight",(int)user.getWeight());
		
		return "normCounter";
	}

	@PostMapping
	String postNormCounter(Model model, @Param("age") Integer age, @Param("height") Integer height, @Param("weight") Integer weight,
			@Param("k") String k, @Param("pol") Integer pol, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		
		float kk=Float.parseFloat(k);
		if(age==null || height==null||weight==null ||k==null||pol==null)return "normCounter";
		float BOO;
		if(pol==1)
			BOO = (float) (10*weight + 6.25*height - 5*age + 5);
		else
			BOO=(float) (10*weight + 6.25*height - 5*age - 161);		

		model.addAttribute("height",(int)user.getHeight());
		model.addAttribute("weight",(int)user.getWeight());
		model.addAttribute("BOO",BOO);
		model.addAttribute("norm",kk*BOO);
		
		return "normCounter";
	}

}
