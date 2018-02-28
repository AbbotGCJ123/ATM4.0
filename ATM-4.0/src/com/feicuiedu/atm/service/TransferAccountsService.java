package com.feicuiedu.atm.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.feicuiedu.atm.dao.ATMjdbc;
import com.feicuiedu.atm.mod.Users;

public class TransferAccountsService {
	
	ATMjdbc aj = new ATMjdbc();

	public Users getUser(String bCardNumber) {
		
		String sql = "select * from db_user where cardnumber = ?";
		
		ResultSet rs = aj.getResultSet(sql,bCardNumber);
		Users user = new Users();
		
		try {
			
			while(rs.next()) {
				
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setCardid(rs.getString("cardid"));
				user.setSex(rs.getInt("sex"));
				user.setBirthday(rs.getString("birthday"));
				user.setSite(rs.getString("site"));
				user.setRemark(rs.getString("remark"));
				user.setMoney(rs.getShort("money"));
				user.setCardnumber(rs.getString("cardnumber"));
				user.setPassword(rs.getString("password"));
				user.setIdentity(rs.getInt("identity"));
				user.setStatu(rs.getInt("statu"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return user;
	}
	/**
	 * 转账逻辑
	 * @param auser
	 * @param buser
	 * @return
	 */
	public boolean transferAccounts(Users auser,Users buser,double money) {
		
		ATMjdbc aj = new ATMjdbc();
		
		String aresult = String.valueOf(auser.getMoney() - money);
		String bresult = String.valueOf(buser.getMoney() + money);
		
		String asql = "UPDATE db_user SET money = ? WHERE cardNumber = ?";
		String bsql = "UPDATE db_user SET money = ? WHERE cardNumber = ?";
		
		int acount = aj.updateshow(asql,aresult,auser.getCardnumber());
		int bcount = aj.updateshow(bsql,bresult,buser.getCardnumber());
		
		boolean bln = false;
		
		if (acount>0 && bcount>0) {
			
			bln = true;
		}
		return bln;
	}
}
