package com.jspiders.cardekho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.cardekho.pojo.Car;
import com.jspiders.cardekho.response.ResponseStructure;
import com.jspiders.cardekho.service.CarService;

@RestController
public class CarController {

	@Autowired
	private CarService carService;
	
	
	@PostMapping("/car")
	public ResponseEntity<ResponseStructure<Car>> addCar(@RequestBody Car car){
		
		Car addedCar = carService.addCar(car);
		
		ResponseStructure<Car> response = new ResponseStructure<>();
		
		response.setData(addedCar);
		response.setMessage("car added");
		response.setStatus(HttpStatus.CREATED.value());
		
		return new ResponseEntity<ResponseStructure<Car>>(response, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/cars")
	public ResponseEntity<ResponseStructure<List<Car>>> showAllCars(){
		List<Car> cars = carService.showAllCars();
		
		ResponseStructure<List<Car>> response = new ResponseStructure<>();
		
		if(cars != null && cars.size()>0) {
			response.setData(cars);
			response.setMessage("cars fetched");
			response.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<>(response, HttpStatus.FOUND);
				
		}
		
		else {
			response.setData(cars);
			response.setMessage("cars not found");
			response.setStatus(HttpStatus.NOT_FOUND.value());
			
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("/car")
	public ResponseEntity<ResponseStructure<Car>> updateCar(@RequestBody Car car){
		Car updatedCar = carService.addCar(car);
		ResponseStructure<Car> response = new ResponseStructure<>();
		
		if(updatedCar != null) {
			response.setData(updatedCar);
			response.setMessage("car updated");
			response.setStatus(HttpStatus.OK.value());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			response.setData(updatedCar);
			response.setMessage("car not found");
			response.setStatus(HttpStatus.NOT_FOUND.value());
			
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
	
}
