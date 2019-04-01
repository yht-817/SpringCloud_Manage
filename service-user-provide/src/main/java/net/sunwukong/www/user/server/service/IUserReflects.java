package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;

import java.math.BigDecimal;
import java.util.Map;

public interface IUserReflects {
    ResponseData addReflectMeth(Map<String, String> reflectInfo);

    boolean seeUserInfoR(Map<String, String> reflectInfo);

    ResponseData addOrder(Map<String, String> reflectInfo);

    BigDecimal getMony(Map<String, String> reflectInfo);

    ResponseData removeAccount(Map<String, String> reflectInfo);

    Map<String,String> seeCarNo(Map<String, String> reflectInfo);
}

