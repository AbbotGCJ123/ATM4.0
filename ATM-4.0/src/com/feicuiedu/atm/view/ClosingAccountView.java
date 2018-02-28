package com.feicuiedu.atm.view;

import java.util.Scanner;

import com.feicuiedu.atm.mod.Users;
import com.feicuiedu.atm.service.UserService;

/**
 * 销户视图
 * @author Guo
 *
 */
public class ClosingAccountView implements AdminBusiness {

	@Override
	public void display() {
		
		Scanner scanner = new Scanner(System.in);
		UserService us = new UserService();
		
		System.out.println("请输入要销户的账号：");
		while(true) {
			String cardNumber = scanner.next();
			
			System.out.println("请输入密码：");
			String password = scanner.next();
			
			Users user = us.getUser(cardNumber, password);
			
			if (user.getCardnumber() != null && user.getPassword().equals(password)) {
				
				String statu = "";
				if (user.getStatu() == 1) {
					
					statu = "正常";
				}else if (user.getStatu() == 2) {
					
					statu = "已销户";
				}else if (user.getStatu() == 3) {
					
					statu = "已锁定";
				}
				System.out.println("当前账户状态：" + statu);
				System.out.println("确定要销户吗？   1：确定    2：取消");
				
				int chooseNum = scanner.nextInt();
				
				if (chooseNum == 1) {
					
					//进行销户
					boolean bln = us.updateUser(cardNumber);
					if (bln) {
						
						System.out.println("销户成功！");
						
						//销户成功后需返回到adminMunu
						Object obj = ViewUtil.uiProperties("AdminBusinessMunu.properties", "AdminMunu");
						((AdminMunuView) obj).show();
						
						scanner.close();
						break;
					}else {
						
						System.out.println("销户失败！");
						
						Object obj = ViewUtil.uiProperties("AdminBusinessMunu.properties", "AdminMunu");
						((AdminMunuView) obj).show();
						break;
					}
				}else if (chooseNum == 2) {
					
					//取消返回到adminMunu
					Object obj = ViewUtil.uiProperties("AdminBusinessMunu.properties", "AdminMunu");
					((AdminMunuView) obj).show();
				}
			}else {
				System.out.println("账号或密码错误！请从新输入账号：");
			}
		}
	}
}
