package com.feicuiedu.atm.view;

import java.util.Scanner;

import com.feicuiedu.atm.mod.Users;

public class PersonalInformationView implements UserBusiness {

	@Override
	public void display(Users user) {
		
		ViewUtil vu = new ViewUtil();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("个人信息：");
		
		System.out.println("id：" + user.getId());
		System.out.println("姓名：" + user.getName());
		System.out.println("身份证号：" + user.getCardid());
		System.out.println("性别：" + vu.disposeSex(user.getSex()));
		System.out.println("生日：" + user.getBirthday());
		System.out.println("地址：" + user.getSite());
		System.out.println("备注：" + user.getRemark());
		System.out.println("余额：" + user.getMoney());
		System.out.println("卡号：" + user.getCardnumber());
		System.out.println("帐号类型：" + vu.disposeIdentity(user.getIdentity()));
		System.out.println("帐号状态：" + vu.disposeStatus(user.getStatu()));
		System.out.println("--------------------------------");
		System.out.println("1:返回菜单");
		while(true) {
			int chooseNum = scanner.nextInt();
			if (chooseNum == 1) {
				
				Object obj = ViewUtil.uiProperties("UserBusinessMunu.properties", "UserMunu");
				((UserMunuView) obj).show(user);
				
				scanner.close();
				break;
			}else {
				System.out.println("错误！请从新选择");
			}
		}
	}

}
