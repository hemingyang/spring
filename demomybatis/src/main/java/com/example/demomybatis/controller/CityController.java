package com.example.demomybatis.controller;

import com.example.demomybatis.model.City;
import com.example.demomybatis.service.CityService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    public PageInfo<City>getAll(City city){
        List<City>cityList =cityService.getAll(city);

        return  new PageInfo<City>(cityList);

    }
        @RequestMapping(value = "/add")
        public City add(){
         return  new City();
        }

        public City view(@PathVariable Integer id){
            ModelAndView result = new ModelAndView();
            City city = cityService.getById(id);
            return city;
        }
    @RequestMapping(value = "/delete/{id}")
    public ModelMap delete(@PathVariable Integer id) {
        ModelMap result = new ModelMap();
        cityService.deleteById(id);
        result.put("msg", "删除成功!");
        return result;
    }

    // @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping(value = "/aave")
    public ModelMap save(City city) {
        ModelMap result = new ModelMap();
        String msg = city.getId() == null ? "新增成功!" : "更新成功!";
        cityService.save(city);
        result.put("city", city);
        result.put("msg", msg);
        return result;
    }


}
