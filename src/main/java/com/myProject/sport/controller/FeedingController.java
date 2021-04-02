package com.myProject.sport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myProject.sport.entity.Product;
import com.myProject.sport.exception.RecordNotFoundException;
import com.myProject.sport.service.ProductService;

@Controller
@RequestMapping("/feeding")
public class FeedingController {
	@Autowired
	ProductService service;

	@RequestMapping
	public String getAll(Model model) {
		List<Product> list = service.getAll();

		model.addAttribute("list", list);
		return "feeding-list";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name="id",required = false) Long id) throws RecordNotFoundException {
		if (id!=null) {
			Product entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new Product());
		}
		return "feeding-add-edit";
	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return "redirect:/feeding";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(Product entity) throws Exception {
		service.create(entity);
		return "redirect:/feeding";
	}
}