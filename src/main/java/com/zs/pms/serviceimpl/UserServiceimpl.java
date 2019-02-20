package com.zs.pms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.Userdao;
import com.zs.pms.exception.AppException;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.Tuser;
import com.zs.pms.service.UserService;
import com.zs.pms.util.Constants;
import com.zs.pms.util.MD5;
import com.zs.pms.vo.QueryUser;
@Service
@Transactional  //需要开启事务的业务对象
public class UserServiceimpl implements UserService {
	
	@Autowired   //自动注入
	Userdao dao;
	
	
	@Override
	public void hello() {
		// TODO Auto-generated method stub
	System.out.println("hello spring");	
	}

	
	@Override
	public List<TPermission> queryByUid(int id) {
		// TODO Auto-generated method stub
		return dao.queryByUid(id);
	}

	
	@Override
	public List<TPermission> genMenu(List<TPermission> pers) {
		// TODO Auto-generated method stub
		//����������
		List<TPermission> list = new ArrayList<>();
		//����Ȩ���б�
		for(TPermission per:pers) {
			//���һ���˵�
			if(per.getLev()==1) {
				//���ظ�һ���˵��µĶ����˵�
				//����Ȩ��
				for(TPermission per2:pers) {
					//����Ȩ�޵ȼ�==һ��Ȩ�޵�id  �ͷŵ�һ��
					if(per2.getPid()==per.getId()) {
						//�����Ȩ�޵ķ���
						per.addChil(per2);
					}
				}
				list.add(per);
			}
		}
		return list;
	}
	
	
	//��ѯ
	@Override
	public List<Tuser> QueryByCon(QueryUser query) {
		// TODO Auto-generated method stub
		return dao.QueryByCon(query);
	}
	
	
	//����ɾ��
	@Override
	public void deleteByIds(int[] ids) {
		// TODO Auto-generated method stub
		dao.deleteByIds(ids);
	}
	
	//�޸�
	@Override
	public void updateUser(Tuser user) {
		// TODO Auto-generated method stub
		dao.updateUser(user);
	}
	
	
	//新增
	@Override
	//该方法只要有异常就回滚事务
	@Transactional(rollbackFor=Exception.class)
	public int insertUser(Tuser user)throws AppException {
		// TODO Auto-generated method stub
		//模拟业务异常
		if("admin".equals(user.getLoginname())) {
			throw new AppException(1003, "登录名不能是admin");
		}	
		MD5 md5 = new MD5();
		user.setPassword(md5.getMD5ofStr(user.getPassword()));;
		//返回主键
		return user.getId();
	}
	
	
	//删除一条
	@Override
	public void deleteByid(int id) {
		// TODO Auto-generated method stub
		dao.deleteByid(id);
		
	}
	
	
	/**
	 * ��ȡ��ҳ��¼
	 * page:��ǰҳ
	 * query:��ѯ����
	 */
	@Override
	public List<Tuser> queryByPage(int page, QueryUser query) {
		// TODO Auto-generated method stub
		/**
		 * ͨ����ǰҳ��������ʼ���ͽ�ֹ��
		 */
		//��ʼ����1��ʼ
		int start = (page-1)*Constants.PAGECONT+1;
		//��ֹ��
		int end =page * Constants.PAGECONT;
		
		query.setStart(start);
		query.setEnd(end);
		
		return dao.queryByPage(query);
		
	}
	
	
	/**
	 * ������ҳ��
	 */
	@Override
	public int queryCount(QueryUser query) {
		// TODO Auto-generated method stub
		//ͨ��������������ҳ��
		int cont = dao.queryCount(query);
		//������
		if(cont%Constants.PAGECONT==0) {
			return cont/Constants.PAGECONT;
		}
		else {
			return cont/Constants.PAGECONT+1;
		}
	}

	//根据主键读取
	@Override
	public Tuser queryById(int id) {
		// TODO Auto-generated method stub
		return dao.queryById(id);
	}

}
