package com.jspiders.cardekho.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspiders.cardekho.pojo.Car;
import com.jspiders.cardekho.repository.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;
	
	public Car addCar(Car car) {
		
		Optional<Car> optionalCar = carRepository.findById(car.getId());
		Car existing = optionalCar.orElse(null);
		Car addedCar = null;
		
		if(existing != null) {
			existing.setName(car.getName());
			existing.setBrand(car.getBrand());
			existing.setPrice(car.getPrice());
			addedCar = carRepository.save(existing);
		
		}
		else {
			addedCar = carRepository.save(car);
		}
		
		return addedCar;
		
		
		
	}
	
	public List<Car> showAllCars(){
		
		return carRepository.findAll();
	
	}
	
	
	
}
