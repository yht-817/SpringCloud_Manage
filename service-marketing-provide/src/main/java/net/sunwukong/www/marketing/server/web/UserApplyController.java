package net.sunwukong.www.marketing.server.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.marketing.vo.SubmitApplyVo;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * 说明:个体/机构申请控制
 *
 * @author Mick
 * @CreateDate 2018/6/28 16:00
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@RestController
@RequestMapping("/userapply")
@Api(tags = "个体/机构-申请控制", description = "")
public class UserApplyController extends BaseController {

    @RequestMapping(value="/submitapply",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "提交申请", httpMethod = "POST", notes = "提交申请",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "phoneNo", value = "手机号",paramType = "query"),
            @ApiImplicitParam(name = "sexNo", value = "性别(男：10010001 女：10010002)",paramType = "query"),
            @ApiImplicitParam(name = "applyType", value = "申请类型(个体首次申请:10300001 个体补充申请:10300002 机构首次申请:10300003 机构补充申请:10300004)",paramType = "query"),
            @ApiImplicitParam(name = "address", value = "机构地址",paramType = "query"),
            @ApiImplicitParam(name = "userNoManger", value = "上级编码",paramType = "query"),
            @ApiImplicitParam(name = "contractNumber", value = "机构电话",paramType = "query"),
            @ApiImplicitParam(name = "userApplyCategories", value = "服务类目列表",paramType = "query"),
            @ApiImplicitParam(name = "userApplyCitys", value = "所在城市列表",paramType = "query"),
            @ApiImplicitParam(name = "userApplyScopes", value = "服务城市列表",paramType = "query"),
            @ApiImplicitParam(name = "userApplyPhotos", value = "用户证件列表",paramType = "query"),
    })
    public ResponseData saveSubmitApply(@RequestBody(required = false) @Valid SubmitApplyVo submitApplyVo, BindingResult result){
        if (result.hasErrors()){
            throw new GrilException(result.getFieldError().getDefaultMessage());
        }
        return iUserApplyService.saveSubmitApply(submitApplyVo);
    }

    @RequestMapping(value="/audituserapply",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "审核服务机构", httpMethod = "POST", notes = "审核服务机构",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyNo", value = "申请编码",paramType = "query"),
            @ApiImplicitParam(name = "auditState", value = "审核状态(已审核:10070002 已驳回:10070003)",paramType = "query"),
    })
    public ResponseData updateAuditUserApply(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        Map<String, String> data = requestData.getData();
        return iUserApplyService.updateAuditUserApply(data.get("applyNo"),data.get("auditState"));
    }

    @RequestMapping(value="/findbyapplyno",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "根据申请编码查询申请信息", httpMethod = "POST", notes = "根据申请编码查询申请信息",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyNo", value = "申请编码",paramType = "query"),
    })
    public ResponseData findByApplyNo(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return iUserApplyService.findByApplyNo(requestData.getData().get("applyNo"));
    }


    /**
     * 计算服务机构星数
     * 机构星级＝(每单获得的分数总和)累计获取分数/单数
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value="/getUserStar",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "服务机构星数", httpMethod = "POST", notes = "服务机构星数",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "服务机构编码",paramType = "query"),
    })
    public ResponseData getUserStar(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String,String> data = requestData.getData();
        return iUserApplyService.getUserStar(data.get("userNo"));
    }


    /**
     * 查看服务机构详情信息接口
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value="/getOrganizationDetail",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "查看服务机构详情信息接口", httpMethod = "POST", notes = "查看服务机构详情信息接口",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyNo", value = "申请编码",paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
    })
    public ResponseData getOrganizationDetail(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return iUserApplyService.getOrganizationDetail(requestData.getData());
    }
}
