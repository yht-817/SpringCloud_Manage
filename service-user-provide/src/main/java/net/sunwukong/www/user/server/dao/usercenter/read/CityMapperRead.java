package net.sunwukong.www.user.server.dao.usercenter.read;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.bean.City;

public interface CityMapperRead extends BaseMapper<City> {
    City findByCity(String cityNo);
}