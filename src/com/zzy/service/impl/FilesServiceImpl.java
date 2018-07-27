package com.zzy.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzy.dao.BaseDao;
import com.zzy.model.Files;
import com.zzy.service.FilesService;
 /**文件服务的接口的实现*/
@Transactional
@Service()
public class FilesServiceImpl implements FilesService {
	
	private BaseDao<Files> basedao;
	
	public BaseDao<Files> getBasedao() {
		return basedao;
	}
	
	public void setBasedao(BaseDao<Files> basedao) {
		this.basedao = basedao;
	}

	/*public Files getFilesModelByID(Integer id) {
		String hql="from Files where id=?";
		Object []param=new Object[]{id};
		Files o = basedao.get(hql, param);
		return o;
	}*/
	public Files getFilesModelByUID(String uid) {
		String hql="from Files where uid=?";
		Object []param=new Object[]{uid};
		Files o = basedao.get(hql, param);
		return o;
	}
	public Files getFilesModelByFID(String fid) {
		String hql="from Files where fid=?";
		Object []param=new Object[]{fid};
		Files o = basedao.get(hql, param);
		return o;
	}
	
	public List<Files> getAllFiles() {
		return basedao.find("from Files");
	}

	public Integer delFilesByUID(String uid) {
		Integer i = 1;
		Files o = new Files();
		o=this.getFilesModelByUID(uid);
		try {
			basedao.delete(o);
		} catch (Exception e) {
			System.out.println("保存失败！");
			i=2;
		}
		return i;
	}

	public Integer saveFiles(Files Files) {
		Integer i=1;
		try {
			basedao.save(Files);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！保存失败！");
		}
		return i;
	}

	public Integer upFiles(Files Files) {
		Integer i = 1;
		try {
			basedao.update( Files);
		} catch (Exception e) {
			System.out.println("保存失败！");
			i=2;
		}
		return i;
	}

	public List<Files> getFilesByHql(String hql, Object[] param) {
		return basedao.find(hql, param);
	}

	public List<Files> getFilesPage(String hql, Object[] param, Integer page01,
			Integer rows01) {
		return basedao.find(hql, param, page01, rows01);
	}

	public Integer getTotalNum(String hql, Object[] param) {
		long k = basedao.count(hql,param);
		return (int) k;
	}

	public Integer saveorupFiles(Files Files) {
		Integer i=1;
		try {
			basedao.saveOrUpdate(Files);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！保存失败！");
		}
		return i;
	}

	
}
