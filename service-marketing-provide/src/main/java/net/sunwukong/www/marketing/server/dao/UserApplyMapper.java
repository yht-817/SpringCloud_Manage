package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.UserApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserApplyMapper extends BaseMapper<UserApply> {

    /**
     * 根据用户编码和审核状态查询申请信息
     * 每一个审核类型只能存在一条数据
     *
     * @param userNo     用户编码
     * @param auditState 审核状态（10070001：待审核 10070002：已审核 10070003：已驳回）
     * @return
     */
    UserApply findByUserNoAndAuditState(String userNo, String auditState);

    /**
     * 根据申请编码查询申请信息
     * @param applyNo   申请编码
     * @return
     */
    UserApply finByApplyNo(String applyNo);


    /**
     * 获取运营官下面的服务机构列表
     * @param userNoManger  城市运营官编码
     * @param start         开始位置
     * @param pageSize      结束位置
     * @param auditState    审核状态（10070001：待审核 10070002：已审核 10070003：已驳回）
     * @return
     */
    List<UserApply> findByUserNoManagerAndAuditState(@Param(value = "userNoManger") String userNoManger,
                                                            @Param(value = "start") Integer start,
                                                            @Param(value = "pageSize") Integer pageSize,
                                                            @Param(value = "auditState") String auditState);

    /**
     * 根据用户编码获取首次申请信息(个体首次申请,机构首次申请)
     * @param userNo    用户编码
     * @param gtType    个体类型
     * @param jgType    机构类型
     * @return  返回对象
     */
    UserApply determineIfThereIsAnInitialApplication(@Param(value = "userNo") String userNo,
                                                     @Param(value = "gtType") String gtType,
                                                     @Param(value = "jgType") String jgType);
}