package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.Tuser;
import com.zs.pms.vo.QueryUser;

public interface UserService {
	//测试方法
	public void hello();
	//根据用户id获得权限
	public List<TPermission> queryByUid(int id);
	
	//根据原有权限整理菜单
	public List<TPermission> genMenu(List<TPermission> pers);
	
	//按条件查询
	public List<Tuser>QueryByCon(QueryUser query);
	
	//分页记录
	public List<Tuser>queryByPage(int page,QueryUser query);
		
	//计算总页数
	public int queryCount(QueryUser query);
	
	//新增  返回主键
	public int insertUser(Tuser user) throws AppException;
	
	//删除一条
	public void deleteByid(int id);
	
	//批量删除
	public void deleteByIds(int [] ids);

	//修改
	public void updateUser(Tuser user);
	
	//根据主键读取
	public Tuser queryById(int id);
}
