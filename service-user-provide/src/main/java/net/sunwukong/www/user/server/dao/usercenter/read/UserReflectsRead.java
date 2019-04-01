package net.sunwukong.www.user.server.dao.usercenter.read;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Map;

public interface UserReflectsRead {
    // 查看用户是否存在当前银行卡信息
    int getContUserInfo(@Param(value = "userNo") String userNo);

    BigDecimal getTx(@Param(value = "userNo") String userNo);

    // 查看用户是否存在当前数据
    int getExtis(@Param(value = "userNo") String userNo);

    // 查看用户是否还有没有完成的操作
    int exist(@Param(value = "userNo") String userNo);

    // 查询当前用户的卡信息
    Map<String, String> seeCar(@Param(value = "userNo") String userNo);

    // 查询当前卡的信息
    Map<String, String> seeCarInfo(@Param(value = "userNo") String userNo);

    // 求一周的交易次数
    int getTransactionNumber(@Param(value = "monday") String monday,@Param(value = "userNo") String userNo);
}
