package com.feicuiedu.atm.mod;

import com.feicuiedu.atm.anno.Column;
import com.feicuiedu.atm.anno.Table;

@Table("db_record")
public class Record {

	//id
	@Column("id")
	private int id;
	
	//原账户
	@Column("account")
	private String account;
	
	//目标账户
	@Column("target_account")
	private String target_account;
	
	//交易类型  1： 2： 3：  4：
	@Column("target_type")
	private int target_type;
	
	//交易时间
	@Column("target_date")
	private String target_date;
	
	//交易金额
	@Column("target_money")
	private String target_money;
	
	//金额
	@Column("money")
	private double money;
	
 	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getTarget_account() {
		return target_account;
	}
	public void setTarget_account(String target_account) {
		this.target_account = target_account;
	}
	
	public int getTarget_type() {
		return target_type;
	}
	public void setTarget_type(int target_type) {
		this.target_type = target_type;
	}
	
	public String getTarget_date() {
		return target_date;
	}
	public void setTarget_date(String target_date) {
		this.target_date = target_date;
	}
	
	public String getTarget_money() {
		return target_money;
	}
	public void setTarget_money(String target_money) {
		this.target_money = target_money;
	}
	
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
 	
}
