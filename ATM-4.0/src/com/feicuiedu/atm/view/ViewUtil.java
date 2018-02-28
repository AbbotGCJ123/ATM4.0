package com.feicuiedu.atm.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * 试图帮助类
 * @author Guo
 *
 */
public class ViewUtil {

	/**
	 * 读取并映射类
	 * @param fileName 要获取的properties文件名
	 * @return object类型 一般都是获取到文件中类所在的位置，返回一个对象
	 */
	public static Object uiProperties(String fileName,String choiceNum) {
		
		Properties properties = new Properties();
		
		Object object = null;
		
		try {
			
			properties.load(new FileInputStream(new File("document" + File.separator + fileName)));
			String packageName = properties.getProperty(choiceNum);

			object = Class.forName(packageName).newInstance();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return object;
	}
	
	/**
	 * 将生日转为账号的辅助方法
	 * @param birthday生日
	 * @return 转换后的字符串
	 */
	public String accountData(String birthday) {
		
		String[] arr = birthday.split("-");
		String accountdata = arr[0]+arr[1]+arr[2];
		return accountdata;
	}
	

	/**
	 * 随机四个数，用于拼接账号
	 * @return 四个数组成的字符串
	 */
	public String  randomNum() {

		int num = 0;
        String[] arr = new String[4];
        for (int i = 0; i <arr.length; i++) {
        	
        	num = new Random().nextInt(10);
        	String strnum = Integer.toString(num);
        	arr[i] = strnum;
		}
		return arr[0] + arr[1] + arr[2] + arr[3];
	}
	
	/**
	 * 性别处理
	 * @param sex
	 * @return
	 */
	public String disposeSex(int sex) {
		
		String gender = "";
		
		if (sex == 1) {
			
			gender = "男";
		}
		if (sex == 2) {
			
			gender = "女";
		}
		return gender;
	}
	
	/**
	 * 帐号状态处理
	 * @param statu
	 * @return
	 */
	public String disposeStatus(int statu) {
		
		String status = "";
		
		if (statu == 1) {
			
			status = "正常";
		}else if (statu == 2) {
			
			status = "已销户";
		}else if (statu == 3) {
			
			status = "已锁定";
		} 
		return status;
	}
	
	/**
	 * 帐号类型处理
	 * @param iden
	 * @return
	 */
	public String disposeIdentity(int iden) {
		
		String identity = "";
		
		if (iden == 1) {
			
			identity = "普通用户";
		}
		if (iden == 2) {
			
			identity = "管理员";
		}
		return identity;
	}
	
	/**
	 * 处理金额只能是大于0的数，只支持最大50000的金额
	 * @param money
	 * @return
	 */
	public boolean disposeMoney(double money) {
		
		boolean bln = false;
		
		if (money>0 && money<=50000) {
			
			bln = true;
		}
		return bln;
	}
	
	/**
	 * 判断金额是不是100的倍数
	 * @param money
	 * @return
	 */
	public boolean moneyIsInteger(double money) {
		
		boolean bln = false;
		
		if (money%100 == 0) {
			
			bln = true;
		}
		return bln;
	}
	
	/**
	 * 获取当前 系统时间  
	 * @return 
	 */
	public static String getNowTime() {
			
			Date date = new Date();
			
			SimpleDateFormat sdfaccountNum = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			String straccountNum = sdfaccountNum.format(date);
			
			return straccountNum;  
	}
	
	/**
	 * 业务类型处理
	 * @param recordType
	 * @return
	 */
	public String disposeRecordType(int recordType) {
		
		String identity = "";
		
		if (recordType == 1) {
			
			identity = "存款";
		}else if (recordType == 2) {
			
			identity = "取款";
		}else if (recordType == 3) {
			
			identity = "转账-收入";
		}else if (recordType == 4) {
			
			identity = "转账-支出";
		}
		return identity;
	}
}
