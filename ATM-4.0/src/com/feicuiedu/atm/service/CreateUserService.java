package com.feicuiedu.atm.service;

import com.feicuiedu.atm.mod.Users;

public class CreateUserService {

	/**
	 * 创建用户
	 * @param user
	 * @return boolean true成功 false失败
	 */
	public boolean createUser(Users user) {
		
		UserService us = new UserService();
		boolean bln = us.insertUser(user);
		return bln;
	}
}
