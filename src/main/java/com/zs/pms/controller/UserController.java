package com.zs.pms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.Tuser;
import com.zs.pms.service.DepService;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

//S：源  被转换的类型
//T： target  目标类型  将要转换的类型
//必须先S T 类型修改后才能实现接口里的方法
@Controller
public class UserController{
	@Autowired
	UserService us;
	@Autowired
	DepService ds;
	
	@RequestMapping("/user/list.do")
	public String list(String page,QueryUser query,ModelMap map) {
		
		if(page==null) {
			page="1";
		}
		//返回分页数据
		map.addAttribute("LIST",us.queryByPage(Integer.parseInt(page), query));
		//返回总页数
		map.addAttribute("PAGECONT",us.queryCount(query));
		//返回当前页数
		map.addAttribute("PAGE",page);
		//返回条件
		map.addAttribute("QUERY",query);
		
		return "user/list";
	}
	
	//删除一条
	@RequestMapping("/user/delete.do")
	public String delete(int id) {
		us.deleteByid(id);
		//跳转url
		return "redirect:list.do";
	}
	
	//批量删除
	@RequestMapping("/user/deletes.do")
	public String delete(int [] ids) {
		us.deleteByIds(ids);
		//跳转url
		return "redirect:list.do";
	}

		
	@RequestMapping("/user/toadd.do")
	public String toAdd(ModelMap map) {
		//返回一级部门
		map.addAttribute("DLIST",ds.queryByPid(0));
		return "/user/add";
	}
	
	@RequestMapping("/user/add.do")
	public String add(Tuser user,HttpSession session,ModelMap map) {
		
		Tuser cu = (Tuser) session.getAttribute("CUSER");
		
			try {
				//装载数据
				user.setIsenabled(1);//可用
				user.setCreator(cu.getId());//新增人
				user.setPic("");
				us.insertUser(user);
				//跳转url
				return "redirect:list.do";
			} catch (AppException e) {
				// TODO Auto-generated catch block
				map.addAttribute("MSG",e.getErrMsg());
			}
			//调用方法传递参数
			return this.toAdd(map);
	}
	
	
	@RequestMapping("/user/get.do")
	public String get(int id,ModelMap map) {
		//返回用户信息
		map.addAttribute("USER",us.queryById(id));
		//返回上级部门
		map.addAttribute("DLIST",ds.queryByPid(0));
		return "/user/update";
		
	}
	
}
