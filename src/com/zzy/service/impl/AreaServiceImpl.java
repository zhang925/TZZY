package com.zzy.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zzy.dao.BaseDao;
import com.zzy.model.district.Area;
import com.zzy.service.AreaService;

/**地区 接口的实现*/
@Transactional
public class AreaServiceImpl implements AreaService{
	private BaseDao<Area> basedao;
	
	public BaseDao<Area> getBasedao() {
		return basedao;
	}
	
	public void setBasedao(BaseDao<Area> basedao) {
		this.basedao = basedao;
	}
	/**根据实体ID删除Area*/
	public Integer delAreaByID(Integer id) {
		Integer i=1;
		Area o = new Area();
		try {
			o=getAreaModel(id);
			basedao.delete(o);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！删除失败！");
		}
		return i;
	}
	/**根据ID获取Area*/
	public Area getAreaModel(Integer id) {
		String hql="from Area where id=?";
		Object []param=new Object[]{id};
		Area o = basedao.get(hql, param);
		return o;
	}

	/**根据实体保存Area*/
	public Integer saveArea(Area Area) {
		Integer i=1;
		try {
			basedao.save(Area);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！保存失败！");
		}
		return i;
	}
	/**根据实体更新Area*/
	public Integer updateArea(Area Area) {
		Integer i=1;
		try {
			basedao.update(Area);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	
	public Integer saveorupArea(Area Area) {
		Integer i=1;
		try {
			basedao.saveOrUpdate(Area);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	/**根据hql获取List<Area>*/
	public List<Area> getAllArea(String hql, Object[] param) {
		return basedao.find(hql,param);
	}
	/**根据hql和条件分页获取List<Area>*/
	public List<Area> getAreaPage(String hql,Object[] param, Integer page, Integer rows) {
		return basedao.find(hql, param, page, rows);
	}
	/**根据查询条件获取全部信息的条数*/
	public Integer getTotalNum(String hql, Object[] param) {
		long k = basedao.count(hql,param);
		return (int) k;
	}
	/**根据查询条件获取全部信息的页数*/
	public Integer getAreaTotalPage(Integer num,Integer rows) {
		int totalpage=1;
		if(num >= rows){
			if(num % rows == 0){
				totalpage = num / rows;
			}else{
				totalpage = (num / rows)+1;
			}
		}
		return totalpage;
	}

}
