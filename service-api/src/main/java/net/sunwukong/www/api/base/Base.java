package net.sunwukong.www.api.base;

import java.io.Serializable;
import java.util.List;

/**
 * 类说明:请求消息处理类
 *
 * @author ideacoding
 * create 2018-04-03 12:17
 * Email ：ideacoding@163.com
 **/
public interface Base<T> {

	/**
	 * 删除数据
	 *
	 * @param id 编号
	 * @throws Exception 统一异常处理
	 */
	int deleteByPrimaryKey(Serializable id);

	/**
	 * 更新数据
	 *
	 * @param t 待更新对象
	 * @throws Exception 统一异常处理
	 */
	int updateByPrimaryKeySelective(T t);

	/**
	 * 更新数据
	 *
	 * @param t 待更新对象
	 * @throws Exception 统一异常处理
	 */
	int updateByPrimaryKey(T t);

	/**
	 * 查询单个对象
	 *
	 * @param id 编号
	 * @return 返回单个对象数据
	 */
	T selectByPrimaryKey(Serializable id);

	/**
	 * 添加数据
	 *
	 * @param t 待添加对象
	 * @throws Exception 统一异常处理
	 */
	int insert(T t);

	/**
	 * 添加数据
	 *
	 * @param t 待添加对象
	 * @throws Exception 统一异常处理
	 */
	int insertSelective(T t);

	/**
	 * 分页查询数据
	 * @param pageNo	当前页
	 * @param pageSize	页面长度
	 * @return	返回数据列表
	 */
	List<T> queryAllPage(int pageNo, int pageSize);

	/**
	 * 查询所有数据
	 *
	 * @return 返回对象列表
	 */
	List<T> queryAll();
}
