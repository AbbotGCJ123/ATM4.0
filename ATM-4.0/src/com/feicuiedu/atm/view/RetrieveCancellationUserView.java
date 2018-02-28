package com.feicuiedu.atm.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.feicuiedu.atm.mod.Users;
import com.feicuiedu.atm.service.UserService;

/**
 * 检索已经销户的用户视图
 * @author Guo
 *
 */
public class RetrieveCancellationUserView implements AdminBusiness{
	
	/*public static void main(String[] args) {
		new RetrieveAccountCancellationUserView().display();
	}*/

	@Override
	public void display() {
		
		System.out.println("所有的已经销户的用户：");
		
		UserService us = new UserService();
		ViewUtil vu = new ViewUtil();
		Scanner scanner = new Scanner(System.in);
		
		ArrayList<Users> userArr = us.getAccountCancellationUser();
		for (Users users : userArr) {
			
			System.out.println("id：" + users.getId());
			System.out.println("姓名：" + users.getName());
			System.out.println("身份证号：" + users.getCardid());
			System.out.println("性别：" + vu.disposeSex(users.getSex()));
			System.out.println("生日：" + users.getBirthday());
			System.out.println("地址：" + users.getSite());
			System.out.println("备注：" + users.getRemark());
			System.out.println("余额：" + users.getMoney());
			System.out.println("卡号：" + users.getCardnumber());
			System.out.println("帐号类型：" + vu.disposeIdentity(users.getIdentity()));
			System.out.println("帐号状态：" + vu.disposeStatus(users.getStatu()));
			System.out.println();
			System.out.println();
		}
		System.out.println("1:返回菜单");
		while(true) {
			
			int choosenum = scanner.nextInt();
			if (choosenum == 1) {
				
				Object obj = ViewUtil.uiProperties("AdminBusinessMunu.properties", "AdminMunu");
				((AdminMunuView) obj).show();
				
				scanner.close();
				break;
			}else {
				
				System.out.println("输入错误，请从新输入：");
			}
		}
	}
}
