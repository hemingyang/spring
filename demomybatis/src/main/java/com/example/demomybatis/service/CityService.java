package com.example.demomybatis.service;

import com.example.demomybatis.mapper.CityMapper;
import com.example.demomybatis.model.City;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityMapper cityMapper;

    public List<City>getAll(City city){
            if (city.getPage()!=null&&city.getRows()!=null){
                PageHelper.startPage(city.getPage(),city.getRows());
            }
            return cityMapper.selectAll();

    }


    public  City getById(Integer id){
        return cityMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id){
        cityMapper.deleteByPrimaryKey(id);
    }


    public void save(City country){
        if (country.getId()!=null){
            cityMapper.updateByPrimaryKey(country);
        }else {
            cityMapper.insert(country);
        }
    }

}
