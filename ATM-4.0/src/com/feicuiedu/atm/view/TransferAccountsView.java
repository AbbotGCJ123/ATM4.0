package com.feicuiedu.atm.view;

import java.util.Scanner;

import com.feicuiedu.atm.mod.Record;
import com.feicuiedu.atm.mod.Users;
import com.feicuiedu.atm.service.SaveRecordService;
import com.feicuiedu.atm.service.TransferAccountsService;

/**
 * 转账视图
 * @author Guo
 *
 */
public class TransferAccountsView implements UserBusiness {

	@Override
	public void display(Users user) {
		
		Scanner scanner = new Scanner(System.in);
		TransferAccountsService tas = new TransferAccountsService();
		ViewUtil vu = new ViewUtil();
		SaveRecordService srs = new SaveRecordService();
		Record rea = new Record();
		Record reb = new Record();
		
		rea.setId(srs.getMaxID() + 1);
		reb.setId(srs.getMaxID() + 1);
		
		rea.setAccount(user.getCardnumber());
		
		System.out.println("请输入你要转账的用户账号：");
		while(true) {
			
			String bCardNum = scanner.next();
			Users buser = tas.getUser(bCardNum);
			if (buser.getCardnumber().equals(bCardNum)) {
				
				reb.setAccount(buser.getCardnumber());
				
				rea.setTarget_account(buser.getCardnumber());
				reb.setTarget_account(user.getCardnumber());
				
				rea.setTarget_type(4);
				reb.setTarget_type(3);
				
				rea.setTarget_date(ViewUtil.getNowTime());
				reb.setTarget_date(ViewUtil.getNowTime());
				
				System.out.println("请输入转账金额：");
				while(true) {
					
					double money = scanner.nextDouble();
					if (user.getMoney() >= money) {
						
						boolean bln = vu.moneyIsInteger(money);
						if (bln) {
							
							boolean bon = vu.disposeMoney(money);
							if (bon) {
								
								//可以转账
								boolean boon = tas.transferAccounts(user, buser, money);
								if (boon) {
									
									rea.setTarget_money("-" + money);
									reb.setTarget_money(String.valueOf(money));
									
									rea.setMoney(user.getMoney() - money);
									reb.setMoney(buser.getMoney() + money);
									
									srs.saveRecord(rea);
									srs.saveRecord(reb);
									
									System.out.println("转账成功！");
									
									//返回到usermunu
									Object obj = ViewUtil.uiProperties("UserBusinessMunu.properties", "UserMunu");
									((UserMunuView) obj).show(user);
									
									scanner.close();
									break;
								}else {
									
									System.out.println("转账失败！");
									
									//返回到usermunu
									Object obj = ViewUtil.uiProperties("UserBusinessMunu.properties", "UserMunu");
									((UserMunuView) obj).show(user);
								}
								
							}else {
								System.out.println("只支持0-50000的交易，请从新输入：");
							}
						}else {
							System.out.println("只支持100或100的倍数，请从新输入金额");
						}
					}else {
						System.out.println("账户余额不足，请从新输入金额");
					}
				}
			}else {
				System.out.println("用户不存在！请从新输入转账用户账号：");
			}
		}
	}
}
