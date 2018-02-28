package com.feicuiedu.atm.view;

import java.util.Scanner;

import com.feicuiedu.atm.mod.Users;

/**
 * 普通用户菜单视图
 * @author Guo
 *
 */
public class UserMunuView implements LoginShow {

	@Override
	public void show(Users user) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("1：取款  2：存款  3：转账  4：查看流水记录  5：查看当前用户信息  6：退出登陆");
		System.out.println("请选择：");
		
		int choiceNum = scanner.nextInt();
		
		if (choiceNum == 6) {
			
			Object object = ViewUtil.uiProperties("UserBusinessMunu.properties",String.valueOf(choiceNum));
			((UserLoginView) object).showLogin();
		}else {
			
			Object object = ViewUtil.uiProperties("UserBusinessMunu.properties",String.valueOf(choiceNum));
			((UserBusiness) object).display(user);
		}
		
		
		scanner.close();
		
	}

	@Override
	public void show() {
		
	}
}
