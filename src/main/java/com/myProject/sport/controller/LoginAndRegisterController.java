package com.myProject.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myProject.sport.entity.Measurement;
import com.myProject.sport.entity.User;
import com.myProject.sport.service.UserServiceImpl;

@Controller
@RequestMapping
public class LoginAndRegisterController {

    @Autowired
    UserServiceImpl userService;

    
    @PostMapping("/registration")
    public String registraionUser(Model model, @ModelAttribute User user,@ModelAttribute Measurement measurement,RedirectAttributes redirectAttributes) {
        boolean success = userService.userRegistration(user,measurement);
        String response = success ? "Успешно зарегистрирован" : "Ошибка регистрации. Попробуйте позже.";
        redirectAttributes.addFlashAttribute("success", response);
        return "redirect:/";
    }
    @GetMapping("/registration")
    public String getRegistrationPage() {
    	return "registration";
    }
    

}
