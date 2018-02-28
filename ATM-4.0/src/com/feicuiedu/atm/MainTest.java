package com.feicuiedu.atm;

import com.feicuiedu.atm.dao.UserDao;
import com.feicuiedu.atm.mod.Users;

public class MainTest {

	public static void main(String[] args) {
		/*String name = "呵呵";
		System.out.println( name.matches("^{1,20}$"));*/
		UserDao ud = new UserDao();
		
		try {
			
			String sql =  ud.save(new Users());
			System.out.println(sql);
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
