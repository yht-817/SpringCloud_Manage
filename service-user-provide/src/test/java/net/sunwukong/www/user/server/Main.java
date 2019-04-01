package net.sunwukong.www.user.server;

import org.junit.Ignore;

import java.util.Arrays;
import java.util.List;

/**
 * 说明:
 *
 * @author Mick
 * CreateDate 2018/6/15 18:03
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Ignore
public class Main {
    public static void main(String[] args) {
        /*Map<String, String> map = new HashMap<>();
        map.put("userNo",userNo);

        RequestData requestData = new RequestData();
        requestData.setUserNo(userNo);
        requestData.setData(AES128UtilBase64.encryptAES(JSONUtil.map2json(map)));
        HttpRequest httpRequest = HttpRequest.post(ServiceUrl.SEND_COIN).body(JSONUtil.bean2json(requestData)).contentType("application/json");
        ResponseData responseData = com.sdkinfo.www.json.JSONUtil.toBean(httpRequest.execute().body(), ResponseData.class);
        if (responseData.getCode()!=StatusEnum.SUCCESS.getCode()){
            throw new GrilException("发放悟空币失败");
        }*/
        /*Map<String,Object> mapData = new HashMap<>();
        mapData.put("userNoManger","140f4c1aad139fbe69f04fc56906b198");
        mapData.put("userNo","140f4c1aad139fbe69f04fc56906b198");
        mapData.put("contractNumber","18728578786");
        mapData.put("address","电子科大");
        RequestData requestData = new RequestData();
        requestData.setData(AES128UtilBase64.encryptAES(JSONUtil.map2json(mapData)));
        HttpRequest httpRequest = HttpRequest.post(ServiceUrl.ADD_MERCHAN_APPLY).body(JSONUtil.bean2json(requestData)).contentType("application/json");
        System.out.println(httpRequest.execute().body());
        JSONObject jsonObject = JSONObject.fromObject(httpRequest.execute().body());
        if (jsonObject.getInt("code")!= StatusEnum.SUCCESS.getCode()){
            throw new GrilException("服务机构申请表插入数据失败");
        }*/
        /*String s = "{\n" +
                "  \"message\": \"操作成功\",\n" +
                "  \"code\": 200,\n" +
                "  \"data\": {\n" +
                "    \"id\": \"0b6e4f3c21ec5d55127b54a9c4ea4a21\",\n" +
                "    \"userNo\": \"yh201806241935359471\",\n" +
                "    \"userHead\": \"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqqNBIjTYMAesAySq7eg3TEYSSxFXDrOQXFk90EILFOEnQfv5SLhjHKQJjOXFL5zSFqCYcxeSC9g/132\",\n" +
                "    \"userName\": \"昨夜小楼又秋风\",\n" +
                "    \"nikeName\": \"昨秋风\",\n" +
                "    \"sexNo\": \"null\",\n" +
                "    \"phoneNo\": \"null\",\n" +
                "    \"mailboxNo\": \"185476856@qq.com\",\n" +
                "    \"passWord\": null,\n" +
                "    \"regionDate\": 1529840135000,\n" +
                "    \"userType\": \"猴王服务机构\",\n" +
                "    \"lastLoginDate\": 1529995844075,\n" +
                "    \"userState\": \"正常\",\n" +
                "    \"evaluateDividing\": 2075,\n" +
                "    \"userGrade\": \"铜牌服务\",\n" +
                "    \"userGradeChangeDate\": 1529994926000,\n" +
                "    \"otherAppId\": \"ok7rT0_mFwj1TtG53Pax87_aPs4U\",\n" +
                "    \"otherAppName\": \"微信\"\n" +
                "  },\n" +
                "  \"currentPage\": 0,\n" +
                "  \"totalPage\": 0,\n" +
                "  \"totalCount\": 0\n" +
                "}";

        ResponseData<UserInfo> userInfoResponseData = JSON.parseObject(s, new TypeReference<ResponseData<UserInfo>>() {
        });
        System.out.println(userInfoResponseData.getData().toString());*/
        /*BigDecimal bigDecimal = new BigDecimal(20);
        System.out.println(bigDecimal.negate());
        System.out.println(bigDecimal.abs());*/
        /*String snsapi_userinfo = WeChatLoginDao.getCode("http://www.sunwukong.net/redirect_url", "snsapi_userinfo");
        StaticLog.info("snsapi_userinfo:"+snsapi_userinfo);*/

        List<String> strings = Arrays.asList("/admin/dddd", "/ppp");
        if ("/admin".contains("/admina")){
            System.out.println("1");
        }else {
            System.out.println("2");
        }
    }
}
