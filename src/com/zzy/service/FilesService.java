package com.zzy.service;


import java.util.List;

import com.zzy.model.Files;

/**文件服务的接口*/
public interface FilesService {
	/**根据ID获取文件实体
	public Files getFilesModelByID(Integer id);*/
	/**根据FID获取文件实体*/
	public Files getFilesModelByFID(String fid);
	/**根据UID获取文件实体*/
	public Files getFilesModelByUID(String uid);
	/**获取全部文件List<Files>*/
	public List<Files> getAllFiles();
	/**保存文件信息<Files>1成功2异常*/
	public Integer saveFiles(Files files);
	/**修改文件信息1成功2异常*/
	public Integer upFiles(Files files);
	/**保存或者修改文件信息1成功2异常*/
	public Integer saveorupFiles(Files files);
	/**根据UID删除文件1成功2异常*/
	public Integer delFilesByUID(String uid);
	/**根据条件查询List<Files>不带分页*/
	public List<Files> getFilesByHql(String hql,Object[] param);
	/**根据条件查询获取实体(分页)*/
	public List<Files> getFilesPage(String hql,Object[] param,Integer page01,Integer rows01);
	/**根据查询条件获取全部信息的(条数)
	 * select count(*) from Files
	 * */
	public Integer getTotalNum(String hql,Object[] param);
	
}
