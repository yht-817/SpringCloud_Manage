package net.sunwukong.www.user.server.dao.usercenter.write;

import org.apache.ibatis.annotations.Param;

public interface UserReflectsWrite {
    // 添加银行卡的数据
    int addReflectMeth(@Param(value = "id") String id, @Param(value = "userNo") String userNo, @Param(value = "userName") String userName,
                       @Param(value = "userPhone") String userPhone, @Param(value = "userAddress") String userAddress,
                       @Param(value = "userAccount") String userAccount,@Param(value = "bankName") String bankName);

    // 插入申请的数据列表
    int addSQinfo(@Param(value = "id") String id, @Param(value = "userNo") String userNo, @Param(value = "data") String data,
                  @Param(value = "amount") String amount, @Param(value = "sqno") String sqno, @Param(value = "sqstates") String sqstates,
                  @Param(value = "userAccount") String userAccount, @Param(value = "userName") String userName,
                  @Param(value = "userAddres") String userAddres, @Param(value = "userPhone") String userPhone,@Param(value = "bankName") String bankName);

    // 删除当前银行卡信息
    int deleteInfo(@Param(value = "userNo") String userNo);

    // 插入用户提现日志
    int addLog(@Param(value = "id") String id, @Param(value = "userNo") String userNo, @Param(value = "changType") String changType,
               @Param(value = "amount") String amount, @Param(value = "data") String data, @Param(value = "sqno") String sqno, @Param(value = "changeremark") String changeremark);

    // 减去金额
    int subtractionTx(@Param(value = "userNo") String userNo, @Param(value = "amount") String amount);

    // 添加金额
    int addJe(@Param(value = "userNo") String userNo, @Param(value = "amount") String amount);

    // 删除日志
    int deleteLog(@Param(value = "sqno") String sqno);
}
