package com.feicuiedu.atm.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.feicuiedu.atm.mod.Users;
import com.feicuiedu.atm.service.UserService;

/**
 * 检索已锁定的用户视图
 * @author Guo
 *
 */
public class RetrieveLockUserView implements AdminBusiness {
	
	@Override
	public void display() {
		
		System.out.println("所有的已经锁定的用户：");
		
		UserService us = new UserService();
		ViewUtil vu = new ViewUtil();
		Scanner scanner = new Scanner(System.in);
		
		ArrayList<Users> userArr = us.getLockUser();
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
		System.out.println("1：解锁用户   2：返回");
		while(true) {
			
			int chooseNum = scanner.nextInt();
			if (chooseNum == 1) {
				
				System.out.println("请输入要解锁的用户账号：");
				String cardnumber = scanner.next();
				
				boolean bln = us.updateLockUser(cardnumber);
				if (bln) {
					
					System.out.println("成功解锁！");
					
					//返回adminmunu！
					Object obj = ViewUtil.uiProperties("AdminBusinessMunu.properties", "AdminMunu");
					((AdminMunuView) obj).show();
					break;
				}else {
					
					System.out.println("解锁失败！请从新输入：");
				}
			}else if (chooseNum == 2) {
				
				Object obj = ViewUtil.uiProperties("AdminBusinessMunu.properties", "AdminMunu");
				((AdminMunuView) obj).show();
				break;
			} else {
				
				System.out.println("选择错误，请从新选择：");
			}
			scanner.close();
		}
	}
}
