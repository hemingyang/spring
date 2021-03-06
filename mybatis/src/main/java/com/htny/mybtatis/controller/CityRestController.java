package com.htny.mybtatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.htny.mybtatis.domain.City;
import com.htny.mybtatis.service.CityService;

@RestController
public class CityRestController {

	@Autowired
	private CityService cityService;

	@RequestMapping(value = "/api/city/{id}", method = RequestMethod.GET)
	public City findOneCtiy(@PathVariable("id") Long id) {

		return cityService.findCityById(id);
	};

	@RequestMapping(value = "/api/city", method = RequestMethod.POST)
	public void createCity(@RequestBody City city) {
		cityService.saveCity(city);
	}

	@RequestMapping(value = "/api/city", method = RequestMethod.PUT)
	public void modifyCity(@RequestBody City city) {
		cityService.updateCity(city);
	}

	@RequestMapping(value = "/api/city/{id}", method = RequestMethod.DELETE)
	public void modifyCity(@PathVariable("id") Long id) {
		cityService.deleteCity(id);
	}

}
