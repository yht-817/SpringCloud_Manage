package net.sunwukong.www.marketing.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Throwables;
import com.sdkinfo.www.http.HttpRequest;
import com.sdkinfo.www.log.StaticLog;
import net.sunwukong.www.api.crypto.symmetric.AES128UtilBase64;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.JSONUtil;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.*;
import net.sunwukong.www.marketing.server.api.ServiceUrl;
import net.sunwukong.www.marketing.server.dao.DemandEvaluateMapper;
import net.sunwukong.www.marketing.server.dao.UserApplyMapper;
import net.sunwukong.www.marketing.server.service.IUserApplyService;
import net.sunwukong.www.marketing.server.web.BaseController;
import net.sunwukong.www.marketing.vo.SubmitApplyVo;
import net.sunwukong.www.user.bean.UserCategory;
import net.sunwukong.www.user.bean.UserCity;
import net.sunwukong.www.user.bean.UserScope;
import net.sunwukong.www.user.vo.ServerDetails;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 说明:个体/机构申请服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/6/28 16:09
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IUserApplyServiceImpl extends BaseController implements IUserApplyService {
    private static final Logger log = LoggerFactory.getLogger(IUserApplyServiceImpl.class);

    @Autowired
    UserApplyMapper userApplyMapper;

    @Autowired
    DemandEvaluateMapper demandEvaluateMapper;

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData saveSubmitApply(SubmitApplyVo vo) {
        ResponseData responseData = new ResponseData();
        Map<String,String> data = new HashMap<>();
        //判断用户是否有待审核的申请
        UserApply byUserNoAndAuditState = userApplyMapper.findByUserNoAndAuditState(vo.getUserNo(),SysCode.CODE_10070001.getCode());
        if (byUserNoAndAuditState!=null){
            throw new GrilException("你有申请待审核");
        }
        //封装申请信息
        UserApply userApply = new UserApply();
        userApply.setApplyNo(DataBaseTool.createNo("apply"));
        userApply.setApplyType(vo.getApplyType());
        //判断申请类型
        if (userApply.getApplyType().equals(SysCode.CODE_10300001.getCode())
                || userApply.getApplyType().equals(SysCode.CODE_10300003.getCode())) {
            //首次申请
            //判断用户是否已经存在首次申请
            UserApply byUserNoAndApplyType = userApplyMapper.determineIfThereIsAnInitialApplication(vo.getUserNo(),SysCode.CODE_10300001.getCode(),SysCode.CODE_10300003.getCode());
            if (byUserNoAndApplyType!=null){
                throw new GrilException("你已存在个体首次申请,无需再申请!");
            }
            userApply.setUserNo(vo.getUserNo());
            userApply.setUserNoManger(vo.getUserNoManger());
            userApply.setAddress(vo.getAddress());
            userApply.setPhoneNo(vo.getPhoneNo());
            userApply.setSexNo(vo.getSexNo());
            userApply.setContractNumber(vo.getContractNumber());
        } else if (userApply.getApplyType().equals(SysCode.CODE_10300002.getCode())
                || userApply.getApplyType().equals(SysCode.CODE_10300004.getCode())) {
            //补充申请
            //判断用户是否已经存在首次申请
            UserApply byUserNoAndApplyType = userApplyMapper.determineIfThereIsAnInitialApplication(vo.getUserNo(),SysCode.CODE_10300001.getCode(),SysCode.CODE_10300003.getCode());
            if (byUserNoAndApplyType==null){
                throw new GrilException("未查询到首次申请,无法补充申请!");
            }
            userApply.setUserNo(byUserNoAndApplyType.getUserNo());
            userApply.setUserNoManger(byUserNoAndApplyType.getUserNoManger());
            userApply.setAddress(byUserNoAndApplyType.getAddress());
            userApply.setPhoneNo(byUserNoAndApplyType.getPhoneNo());
            userApply.setSexNo(byUserNoAndApplyType.getSexNo());
            userApply.setContractNumber(byUserNoAndApplyType.getContractNumber());
        }else {
            throw new GrilException("申请状态有误!");
        }
        //保存数据到个体/机构申请表
        ResponseData<UserApply> addUserApply = addUserApply(userApply);

        //保存数据到个体/机构申请类目表
        List<UserApplyCategory> userApplyCategorys = vo.getUserApplyCategories();
        userApplyCategorys.forEach(userApplyCategory -> {
            userApplyCategory.setUserNo(userApply.getUserNo());
            userApplyCategory.setApplyNo(userApply.getApplyNo());
            iUserApplyCategoryService.addUserApplyCategory(userApplyCategory);
        });
        //保存数据到个体/机构申请所在城市表
        List<UserApplyCity> userApplyCitys = vo.getUserApplyCitys();
        if (userApplyCitys!=null){
            userApplyCitys.forEach(userApplyCity -> {
                userApplyCity.setUserNo(userApply.getUserNo());
                userApplyCity.setApplyNo(userApply.getApplyNo());
                iUserApplyCityService.addUserApplyCity(userApplyCity);
            });
        }
        //保存数据到个体/机构申请服务城市
        List<UserApplyScope> userApplyScopes = vo.getUserApplyScopes();
        userApplyScopes.forEach(userApplyScope -> {
            userApplyScope.setApplyNo(userApply.getApplyNo());
            userApplyScope.setUserNo(userApply.getUserNo());
            iUserApplyScopeService.addUserApplyScope(userApplyScope);
        });
        //保存数据到个体/机构申请图片表
        List<UserApplyPhoto> userApplyPhotos = vo.getUserApplyPhotos();
        if (userApplyPhotos!=null){
            userApplyPhotos.forEach(userApplyPhoto -> {
                userApplyPhoto.setApplyNo(userApply.getApplyNo());
                userApplyPhoto.setUserNo(userApply.getUserNo());
                iUserApplyPhotoService.addUserApplyPhoto(userApplyPhoto);
            });
        }
        //如果是个体服务者 就自动审核
        // 取消个体申请时的自动审核
//        if (userApply.getApplyType().equals(SysCode.CODE_10300001.getCode())
//                || userApply.getApplyType().equals(SysCode.CODE_10300002.getCode())) {
//            updateAuditUserApply(userApply.getApplyNo(),SysCode.CODE_10070002.getCode());
//        }
        return responseData;
    }

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addUserApply(UserApply userApply) {
        ResponseData responseData = new ResponseData();
        userApply.setId(DataBaseTool.createId());
        userApply.setApplyDate(new Date());
        userApply.setAuditState(SysCode.CODE_10070001.getCode());
        int i = userApplyMapper.insertSelective(userApply);
        if (i<=0){
            throw new GrilException("添加申请信息失败");
        }
        responseData.setData(userApply);
        return responseData;
    }

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData updateAuditUserApply(String applyNo,String auditState) {
        ResponseData responseData = new ResponseData();
        //1、根据申请编码查询申请信息
        ResponseData<UserApply> byApplyNo = findByApplyNo(applyNo);
        UserApply byIdUserApply = byApplyNo.getData();
        //如果申请信息状态不等于待审核状态(抛出异常)
        if (!byIdUserApply.getAuditState().equals(SysCode.CODE_10070001.getCode())){
            throw new GrilException("当前申请已经审核过了!");
        }
        //赋值新的审核状态
        byIdUserApply.setAuditState(auditState);

        //修改申请表的审核状态
        byIdUserApply.setAuditDate(new Date());
        updateUserApply(byIdUserApply);
        //2、判断审核状态是审核通过还是驳回
        if (byIdUserApply.getAuditState().equals(SysCode.CODE_10070002.getCode())){
            Map<String,String> map = new HashMap<>();
            map.put("userNo",byIdUserApply.getUserNo());
            map.put("applyNo",byIdUserApply.getApplyNo());
            map.put("auditState",SysCode.CODE_10070002.getCode());
            //3、获取申请的服务类目
            ResponseData<List<UserApplyCategory>> repCategory = iUserApplyCategoryService.getApplyNoAfterAuditCategoryList(map);
            List<UserApplyCategory> userApplyCategories = repCategory.getData();
            //4、获取申请的所在城市
            ResponseData<List<UserApplyCity>> repCity = iUserApplyCityService.getApplyNoAfterAuditCityList(map);
            List<UserApplyCity> userApplyCitys = repCity.getData();
            //5、获取申请的服务城市
            ResponseData<List<UserApplyScope>> applyNoAfterAuditScopeList = iUserApplyScopeService.getApplyNoAfterAuditScopeList(map);
            List<UserApplyScope> userApplyScopes = applyNoAfterAuditScopeList.getData();
            //5、添加服务类目到正式表中
            List<UserCategory> userCategories = new ArrayList<>();
            userApplyCategories.forEach(userApplyCategory -> {
                UserCategory userCategory = new UserCategory();
                userCategory.setCategoryOneNo(userApplyCategory.getCategoryOneNo());
                userCategory.setCategoryTwoNo(userApplyCategory.getCategoryTwoNo());
                userCategory.setUserNo(userApplyCategory.getUserNo());
                userCategories.add(userCategory);
            });
            //6、添加所在城市到正式表中
            List<UserCity> userCitys = new ArrayList<>();
            userApplyCitys.forEach(userApplyCity -> {
                UserCity userCity = new UserCity();
                userCity.setCityNo(userApplyCity.getCityNo());
                userCity.setUserNo(userApplyCity.getUserNo());
                userCitys.add(userCity);
            });
            //添加服务城市到正式表中
            List<UserScope> userScopes = new ArrayList<>();
            userApplyScopes.forEach(userApplyScope -> {
                UserScope userScope = new UserScope();
                userScope.setUserNo(byIdUserApply.getUserNo());
                userScope.setScopeOneNo(userApplyScope.getScopeOneNo());
                userScope.setScopeTwoNo(userApplyScope.getScopeTwoNo());
                userScopes.add(userScope);
            });
            ServerDetails httpData = new ServerDetails();
            //7、获取用户身份
            if (byIdUserApply.getApplyType().equals(SysCode.CODE_10300003.getCode()) || byIdUserApply.getApplyType().equals(SysCode.CODE_10300004.getCode())){
                //机构身份
                httpData.setUserType(SysCode.CODE_10020003.getCode());
            }else {
                //个人服务者身份
                httpData.setUserType(SysCode.CODE_10020004.getCode());
            }
            httpData.setUserNo(byIdUserApply.getUserNo());
            httpData.setUserCategories(userCategories);
            httpData.setUserCitys(userCitys);
            httpData.setUserScopes(userScopes);
            saveServerDetails(httpData);

        }else if (byIdUserApply.getAuditState().equals(SysCode.CODE_10070003.getCode())){//驳回
            //申请驳回,发送审核通知给用户
            //TODO
            StaticLog.info("申请驳回,发送审核通知给用户");
            //根据申请编码删除申请信息
        }else {
            throw new GrilException("输入的审核状态有误");
        }

        return responseData;
    }

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData updateUserApply(UserApply userApply) {
        ResponseData responseData = new ResponseData();
        int i = userApplyMapper.updateByPrimaryKeySelective(userApply);
        if (i<=0){
            throw new GrilException("添加申请信息失败");
        }
        return responseData;
    }

    @Override
    public ResponseData findByApplyNo(String applyNo) {
        ResponseData responseData = new ResponseData();
        UserApply userApply = userApplyMapper.finByApplyNo(applyNo);
        responseData.setData(userApply);
        return responseData;
    }



    /**
     * 服务机构星级计算
     * 机构星级＝(每单获得的分数总和)累计获取分数/单数
     * @author kangdong
     * @param userNo 服务机构编码
     * @return 返回服务机构星数
     */
    @Override
    public ResponseData getUserStar(String userNo) {
        ResponseData responseData = new ResponseData();
        try {
            int star = 0;
            //通过服务机构编码查询评价表
            HashMap<String,Object> mapStar = demandEvaluateMapper.getStarSumByUserNo(userNo);
            long number = Long.valueOf(mapStar.get("num").toString());//单数
            if(mapStar.get("total") != null && number !=0) {
                long total = Long.valueOf(mapStar.get("total").toString());//累计评分
                //最后星级＝累计评分／单数／项数
                star = (int)Math.rint((total/number)/3);//四舍五入取整数
            }
            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(star);
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }



    /**
     * 查看服务机构详情信息接口
     * @author kangdong
     * @param data
     * @return
     */
    @Override
    public ResponseData getOrganizationDetail(Map<String, String> data) {
        //通过用户编码获取服务类别和城市
        ResponseData<Map<String,Object>> responseData = getMerchanInfo(data.get("userNo"));
        try {
            Map<String, Object> map = responseData.getData();
            UserApply userApply = userApplyMapper.finByApplyNo(data.get("applyNo"));
            map.put("id",userApply.getId());
            map.put("applyNo",userApply.getApplyNo());
            map.put("address",userApply.getAddress());
            map.put("contractNumber",userApply.getContractNumber());
            map.put("applyDate",userApply.getApplyDate());
            responseData.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseData;
    }

    /**
     * 通过用户服务获取服务机构详情信息
     * @author kangdong
     * @param userNo   用户编码
     * @return
     */

    public ResponseData getMerchanInfo(String userNo) {
        ResponseData responseData = new ResponseData();
        try {
            Map<String, String> map = new HashMap<>();
            map.put("userNo",userNo);
            RequestData requestData = new RequestData();
            requestData.setUserNo(userNo);
            requestData.setData(AES128UtilBase64.encryptAES(JSONUtil.map2json(map)));
            HttpRequest httpRequest = HttpRequest.post(ServiceUrl.GET_MERCHAN_APPLY_INFO).body(JSONUtil.bean2json(requestData)).contentType("application/json");
            JSONObject jsonObject = JSON.parseObject(httpRequest.execute().body());
            if (jsonObject.getInteger("code") != StatusEnum.SUCCESS.getCode()){
                throw new GrilException("服务信息获取失败");
            }
            responseData.setData(jsonObject.get("data"));
        } catch (GrilException e) {
            throw new GrilException("获取服务机构详情信息失败");
        }
        return responseData;
    }

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData saveServerDetails(ServerDetails data) {
        ResponseData responseData = new ResponseData();
        //保存服务类目
        List<UserCategory> userCategories = data.getUserCategories();
        userCategories.forEach(userCategory -> {
            iUserCategoryService.addUserCategory(userCategory);
        });
        //保存所在城市
        List<UserCity> userCitys = data.getUserCitys();
        userCitys.forEach(userCity -> {
            iUserCityService.addUserCity(userCity);
        });
        //保存服务城市
        List<UserScope> userScopes = data.getUserScopes();
        userScopes.forEach(userScope -> {
            iUserScopeService.addUserScope(userScope);
        });
        //修改用户身份信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserNo(data.getUserNo());
        userInfo.setUserType(data.getUserType());
        iUserInfoServer.updateByUserNoSelective(userInfo);
        return responseData;
    }

    /**
     * 获取运营官下面的服务机构列表
     * @param userNoManger  城市运营官编码
     * @param start         开始位置
     * @param pageSize      结束位置
     * @param auditState    审核状态（10070001：待审核 10070002：已审核 10070003：已驳回）
     * @return
     */
    @Override
    public List<UserApply> findByUserNoManagerAndAuditState(String userNoManger, Integer start, Integer pageSize, String auditState) {
        return userApplyMapper.findByUserNoManagerAndAuditState(userNoManger,start,pageSize,auditState);
    }

    /**
     * 获取运营官下面的服务机构列表(默认全部数据)
     * @param userNoManger  城市运营官编码
     * @param auditState    审核状态（10070001：待审核 10070002：已审核 10070003：已驳回）
     * @return
     */
    @Override
    public List<UserApply> findByUserNoManagerAndAuditState(String userNoManger, String auditState) {
        return findByUserNoManagerAndAuditState(userNoManger,0,0,auditState);
    }

    /**
     * 根据用户编码和审核状态查询申请信息
     * @param userNo        用户编码
     * @param auditState    审核状态
     * @return
     */
    @Override
    public UserApply findByUserNoAndAuditState(String userNo, String auditState) {
        return userApplyMapper.findByUserNoAndAuditState(userNo,auditState);
    }
}
