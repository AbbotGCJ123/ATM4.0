package com.feicuiedu.atm.mod;

import com.feicuiedu.atm.anno.Column;
import com.feicuiedu.atm.anno.Table;

@Table("db_user")
public class Users {
	
	//编号
	@Column("id")
	private int id;
	
	//姓名
	@Column("name")
	private String name;
	
	//身份证号
	@Column("cardid")
	private String cardid;
	
	//性别
	@Column("sex")
	private int sex;
	
	//出生日期
	@Column("birthday")
	private String birthday;
	
	//地址
	@Column("site")
	private String site;
	
	//备注
	@Column("remark")
	private String remark;
	
	//余额
	@Column("money")
	private double money;
	
	//卡号
	@Column("cardnumber")
	private String cardnumber;
	
	//密码
	@Column("password")
	private String password;
	
	//用户类型
	@Column("identity")
	private int identity;
	
	//用户状态
	@Column("statu")
	private int statu;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIdentity() {
		return identity;
	}

	public void setIdentity(int identity) {
		this.identity = identity;
	}

	public int getStatu() {
		return statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	}
}
