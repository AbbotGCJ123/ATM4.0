package com.feicuiedu.atm.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.feicuiedu.atm.mod.Record;
import com.feicuiedu.atm.mod.Users;
import com.feicuiedu.atm.service.SaveRecordService;

public class RecordView implements UserBusiness{

	@Override
	public void display(Users user) {
		
		SaveRecordService srs = new SaveRecordService();
		ViewUtil vu = new ViewUtil();
		Scanner scanner = new Scanner(System.in);
		
		ArrayList<Record> recordArr = srs.getRecord(user.getCardnumber());
		
		for (Record record : recordArr) {
			
			System.out.println("id：" + record.getId());
			System.out.println("原账号：" + record.getAccount());
			System.out.println("目标账号：" + record.getTarget_account() );
			System.out.println("交易类型：" + vu.disposeRecordType(record.getTarget_type()));
			System.out.println("交易时间：" + record.getTarget_date());
			System.out.println("交易金额：" + record.getTarget_money());
			System.out.println("交易后金额：" + record.getMoney());
			System.out.println();
			System.out.println();
		}
		
		System.out.println("1:返回菜单");
		while(true) {
			
			int choosenum =scanner.nextInt();
			if (choosenum == 1) {
				
				Object obj = ViewUtil.uiProperties("UserBusinessMunu.properties", "UserMunu");
				((UserMunuView) obj).show(user);
				
				scanner.close();
				break;
			}else {
				
				System.out.println("输入错误，请从新输入：");
			}
		}
	}
}
