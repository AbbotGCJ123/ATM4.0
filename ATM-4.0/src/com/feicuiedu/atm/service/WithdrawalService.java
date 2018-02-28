package com.feicuiedu.atm.service;

import com.feicuiedu.atm.dao.ATMjdbc;
import com.feicuiedu.atm.mod.Users;

public class WithdrawalService {

	/**
	 * 取款逻辑
	 * @param user
	 * @param newMoney
	 * @return
	 */
	public boolean withdrawal(Users user,double newMoney) {
		
		ATMjdbc aj = new ATMjdbc();
		
		String result = String.valueOf(user.getMoney() - newMoney);
		
		String sql = "UPDATE db_user SET money =? WHERE cardNumber = ?";
		System.out.println(sql);
		
		int count = aj.updateshow(sql,result,user.getCardnumber());
		boolean bln = false;
		
		if (count>0) {
			bln = true;
		}
		
		return bln;
	}
}
