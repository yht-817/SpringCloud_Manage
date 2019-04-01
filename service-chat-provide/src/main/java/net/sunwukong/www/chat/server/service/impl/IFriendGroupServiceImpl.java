package net.sunwukong.www.chat.server.service.impl;

import com.sdkinfo.www.core.collection.CollUtil;
import com.sdkinfo.www.core.util.StrUtil;
import com.sdkinfo.www.im.easemob.dao.IMChatGroup;
import net.sunwukong.www.api.base.BaseResp;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.DefualtEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.chat.bean.FriendGroup;
import net.sunwukong.www.chat.bean.FriendGroupUser;
import net.sunwukong.www.chat.server.dao.FriendGroupMapper;
import net.sunwukong.www.chat.server.service.IFriendGroupService;
import net.sunwukong.www.chat.server.web.BaseController;
import net.sunwukong.www.chat.vo.GroupUsers;
import net.sunwukong.www.chat.vo.QueryGroup;
import net.sunwukong.www.chat.vo.QueryGroupDetails;
import net.sunwukong.www.chat.vo.QueryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 说明:好友群信息服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/7/9 15:03
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IFriendGroupServiceImpl extends BaseController implements IFriendGroupService {
    @Autowired
    FriendGroupMapper friendGroupMapper;

    /**
     * 创建群聊
     * @param data  群聊对象
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addFriendGroup(FriendGroup data) {
        ResponseData responseData = new ResponseData();
        //创建环信群聊
        String groupId = IMChatGroup.createGroup(data.getGroupName(), data.getGroupNode(), data.getUserNo());
        if (StrUtil.isEmpty(groupId)){
            throw new GrilException("创建环信群聊失败");
        }
        data.setId(DataBaseTool.createId());
        data.setCreateDate(new Date());
        data.setGroupState(SysCode.CODE_10160001.getCode());
        data.setGroupNo(groupId);
        if (StrUtil.isEmpty(data.getGroupHead())){
            data.setGroupHead(DefualtEnum.CODE_10010001.getUrl());
        }
        int i = friendGroupMapper.insert(data);
        if (i<=0){
            throw new GrilException("创建群聊失败");
        }
        //插入一条关系表
        FriendGroupUser friendGroupUser = new FriendGroupUser();
        friendGroupUser.setGroupNo(data.getGroupNo());
        friendGroupUser.setUserNo(data.getUserNo());
        friendGroupUser.setGroupUserState(SysCode.CODE_10170001.getCode());
        iFriendGroupUserService.addFriendGroupUser(friendGroupUser);
        responseData.setData(friendGroupUser.getGroupNo());
        return responseData;
    }

    /**
     * 解散群聊
     * @param groupNo   群编码
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData writeDissolve(String groupNo) {
        FriendGroup byGroupNo = friendGroupMapper.findByGroupNo(groupNo);
        byGroupNo.setGroupState(SysCode.CODE_10160002.getCode());
        int i = friendGroupMapper.updateByPrimaryKey(byGroupNo);
        if (i<=0){
            throw new GrilException("解散群失败");
        }
        boolean delGroup = IMChatGroup.delGroup(groupNo);
        if (!delGroup){
            throw new GrilException("删除环信群失败");
        }
        return BaseResp.getInstance();
    }

    /**
     * 退出群聊
     * @param userNo    用户编码
     * @param groupNo   群聊编码
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData writeExit(String userNo, String groupNo) {
        return iFriendGroupUserService.writeExit(userNo,groupNo);
    }

    /**
     * 邀请加入群聊
     *
     * @param userNo         邀请方的用户编码
     * @param userNos        用户编码列表
     * @param groupNo        群聊编码
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData writeInviteJoin(String userNo,List<Map<String,String>> userNos, String groupNo) {
        userNos.forEach(s -> {
            FriendGroupUser friendGroupUser = new FriendGroupUser();
            friendGroupUser.setInviteNo(DataBaseTool.createNo("invite"));
            friendGroupUser.setUserNo(s.get("userNo"));
            friendGroupUser.setGroupNo(groupNo);
            friendGroupUser.setGroupUserState(SysCode.CODE_10170003.getCode());
            friendGroupUser.setFriendUserNo(userNo);
            iFriendGroupUserService.addFriendGroupUser(friendGroupUser);
        });
        return BaseResp.getInstance();
    }

    /**
     * 申请加入群聊
     * @param userNo            用户编码
     * @param groupNo           群聊编码
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData writeApplyJoin(String userNo, String groupNo) {
        FriendGroupUser friendGroupUser = new FriendGroupUser();
        friendGroupUser.setInviteNo(DataBaseTool.createNo("apply"));
        friendGroupUser.setUserNo(userNo);
        friendGroupUser.setGroupNo(groupNo);
        friendGroupUser.setGroupUserState(SysCode.CODE_10170005.getCode());
        iFriendGroupUserService.addFriendGroupUser(friendGroupUser);
        return BaseResp.getInstance();
    }

    /**
     * 获取群用户list
     * @param groupNo   群编码
     * @param content   检索内容
     * @param start     当前页
     * @param size      页面长度
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData queryPageGroupUsers(String groupNo,String content,int start,int size) {
        return iFriendGroupUserService.queryPageGroupUsers(groupNo,content,start,size);
    }

    /**
     * 修改群信息
     * @param groupNo   群编码
     * @param groupName 群名称
     * @param groupNode 群公告
     * @param groupHead 群头像
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData updateFriendGroup(String groupNo, String groupName,String groupNode,String groupHead) {
        FriendGroup byGroupNo = findByGroupNo(groupNo);
        if (StrUtil.isNotEmpty(groupName)){
            byGroupNo.setGroupName(groupName);
        }
        if (StrUtil.isNotEmpty(groupNode)){
            byGroupNo.setGroupNode(groupNode);
        }
        if (StrUtil.isNotEmpty(groupHead)){
            byGroupNo.setGroupHead(groupHead);
        }
        friendGroupMapper.updateByPrimaryKey(byGroupNo);
        //更新环信群信息
        IMChatGroup.modifyChatGroup(byGroupNo.getGroupNo(),byGroupNo.getGroupName(),byGroupNo.getGroupNode());
        return BaseResp.getInstance();
    }

    /**
     * 设置群是否收费
     * @param groupNo       群编码
     * @param ifPayGroup    是否收费
     * @param payAmount     收费金额
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData updateGroupSetting(String groupNo, String ifPayGroup, String payAmount) {
        FriendGroup byGroupNo = findByGroupNo(groupNo);
        byGroupNo.setIfPayGroup(ifPayGroup);
        byGroupNo.setPayAmount(new BigDecimal(payAmount));
        friendGroupMapper.updateByPrimaryKey(byGroupNo);
        return BaseResp.getInstance();
    }

    /**
     * 根据群编码查询群信息
     * @param groupNo   群编码
     * @return
     */
    @Override
    public FriendGroup findByGroupNo(String groupNo) {
        FriendGroup byGroupNo = friendGroupMapper.findByGroupNo(groupNo);
        if (byGroupNo==null){
            throw new GrilException("未查询当前群的信息");
        }
        return byGroupNo;
    }

    /**
     * 获取用户群列表
     *
     * @param userNo 用户编码
     * @return
     */
    @Override
    public ResponseData queryPageUserGroups(String userNo,int start,int size) {
        return iFriendGroupUserService.queryPageUserGroups(userNo,start,size);
    }

    /**
     * 获取标签列表
     * @return
     */
    @Override
    public ResponseData queryLabels() {
        return iGroupLabelService.queryLabels();
    }

    /**
     * 检索群聊
     * @param userNo    用户编码
     * @param content   检索内容
     * @param start     开始位置
     * @param size      检索长度
     * @return
     */
    @Override
    public ResponseData queryPageSearchGroup(String userNo,String content,int start,int size) {
        ResponseData responseData = new ResponseData();
        List<QueryGroup> maps = friendGroupMapper.queryPageSearchGroup(content, SysCode.CODE_10160001.getCode(), start, size);
        maps.forEach(map -> {
            //查询群成员
            ResponseData<List<GroupUsers>> queryPageGroupUsers = iFriendGroupUserService.queryPageGroupUsers(map.getGroupNo(),null, 0, 0);
            map.setGroupPersonNo(queryPageGroupUsers.getData().size());
            //查询是否已经加入该群
            map.setIsokJoin(iFriendGroupUserService.findByGroupNoAndUserNoExists(map.getGroupNo(),userNo,SysCode.CODE_10170001.getCode()));
            if (map.isIsokJoin()){
                //查询当前用户是否是群主
                map.setIsokOwner(iFriendGroupService.queryJudgeIsokOwner(map.getGroupNo(),userNo));
            }
        });
        responseData.setData(maps);
        return responseData;
    }

    /**
     * 开始检索
     * @param content   检索内容
     * @param type      检索类型
     * @param start     开始位置
     * @param size      页面长度
     * @return
     */
    @Override
    public ResponseData queryPageSearch(String userNo,String content, String type,int start,int size) {
        if ("0".equals(type)) {
            //检索用户
            return iUserInfoService.queryPageSearchUser(userNo,content, start, size);
        } else if ("1".equals(type)) {
            //检索群聊
            return iFriendGroupService.queryPageSearchGroup(userNo,content, start, size);
        } else {
            throw new GrilException("请输入正确的检索类型");
        }
    }

    /**
     * 获取群详情
     * @param groupNo   群编码
     * @param userNo    用户编码
     * @return
     */
    @Override
    public ResponseData queryGroupDetails(String groupNo, String userNo) {
        ResponseData responseData = new ResponseData();
        QueryGroupDetails details = friendGroupMapper.queryGroupDetails(groupNo);
        //判断当前浏览用户是否加入该群
        boolean exists = iFriendGroupUserService.findByGroupNoAndUserNoExists(groupNo, userNo, SysCode.CODE_10170001.getCode());
        details.setIsokJoin(exists);
        if (exists){
            boolean isokOwner = queryJudgeIsokOwner(groupNo, userNo);
            details.setIsokOwner(isokOwner);
        }else {
            details.setIsokOwner(false);
        }
        //获取群人数
        details.setGroupCount(iFriendGroupUserService.readCountByGroupNo(groupNo));
        //获取群详情 群主信息+7位成员信息
        ResponseData<List<QueryUser>> groupUsers = iFriendGroupUserService.queryPageGroupUsers(groupNo,null, 0, 7);
        details.setQueryUsers(groupUsers.getData());
        responseData.setData(details);
        return responseData;
    }

    /**
     * 判断是否是群主
     * @param groupNo   群编码
     * @param userNo    用户编码
     * @return
     */
    @Override
    public boolean queryJudgeIsokOwner(String groupNo, String userNo) {
        //判断当前浏览用户是否是群主
        FriendGroup byGroupNoAndUserNo = friendGroupMapper.findByGroupNoAndUserNo(groupNo, userNo);
        if (byGroupNoAndUserNo!=null){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 分页检索邀请群成员列表
     * @param userNo    用户编码
     * @param groupNo   群编码
     * @param content   检索内容
     * @param start     开始位置
     * @param size      页面长度
     * @return
     */
    @Override
    public ResponseData queryPageSearchInviteUser(String userNo, String groupNo, String content, int start, int size) {
        //获取我的好友列表
        ResponseData<List<Map<String,String>>> queryPageFriends = iFriendInviteService.queryPageSearchFriendUser(userNo,content, 0, 0);
        List<Map<String,String>> queryFriendUsers = queryPageFriends.getData();

        //查询群信息
        FriendGroup byGroupNo = findByGroupNo(groupNo);

        //获取群成员列表
        ResponseData<List<GroupUsers>> queryPageGroupUsers = iFriendGroupUserService.queryPageGroupUsers(groupNo,null, 0, 0);
        List<GroupUsers> quseryGroupUsers = queryPageGroupUsers.getData();
        List<String> userNos = new ArrayList<>();
        //获取已经在群里面的好友编码
        quseryGroupUsers.forEach(queryGroupUser -> {
            queryFriendUsers.forEach(queryFriendUser -> {
                //如果搜索到群主信息也过滤掉
                if (byGroupNo.getUserNo().equals(queryFriendUser.get("userNo"))){
                    userNos.add(queryGroupUser.getUserNo());
                }
                if (queryFriendUser.get("userNo").equals(queryGroupUser.getUserNo())){
                    userNos.add(queryFriendUser.get("userNo"));
                }
            });
        });

        //开始检索我好友列表的用户
        ResponseData<List<Map<String,Object>>> responseData = iFriendInviteService.queryPageSearchFriendUser(userNo, content, start, size);
        List<Map<String,Object>> queryUsers = responseData.getData();
        queryUsers.forEach(queryUser -> {
            //如果搜索到自己的也过滤掉()
            /*if (userNo.equals(queryUser.getUserNo())){
                userNos.add(queryUser.getUserNo());
            }*/
            queryUser.put("isokInvite",false);
            userNos.forEach(un->{
                if (un.equals(queryUser.get("userNo"))){
                    queryUser.put("isokInvite",true);
                }
            });
        });
        CollUtil.sort(queryUsers, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return String.valueOf(o1.get("isokInvite")).compareTo(String.valueOf(o2.get("isokInvite")));//顺序
            }
        });
        responseData.setData(queryUsers);
        return responseData;
    }

    /**
     * 删除群成员
     *
     * @param userNo  群主编码
     * @param groupNo 群聊编码
     * @param userNos 用户编码对象
     * @return
     */
    @Override
    public ResponseData writeDelGroupUser(String userNo, String groupNo, List<Map<String, String>> userNos) {
        List<String> list = new ArrayList<>();
        userNos.forEach(map -> {
            list.add(map.get("userNo"));
        });
        iFriendGroupUserService.writeDelGroupUser(userNo, groupNo, list);
        return BaseResp.getInstance();
    }
}
