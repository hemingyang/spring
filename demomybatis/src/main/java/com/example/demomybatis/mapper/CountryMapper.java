package com.example.demomybatis.mapper;

import com.example.demomybatis.model.Country;
import com.example.demomybatis.util.MyMapper;
import org.apache.ibatis.annotations.Select;

public interface CountryMapper extends MyMapper<Country> {

    @Select("select*from country limit 1")
    Country findOne();


}
