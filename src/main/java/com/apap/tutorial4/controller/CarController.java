package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.*;
import com.apap.tutorial4.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "dealerId") Long dealerId, Model model) {
		CarModel car = new CarModel();
		DealerModel dealer = dealerService.getDealerDetailByID(dealerId).get();
		car.setDealer(dealer);
		
		model.addAttribute("car", car);
		return "addCar";
	}
		
		
	@RequestMapping(value = "/car/add", method = RequestMethod.POST)
	private String addCarSubmit(@ModelAttribute CarModel car) {
		carService.addCar(car);
		return "add";
		
	}

	@RequestMapping(value = "/car/delete/{carId}", method = RequestMethod.GET)
	private String delete(@PathVariable(value = "carId") Long carId, Model model) {
		CarModel car = carService.findCarById(carId);
		carService.deleteCar(car);
		
		model.addAttribute("car", car);
		return "delete";
	}
	
	
	@RequestMapping(value = "/car/update/{carId}", method = RequestMethod.GET)
	private String update(@PathVariable(value = "carId") Long carId, Model model) {
		CarModel car = carService.findCarById(carId);
		
		model.addAttribute("car", car);
		return "update-car";
	}
		
		
	@RequestMapping(value = "/car/update/{carId}", method = RequestMethod.POST)
	private String updateCarSubmit(@PathVariable(value = "carId") Long carId, @ModelAttribute CarModel car) {
		carService.carUpdate(car, carId);
		return "update";
		
	}
}
 