package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.vo.SubmitApplyVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:个体/机构申请服务接口
 *
 * @author Mick
 * @CreateDate 2018/6/28 16:05
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@FeignClient(value = "marketing-server-1", path = "/userapply")
public interface UserApplyService {
    @RequestMapping(value = "/submitapply",method = RequestMethod.POST)
    ResponseData submitApply(@RequestBody(required = false) SubmitApplyVo submitApplyVo);

    @RequestMapping(value = "/audituserapply",method = RequestMethod.POST)
    ResponseData updateAuditUserApply(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    @RequestMapping(value = "/findbyapplyno",method = RequestMethod.POST)
    ResponseData findByApplyNo(RequestData<Map<String,String>> requestData);



    /**
     * 计算服务机构星数
     * 机构星级＝(每单获得的分数总和)累计获取分数/单数
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getUserStar",method = RequestMethod.POST)
    ResponseData getUserStar(RequestData<Map<String,String>> requestData);



    /**
     * 查看服务机构详情信息接口
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getOrganizationDetail",method = RequestMethod.POST)
    ResponseData getOrganizationDetail(RequestData<Map<String,String>> requestData);
}
