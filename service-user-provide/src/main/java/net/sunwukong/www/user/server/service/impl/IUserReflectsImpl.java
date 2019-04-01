package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.base.util.CardNumberCheck;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.user.server.dao.usercenter.read.UserReflectsRead;
import net.sunwukong.www.user.server.dao.usercenter.write.UserReflectsWrite;
import net.sunwukong.www.user.server.service.IUserReflects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class IUserReflectsImpl implements IUserReflects {
    private static final Logger log = LoggerFactory.getLogger(IUserReflectsImpl.class);
    @Autowired
    private UserReflectsWrite userReflectsWrite;
    @Autowired
    private UserReflectsRead userReflectsRead;

    /**
     * 绑定银行卡信息
     */
    public ResponseData addReflectMeth(Map<String, String> reflectInfo) {
        ResponseData responseData = new ResponseData();
        String id = DataBaseTool.createId();
        String userNo = reflectInfo.get("userNo");
        String userName = reflectInfo.get("userName");
        String userPhone = reflectInfo.get("userPhone");
        String userAddress = reflectInfo.get("userAddress");
        String userAccount = reflectInfo.get("userAccount");
        String bankName = reflectInfo.get("bankName");
        if (userNo != null && userNo != "" && userName != null && userName != "" && userPhone != null && userPhone != ""
                && userAddress != "" && userAddress != null && userAccount != null && userAccount != "" && bankName != null && bankName != "") {
            boolean cartf = CardNumberCheck.checkBankCard(userAccount);
            if (cartf) {
                // 1.0 查看用户是否存在当前的账号
                int exits = userReflectsRead.getExtis(userNo);
                if (exits == 0) {
                    int addinfo = userReflectsWrite.addReflectMeth(id, userNo, userName, userPhone, userAddress, userAccount, bankName);
                    if (addinfo > 0) {
                        responseData.setCode(200);
                        responseData.setData("银行卡信息添加成功");
                        return responseData;
                    }
                    responseData.setData("银行卡信息添加失败");
                    responseData.setCode(500);
                    return responseData;
                }
                responseData.setData("银行卡信息已存在");
                responseData.setCode(500);
                return responseData;
            } else {
                responseData.setData("添加正确的银行卡号！");
                responseData.setCode(500);
                return responseData;
            }
        } else {
            responseData.setData("绑定的参数不能为空！");
            responseData.setCode(500);
            return responseData;
        }
    }

    /**
     * 查看用户是否存在
     */
    public boolean seeUserInfoR(Map<String, String> reflectInfo) {
        String userNo = reflectInfo.get("userNo");
        int contuserinfo = userReflectsRead.getContUserInfo(userNo);
        if (contuserinfo > 0) {
            return true;
        }
        return false;
    }

    /**
     * 查询用户的提现金额
     */
    public BigDecimal getMony(Map<String, String> reflectInfo) {
        String userNo = reflectInfo.get("userNo");
        return userReflectsRead.getTx(userNo);
    }


    /**
     * 进行用户提现下单
     */
    public ResponseData addOrder(Map<String, String> reflectInfo) {
        ResponseData responseData = new ResponseData();
        String userNo = reflectInfo.get("userNo");
        String amount = reflectInfo.get("amount");
        String userAccount = reflectInfo.get("userAccount");
        System.out.println("用户编码：" + userNo + "金额：" + amount + "银行卡：" + userAccount);
        if (userAccount != null && userAccount != "" && userNo != null && userNo != "" && amount != null && amount != "") {
            // 单次金额不能大于1000
            if (Integer.valueOf(amount) <= 1000) {
                // 求一周的交易次数不能超过2次
                int sum = userReflectsRead.getTransactionNumber(DataBaseTool.getMonday(), userNo);
                log.error("当前数据库里面的数据：" + sum);
                if (sum < 2) {
                    // 查询当前用户的可以提现的金额
                    BigDecimal tx = userReflectsRead.getTx(userNo);
                    BigDecimal hq = BigDecimal.valueOf(Long.valueOf(amount));
                    if (tx.compareTo(hq) == 1 || tx.compareTo(hq) == 0) { // 进行提现
                        // 进行数据提现申请下单
                        String id = DataBaseTool.createId();
                        String sqno = "SQTX" + id;
                        String data = DataBaseTool.createDate();
                        String sqstates = "105210003";
                        // 加入提现日志 user_cash_account_log
                        String changType = "10330004";
                        String changeremark = "用户提现";
                        int logtx = userReflectsWrite.addLog(id, userNo, changType, amount, data, sqno, changeremark);
                        // 进行申请
                        if (logtx > 0) {
                            // 减去提现的现有金额
                            int subtraction = userReflectsWrite.subtractionTx(userNo, amount);
                            if (subtraction > 0) {
                                // 查询当前用户的信息
                                Map<String, String> usercarinfo = userReflectsRead.seeCarInfo(userNo);
                                try {
                                    int sq = userReflectsWrite.addSQinfo(id, userNo, data, amount, sqno, sqstates, userAccount, usercarinfo.get("userName"), usercarinfo.get("userAddres"), usercarinfo.get("userPhone"), usercarinfo.get("bankName"));
                                    if (sq > 0) { // 申请体现成功
                                        responseData.setData("正在提现中..");
                                        responseData.setCode(200);
                                        return responseData;
                                    }
                                } catch (Exception e) {
                                    log.error("申请提现失败");
                                }
                                // 插入提现数据失败，返回减去的提现金额，删除日志信息
                                int addJe = userReflectsWrite.addJe(userNo, amount);
                                int delog = userReflectsWrite.deleteLog(sqno);
                                if (addJe == 0 && delog == 0) {
                                    log.error("提现订单处理失败后回滚失败，订单号：" + sqno);
                                }
                                responseData.setData("提现失败，请稍后重试!");
                                responseData.setCode(500);
                                return responseData;
                            }
                            responseData.setData("提现失败，请稍后重试!");
                            responseData.setCode(500);
                            return responseData;
                        }
                        responseData.setData("提现失败，请稍后重试!");
                        responseData.setCode(500);
                        return responseData;
                    }
                    responseData.setData("提现余额不足！");
                    responseData.setCode(500);
                    return responseData;
                }
                responseData.setData("您的提现次数超过规定次数！");
                responseData.setCode(500);
                return responseData;
            }
            responseData.setData("提现金额大于预定额度！");
            responseData.setCode(500);
            return responseData;
        }
        responseData.setData("参数错误");
        responseData.setCode(500);
        return responseData;
    }


    /**
     * 解除账号
     */
    public ResponseData removeAccount(Map<String, String> reflectInfo) {
        ResponseData responseData = new ResponseData();
        String userNo = reflectInfo.get("userNo");
        if (userNo != null && userNo != "") {
            // 查询当前账号是否还有没有提现操作
            int exist = userReflectsRead.exist(userNo);
            if (exist == 0) {
                // 删除当前的信息
                int de = userReflectsWrite.deleteInfo(userNo);
                if (de > 0) {
                    responseData.setData("删除信息成功");
                    responseData.setCode(200);
                    return responseData;
                } else {
                    responseData.setData("删除信息失败");
                    responseData.setCode(500);
                    return responseData;
                }
            } else {
                responseData.setData("当前还有订单未处理");
                responseData.setCode(500);
                return responseData;
            }
        }
        responseData.setData("用户参数错误");
        responseData.setCode(500);
        return responseData;
    }

    /**
     * 查询当前用户的卡信息
     */
    public Map<String, String> seeCarNo(Map<String, String> reflectInfo) {
        String userNo = reflectInfo.get("userNo");
        return userReflectsRead.seeCar(userNo);
    }


}
