package com.feicuiedu.atm.view;

import java.util.Scanner;

import com.feicuiedu.atm.mod.Record;
import com.feicuiedu.atm.mod.Users;
import com.feicuiedu.atm.service.SaveRecordService;
import com.feicuiedu.atm.service.WithdrawalService;

/**
 * 取款视图
 * @author Guo
 *
 */
public class WithdrawalView implements UserBusiness {

	@Override
	public void display(Users user) {
		
		Scanner scanner  = new Scanner(System.in);
		ViewUtil vu = new ViewUtil();
		WithdrawalService ws = new WithdrawalService();
		SaveRecordService srs = new SaveRecordService();
		Record re = new Record();
		
		re.setId(srs.getMaxID() + 1);
		re.setAccount(user.getCardnumber());
		re.setTarget_account(user.getCardnumber());
		re.setTarget_type(2);
		re.setTarget_date(ViewUtil.getNowTime());
		
		System.out.println("请输入要取款的金额：");
		
		while(true) {
			
			double money = scanner.nextDouble();
			boolean bln = vu.moneyIsInteger(money);
			
			if (user.getMoney()>= money) {
				
				if (bln) {
					
					boolean boon = vu.disposeMoney(money);
					if (boon) {
						
						//可以取款
						boolean bon = ws.withdrawal(user, money);
						if (bon) {
							
							re.setTarget_money("-"+money);
							re.setMoney(user.getMoney() - money);
							srs.saveRecord(re);
							
							System.out.println("取款成功！");
							
							//返回到usermunu页面
							Object obj = ViewUtil.uiProperties("UserBusinessMunu.properties", "UserMunu");
							((UserMunuView) obj).show(user);
						}else {
							
							System.out.println("取款失败！");
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
			}else {
				System.out.println("余额不足，不能取款！");
				
				Object obj = ViewUtil.uiProperties("UserBusinessMunu.properties", "UserMunu");
				((UserMunuView) obj).show(user);
			}
		}
	}
}
