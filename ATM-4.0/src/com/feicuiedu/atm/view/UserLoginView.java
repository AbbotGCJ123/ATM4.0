package com.feicuiedu.atm.view;

import java.util.Scanner;

import com.feicuiedu.atm.mod.Users;
import com.feicuiedu.atm.service.UserService;

/**
 * 登陆视图
 * @author Guo
 *
 */
public class UserLoginView {

	public void showLogin() {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("------------------------------");
		System.out.println("----------XXX ATM系统----------");
		System.out.println("------------------------------");
		
		System.out.println("请选择登陆身份： 1、普通用户  2、管理员");
		
		while(true) {
			
			int identity = scanner.nextInt();
			if (identity>=3 && identity<=0) {
				
				System.out.println("错误！请从新选择:");
			}else {
				
				System.out.println("请输入账号：");
				while(true) {
					
					String accountNum = scanner.next();
					
					//判断这个账号是否在数据库中存在
					UserService us = new UserService();
					
					boolean bln = us.judgeUser(accountNum);
					if (bln) {
						
						System.out.println("请输入密码：");
						while(true) {
							
							String password = scanner.next();
							Users user = us.getUser(accountNum, password);
							if (user.getPassword()!=null && user.getPassword().equals(password)) {
								
								if (user.getStatu() == 1) {
									
									if (user.getIdentity() == identity && identity == 1) {
										
										String choiceNum = Integer.toString(identity);
										
										Object object = ViewUtil.uiProperties("LoginMunu.properties",choiceNum);
										((LoginShow) object).show(user);
									}else if (user.getIdentity() == identity && identity == 2) {
										
										String choiceNum = Integer.toString(identity);
										
										Object object = ViewUtil.uiProperties("LoginMunu.properties",choiceNum);
										((LoginShow) object).show();
									}else {
										System.out.println("账号身份不符合！");
									}
								}else {
									System.out.println("账户信息异常，不能登陆，请从新输入账号：");
								}
								
								break;
								
							}else {
								System.out.println("密码错误！请从新输入----");
							}
						}
						
						break;
						
					}else {
						System.out.println("账号不存在，请重新输入账号：");
					}
				}
				scanner.close();
				break;
			}
		}
	}
}
