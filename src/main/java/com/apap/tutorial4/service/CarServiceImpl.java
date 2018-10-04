package com.apap.tutorial4.service;

import java.util.List;
import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.repository.CarDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {
	@Autowired
	private CarDb carDb;
	
	@Override
	public void addCar(CarModel car) {
		// TODO Auto-generated method stub
		carDb.save(car);
		
	}
	
	public void deleteCar(CarModel car) {
		carDb.delete(car);
	}
	
	@Override
	public CarModel findCarById(long id) {
		return carDb.findById(id).get();
		
	}
	
	@Override
	public void carUpdate(CarModel updateCar, Long carId) {
		CarModel dataLama = carDb.findById(carId).get();
		dataLama.setBrand(updateCar.getBrand());
		dataLama.setType(updateCar.getType());
		dataLama.setAmount(updateCar.getAmount());
		dataLama.setPrice(updateCar.getPrice());
		carDb.save(dataLama);
	}
}
