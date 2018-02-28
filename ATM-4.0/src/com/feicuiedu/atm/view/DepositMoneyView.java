package com.feicuiedu.atm.view;

import java.util.Scanner;

import com.feicuiedu.atm.mod.Record;
import com.feicuiedu.atm.mod.Users;
import com.feicuiedu.atm.service.DepositMoneyService;
import com.feicuiedu.atm.service.SaveRecordService;

/**
 * 存款视图
 * @author Guo
 *
 */
public class DepositMoneyView implements UserBusiness {

	@Override
	public void display(Users user) {
		
		Scanner scanner = new Scanner(System.in);
		ViewUtil vu = new ViewUtil();
		DepositMoneyService dms = new DepositMoneyService();
		SaveRecordService srs = new SaveRecordService();
		Record re = new Record();
		
		re.setId(srs.getMaxID() + 1);
		re.setAccount(user.getCardnumber());
		re.setTarget_account(user.getCardnumber());
		re.setTarget_type(1);
		re.setTarget_date(ViewUtil.getNowTime());
		
		System.out.println("请输入要存入的金额：");
		
		while(true) {
			
			double money = scanner.nextDouble();
			boolean bln = vu.moneyIsInteger(money);
			if (bln) {
				
				boolean bon = vu.disposeMoney(money);
				if (bon) {
					
					//可以进行存款业务
					boolean boon = dms.depositMoney(user, money);
					if (boon) {
						
						re.setTarget_money(String.valueOf(money));
						re.setMoney(user.getMoney() + money);
						srs.saveRecord(re);
						
						System.out.println("存款成功！");
						
						//返回usermunu
						Object obj = ViewUtil.uiProperties("UserBusinessMunu.properties", "UserMunu");
						((UserMunuView) obj).show(user);
						
					}else {
						
						System.out.println("存款失败！");
						
						//返回usermunu
						Object obj = ViewUtil.uiProperties("UserBusinessMunu.properties", "UserMunu");
						((UserMunuView) obj).show(user);
					}
					scanner.close();
					break;
				}else {
					
					System.out.println("只支持0-50000的交易，请从新输入：");
				}
			}else {
				
				System.out.println("只支持100或100的倍数，请从新输入金额");
			}
		}
	}
}
