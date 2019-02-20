import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.po.TDep;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.Tuser;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationcontext.xml")

public class Text {
	
	@Autowired    //�Զ�ע��
	
	UserService us;
	
	
	public void textlogin() {
		List<TPermission>list1=us.queryByUid(3084);
		for(TPermission per:list1) {
			System.out.println(per.getPname());
		}
		System.out.println("-----------�����---------"+"\n");
		
		for(TPermission per1:us.genMenu(list1)){
			//һ��Ȩ��
			System.out.println(per1.getPname());
			for(TPermission per2:per1.getChildren()) {
				System.out.println("------"+per2.getPname());
			}
		}
	}
	
	
	//��ѯ�Ĳ��Է���
	public void testQuery() {
		//������ѯ����
		QueryUser query = new QueryUser();
		query.setLoginname("");
		query.setPassword("qwe123");
		System.out.println(us.QueryByCon(query).size());
		
	}
	

	
	//ɾ��һ���Ĳ��Է���
	public void testDel() {
		us.deleteByid(89);
	}
	
	
	//����ɾ���Ĳ��Է���
	public void testDelete() {
		int [] ids = {87,88};
		us.deleteByIds(ids);
	}
	
	
	
	//�޸ĵĲ��Է���
	public void testUpdate() {
		
		Tuser user=new Tuser();
		user.setId(87);
		user.setLoginname("asdsa123");
		user.setPassword("2123123");
		user.setSex("Ů");
		user.setBirthday(new Date());
		user.setEmail("33333@qq.com");
		TDep dep=new TDep();
		dep.setId(2);
		user.setDept(dep);
		user.setRealname("��������");
		user.setUpdator(3001);
		user.setIsenabled(1);
		user.setPic("login");
		
		us.updateUser(user);
		
	}
	
	
	@Test
	//�������Է���
	public void testInsert() {
		
		Tuser user=new Tuser();
		TDep dep=new TDep();
		user.setDept(dep);
		user.setEmail("666666@qq.com");
		user.setLoginname("jang125");
		user.setPassword("qweasd");
		user.setSex("Ů");
		user.setBirthday(new Date());
		user.setRealname("��������");
		user.setCreator(8043);
		user.setIsenabled(1);
		user.setPic("login434.jpg");
		
	}
	
	
	//���Է�ҳ��ѯ�Ĳ��Է���
	public void testCont() {
		//������ѯ����
		QueryUser query = new QueryUser();
		for(Tuser user:us.queryByPage(1, query)) {
			System.out.println(user.getId()+" "+user.getLoginname());
		}
		System.out.println("��"+us.queryCount(query)+"ҳ");
	}
	
	
}
