package com.feicuiedu.atm.view;

import java.util.Scanner;

import com.feicuiedu.atm.mod.Users;

/**
 * 管理员菜单
 * @author Guo
 *
 */
public class AdminMunuView implements LoginShow{

	@Override
	public void show() {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("1：开户 2：销户 3：检索普通账户 4：检索已销户账户 5：检索锁定账户  6：修改密码  7：退出登陆");
		System.out.println("请选择：");
		
		int number = scanner.nextInt();
		
		if (number == 7) {
			
			Object object = ViewUtil.uiProperties("AdminBusinessMunu.properties",String.valueOf(number));
			((UserLoginView) object).showLogin();
		}else {
			
			Object object = ViewUtil.uiProperties("AdminBusinessMunu.properties",String.valueOf(number));
			((AdminBusiness) object).display();
		}
		scanner.close();
	}

	@Override
	public void show(Users user) {
		
	}

}
