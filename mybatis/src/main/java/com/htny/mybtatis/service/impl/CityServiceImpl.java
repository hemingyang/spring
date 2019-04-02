package com.htny.mybtatis.service.impl;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htny.mybtatis.dao.CityDao;
import com.htny.mybtatis.domain.City;
import com.htny.mybtatis.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

	@Autowired
	private CityDao cityDao;

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	@Transactional
	public City findCityById(Long id) {
		String key = "city_" + id;
		ValueOperations<String, City> operations = redisTemplate.opsForValue();
		boolean hashKey = redisTemplate.hasKey(key);
		if (hashKey) {
			City city = operations.get(key);
			LOGGER.info("CityServiceImpl.findCityById() : 从缓存中获取了城市 >> " + city.toString());
			return city;
		}

		City city = cityDao.findById(id);
		operations.set(key, city, 10, TimeUnit.SECONDS);
		LOGGER.info("CityServiceImpl.findCityById() : 城市插入缓存 >>" + city.toString());
		return city;

	}

	@Override
	@Transactional
	public Long saveCity(City city) {
		return cityDao.saveCity(city);
	}

	@Override
	@Transactional
	public Long updateCity(City city) {
		Long ret = cityDao.updateCity(city);

		// 缓存存在，删除缓存
		String key = "city_" + city.getId();
		boolean hasKey = redisTemplate.hasKey(key);
		if (hasKey) {
			redisTemplate.delete(key);

			LOGGER.info("CityServiceImpl.updateCity() : 从缓存中删除城市 >> " + city.toString());
		}

		return ret;
	}

	@Override
	@Transactional
	public Long deleteCity(Long id) {
		Long ret = cityDao.deleteCity(id);

		// 缓存存在，删除缓存
		String key = "city_" + id;
		boolean hasKey = redisTemplate.hasKey(key);
		if (hasKey) {
			redisTemplate.delete(key);

			LOGGER.info("CityServiceImpl.deleteCity() : 从缓存中删除城市 ID >> " + id);
		}
		return ret;
	}

}
