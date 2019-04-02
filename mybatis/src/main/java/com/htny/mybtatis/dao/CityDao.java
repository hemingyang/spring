package com.htny.mybtatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.htny.mybtatis.domain.City;

public interface CityDao {

	
	List<City>findAll();
	
	City findById(@Param("id")Long id);
	
	Long saveCity(City city);
	
	Long updateCity(City city);
	
	Long deleteCity(@Param("id")Long id);
}
