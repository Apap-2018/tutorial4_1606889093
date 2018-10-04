package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.*;
import com.apap.tutorial4.service.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DealerController {
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private CarService carService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/dealer/add", method = RequestMethod.GET)
	private String add (Model model) {
		model.addAttribute("dealer", new DealerModel());
		return "addDealer";
	}
	
	@RequestMapping(value = "/dealer/add", method = RequestMethod.POST)
	private String addDealerSubmit(@ModelAttribute DealerModel dealer) {
		dealerService.addDealer(dealer);
		return "add";
	}
	
	@RequestMapping(value = "/dealer/view/{dealerId}", method = RequestMethod.GET)
	public String viewDealer(@PathVariable ("dealerId") long dealerId, Model model) {
		DealerModel archive = dealerService.getDealerDetailByID(dealerId).get();
		List<CarModel>carList = archive.getListCar();
		
		model.addAttribute("list", carList);
		model.addAttribute("dealer", archive);
		return "view-dealer";
	}
	
	@RequestMapping(value = "/dealer/delete/{dealerId}", method = RequestMethod.GET)
	public String delete(@PathVariable ("dealerId") long dealerId, Model model) {
		DealerModel archive = dealerService.getDealerDetailByID(dealerId).get();
		
		
		dealerService.deleteDealer(archive);
		return "delete";
		
	}
	@RequestMapping(value = "/dealer/viewall", method = RequestMethod.GET)
	public String viewAll(Model model) {
		List<DealerModel>dealerList = dealerService.searchAll().findAll();
		
		model.addAttribute("list", dealerList);
		return "view-all";
	}
	
	@RequestMapping(value = "/dealer/update/{dealerId}", method = RequestMethod.GET)
	private String update(@PathVariable(value = "dealerId") Long dealerId, Model model) {
		DealerModel archive = dealerService.getDealerDetailByID(dealerId).get();
		
		model.addAttribute("dealer", archive);
		return "update-dealer";
	}
		
		
	@RequestMapping(value = "/dealer/update/{dealerId}", method = RequestMethod.POST)
	private String updateDealerSubmit(@PathVariable(value = "dealerId") Long dealerId, @ModelAttribute DealerModel dealer) {
		dealerService.dealerUpdate(dealer, dealerId);
		return "update";
		
	}
}
