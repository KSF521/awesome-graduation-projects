package com.sxt.vo;

import java.io.Serializable;

/**
 * 用户信息类
 * @author Administrator
 *
 */
public class Users implements Serializable{
//	private static final long serialVersionUID = 1L;
	private String id;		//用户名（用户标识）
	private String name;	//昵称
	private String password;//密码
	private int adminStatus;  	//是否为管理员状态
	private String img;  		//用户头像
	
	public Users(){}
	
	public Users(String id, String name, String password, int adminStatus,String  img){
		this.id = id;
		this.name = name;
		this.password = password;
		this.adminStatus =adminStatus;
		this.img=img;
	}
	
	public Users(String id, String name, String password, int adminStatus){
		this.id = id;
		this.name = name;
		this.password = password;
		this.adminStatus =adminStatus;
	}
	
	public Users(String id, String password){
		this.id=id;
		this.password=password;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public int getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(int adminStatus) {
		this.adminStatus = adminStatus;
	}
	
	
	

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", password=" + password + ", adminStatus=" + adminStatus
				+ ", img=" + img + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
