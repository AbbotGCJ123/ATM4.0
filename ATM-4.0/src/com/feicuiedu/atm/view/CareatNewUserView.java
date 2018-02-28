package com.feicuiedu.atm.view;

import java.util.Scanner;

import com.feicuiedu.atm.mod.Users;
import com.feicuiedu.atm.service.CreateUserService;
import com.feicuiedu.atm.service.UserService;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;

/**
 * 创建新用户视图
 * @author Guo
 *
 */
public class CareatNewUserView implements AdminBusiness {

	@Override
	public void display() {
		
		System.out.println("创建用户：");
		
		Scanner scanner = new Scanner(System.in);
		Users user = new Users();
		UserService us = new UserService();
		
		int id = us.getMaxID();
		user.setId(id+1);
		
		System.out.println("请输入姓名：");
		String name;
		
		while(true) {
			
			name = scanner.next();
			if (name.length()<20 && name.length()>0) {
				
				user.setName(name);
				break;
			}else {
				
				System.out.println("姓名不符合规范，请重新输入：");
			}
		}
		
		System.out.println("请输入身份证号：");
		String cardid;
		
		while(true) {
			
			cardid = scanner.next();
			if (cardid.matches("^[0-9]{18}$")) {
				
				user.setCardid(cardid);
				break;
			}else {
				
				System.out.println("身份证号长度不符合规范，必须是18位，请从新输入：");
			}
		}
		
		System.out.println("请输入性别：");
		String sex;
		
		while(true) {
			
			sex = scanner.next();
			if (sex.equals("男") || sex.equals("女")) {
				
				if (sex.equals("男")) {
					
					user.setSex(1);
				}
				if (sex.equals("女")) {
					
					user.setSex(2);
				}
				break;
			}else {
				
				System.out.println("性别只能是男或女，请从新输入：");
			}
		}
		
		System.out.println("请输入出生日期：");
		String birthday;
		
		while(true) {
			
			birthday = scanner.next();
			if (birthday.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
				
				user.setBirthday(birthday);
				break;
			}else {
				
				System.out.println("出生日期格式不正确，正确格式为：yyyy-mm-dd 请从新输入：");
			}
		}
		
		System.out.println("请输入地址：");
		String site;
		
		while(true) {
			
			site = scanner.next();
			if (site.length()<=50) {
				
				user.setSite(site);
				break;
			}else {
				
				System.out.println("地址长度超出50，请从新输入：");
			}
		}
		
		System.out.println("请输入备注：");
		String remark;
		
		while(true) {
			
			remark = scanner.next();
			if (remark.length()<=200) {
				
				user.setRemark(remark);
				break;
			}else {
				
				System.out.println("备注长度超出200，请从新输入：");
			}
		}
		
		user.setMoney(0);
		
		ViewUtil vu = new ViewUtil();
		
		String dataAccount = vu.accountData(birthday);
		String randomNum = vu.randomNum();
		
		if (sex.equals("男")) {
			
			String cardNumber = "BC1801"+dataAccount + randomNum;
			user.setCardnumber(cardNumber);
		}
		if (sex.equals("女")) {
			
			String cardNumber = "BC1802"+dataAccount + randomNum;
			user.setCardnumber(cardNumber);
		}
		
		
		System.out.println("请输入密码：");
		String password;
		
		while(true) {
			
			String pw = scanner.next();
			
			System.out.println("请再次输入密码：");
			password = scanner.next();
			
			if (password.equals(pw)) {
				
				if (password.matches("^[0-9A-Za-z]{0,6}$")) {
					
					user.setPassword(password);
					break;
				}else {
					
					System.out.println("密码格式错误，正确格式为：6位 不能为空 数字或字母组合 ，请从新输入：");
				}
			}else {
				
				System.out.println("两次密码不同，请从新输入：");
			}
		}
		
		user.setIdentity(1);
		user.setStatu(1);
		
		CreateUserService cs = new CreateUserService();
		boolean bln = cs.createUser(user);
		
		if (bln) {
			
			System.out.println("创建成功！");
			
			Object obj = ViewUtil.uiProperties("AdminBusinessMunu.properties", "AdminMunu");
			((AdminMunuView) obj).show();
		}else {
			
			System.out.println("创建失败！");
			
			Object obj = ViewUtil.uiProperties("AdminBusinessMunu.properties", "AdminMunu");
			((AdminMunuView) obj).show();
		}
		scanner.close();
	}
	
}



