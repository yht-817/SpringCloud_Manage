package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;

/**
 * Created by tungkang on 2018/7/23.
 */
public interface IChoiceInfoSearchKeywordService {

    /**
     * 推荐热门关键词列表
     * @return
     */
    ResponseData getHotList();
}
