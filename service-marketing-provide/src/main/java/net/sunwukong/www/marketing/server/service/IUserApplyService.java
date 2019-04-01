package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.UserApply;
import net.sunwukong.www.marketing.vo.SubmitApplyVo;
import net.sunwukong.www.user.vo.ServerDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 说明:个体/机构申请服务接口
 *
 * @author Mick
 * @CreateDate 2018/6/28 16:07
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IUserApplyService {
    /**
     * 保存提交申请信息
     * @param submitApplyVo
     * @return
     */
    ResponseData saveSubmitApply(SubmitApplyVo submitApplyVo);

    /**
     * 添加申请信息
     * @param userApply
     * @return
     */
    ResponseData addUserApply(UserApply userApply);

    /**
     * 审核服务申请
     * @param applyNo       申请编码
     * @param auditState    审核状态
     * @return              响应对象
     */
    ResponseData updateAuditUserApply(String applyNo,String auditState);

    /**
     * 修改申请信息
     * @param userApply
     * @return
     */
    ResponseData updateUserApply(UserApply userApply);

    /**
     * 根据申请编码查询申请信息
     * @param applyNo
     * @return
     */
    ResponseData findByApplyNo(String applyNo);

    /**
     * 服务机构星级计算
     * 机构星级＝(每单获得的分数总和)累计获取分数/单数
     * @author kangdong
     * @param userNo
     * @return
     */
    ResponseData getUserStar(String userNo);


    /**
     * 查看服务机构详情信息接口
     * @author kangdong
     * @param map
     * @return
     */
    ResponseData getOrganizationDetail(Map<String, String> map);

    /**
     * 保存服务数据详情
     * @param data
     * @return
     */
    ResponseData saveServerDetails(ServerDetails data);

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
     * 获取运营官下面的服务机构列表
     * @param userNoManger  城市运营官编码
     * @param auditState    审核状态（10070001：待审核 10070002：已审核 10070003：已驳回）
     * @return
     */
    List<UserApply> findByUserNoManagerAndAuditState(@Param(value = "userNoManger") String userNoManger,
                                                     @Param(value = "auditState") String auditState);

    /**
     * 根据用户编码和审核状态查询申请信息
     * @param userNo        用户编码
     * @param auditState    审核状态
     * @return
     */
    UserApply findByUserNoAndAuditState(String userNo, String auditState);
}
