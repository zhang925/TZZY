package com.zzy.service;

import java.util.List;

import com.zzy.model.BornInfo;

/**
 * 人员信息列表
 * @author zzy
 *
 */
public interface BornInfoService {
	/**
	 * 增加BornInfo信息(1成功2失败)
	 * @param bornInfo
	 * @return int
	 */
	public int saveBornInfo(BornInfo bornInfo);
	/**
	 * 添加或修改(1成功2失败)
	 * @param bornInfo
	 * @return int
	 */
	public int saveOrUpdateBornInfo(BornInfo bornInfo);
	/**
	 * 删除BornInfo信息(1成功2失败)
	 * @param bornInfo
	 * @return int
	 */
	public int delBornInfo(BornInfo bornInfo);
	/**
	 * 修改BornInfo信息(1成功2失败)
	 * @param bornInfo
	 * @return int
	 */
	public int updateBornInfo(BornInfo bornInfo);
	/**
	 * 根据ID查询单个BornInfo信息
	 * @param id
	 * @return BornInfo
	 */
	public BornInfo getBornInfoModelByID(String id);
	/**
	 *  根据其他条件查询单个BornInfo信息
	 * @param hql
	 * @param param
	 * @return BornInfo
	 */
	public BornInfo getBornInfoModel(String hql,Object[] param);
	/**
	 * 查询全部
	 * @param hql
	 * @param param
	 * @return List<BornInfo>
	 */
	public List<BornInfo> getBornInfoList(String hql,Object[] param);
	/**
	 *  查询全部(分页)
	 * @param hql
	 * @param param
	 * @param page
	 * @param rows
	 * @return List<BornInfo>
	 */
	public List<BornInfo> getBornInfoList(String hql,Object[] param,Integer page,Integer rows);
	/**
	 * 获取总条数
	 * @param hql	select count(*) from BornInfo
	 * @param param
	 * @return int
	 */
	public int getTotalNum(String hql,Object[] param);
}
