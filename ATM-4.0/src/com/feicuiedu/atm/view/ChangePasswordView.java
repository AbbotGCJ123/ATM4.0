package com.feicuiedu.atm.view;

import java.util.Scanner;

import com.feicuiedu.atm.mod.Users;
import com.feicuiedu.atm.service.UserService;

/**
 * 修改密码视图
 * @author Guo
 *
 */
public class ChangePasswordView implements AdminBusiness {

	@Override
	public void display() {
		
		Scanner scanner = new Scanner(System.in);
		UserService us = new UserService();
		
		System.out.println("请输入要修改的账号：");
		String cardNumber = scanner.next();
		
		System.out.println("请输入旧密码：");
		String password = scanner.next();
		
		Users user = us.getUser(cardNumber, password);
		
		if (user.getCardnumber() != null && user.getCardnumber().equals(cardNumber)) {
			
			System.out.println("请输入新密码：");
			String newPassword1 = scanner.next();
			
			System.out.println("请再次输入新密码：");
			String newPassword2 = scanner.next();
			
			if (newPassword1.equals(newPassword2)) {
				
				//可以修改
				boolean bln = us.updateUserPassword(cardNumber, newPassword2);
				
				if (bln) {
					
					System.out.println("修改成功！");
					
					Object obj = ViewUtil.uiProperties("AdminBusinessMunu.properties", "AdminMunu");
					((AdminMunuView) obj).show();
				}else {
					
					System.out.println("修改失败！数据库错误！");
					
					Object obj = ViewUtil.uiProperties("AdminBusinessMunu.properties", "AdminMunu");
					((AdminMunuView) obj).show();
				}
			}else {
				
				System.out.println("两次密码不同，密码修改失败！");
			}
		}else {
			System.out.println("账号密码不存在！即将返回菜单");
			Object obj = ViewUtil.uiProperties("AdminBusinessMunu.properties", "AdminMunu");
			((AdminMunuView) obj).show();
		}
		scanner.close();
	}
}
