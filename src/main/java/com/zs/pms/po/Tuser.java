package com.zs.pms.po;

import java.io.Serializable;
import java.util.Date;

import com.zs.pms.util.DateUtil;

public class Tuser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6940772427894641987L;
	
	private int id;
	private String loginname;
	private String password;
	private String sex;
	private Date birthday;
	
	private TDep dept;//关联对象   一对一
	
	private String email;
	private String realname;
	private int creator;
	private String creatime;
	private int updator;
	private String updatime;
	private String pic;
	private int isenabled;
	
	private String isenabledTxt;
	
	private String birthdayTxt;
	
	
	public String getIsenabledTxt() {
		if(isenabled==1) {
			return "可用";
		}
		else {
			return "不可用";
		}
	}
	
	public String getBirthdayTxt() {
		return DateUtil.getStrDate(birthday);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public TDep getDept() {
		return dept;
	}
	public void setDept(TDep dept) {
		this.dept = dept;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public int getCreator() {
		return creator;
	}
	public void setCreator(int creator) {
		this.creator = creator;
	}
	public String getCreatime() {
		return creatime;
	}
	public void setCreatime(String creatime) {
		this.creatime = creatime;
	}
	public int getUpdator() {
		return updator;
	}
	public void setUpdator(int updator) {
		this.updator = updator;
	}
	public String getUpdatime() {
		return updatime;
	}
	public void setUpdatime(String updatime) {
		this.updatime = updatime;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getIsenabled() {
		return isenabled;
	}
	public void setIsenabled(int isenabled) {
		this.isenabled = isenabled;
	}
	
}
