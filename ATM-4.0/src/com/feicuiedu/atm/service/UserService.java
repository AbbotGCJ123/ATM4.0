package com.feicuiedu.atm.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.feicuiedu.atm.dao.ATMjdbc;
import com.feicuiedu.atm.dao.UserDao;
import com.feicuiedu.atm.mod.Users;
import com.sun.javafx.image.IntPixelGetter;


public class UserService {
	
	UserDao ud = new UserDao();
	ATMjdbc aj = new ATMjdbc();

	/**
	 * 判断这个用户账号是否存在
	 * @param cardNumber 用户账号
	 * @return true存在  false不存在
	 */
	public boolean judgeUser(String cardNumber) {
		
		//String sql = ud.selectCount(new Users(), "cardnumber", cardNumber);
		String sql = "select count(*) from db_user where cardnumber = ?";
		ResultSet rs = aj.getResultSet(sql,cardNumber);
		
		int count = 0;

		try {
			
			while(rs.next()) {
				
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			
			try {
				
				rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		boolean bln = false;
		
		if (count == 1 ) {
			
			bln = true;
		}
		
		return bln;
	}
	/**
	 * 通过账号和密码查询到用户的全部信息
	 * @param cardNumber 账号
	 * @param password 密码
	 * @return Users
	 */
	public Users getUser(String cardNumber,String password) {
		
		//String sql = ud.selectuser(new Users(), "cardnumber", cardNumber, "password", password);
		String sql = "select * from db_user where cardnumber = ? and password = ?";
		
		ResultSet rs = aj.getResultSet(sql,cardNumber,password);
		Users user = new Users();
		
		try {
			
			while(rs.next()) {
				
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setCardid(rs.getString("cardid"));
				user.setSex(rs.getInt("sex"));
				user.setBirthday(rs.getString("birthday"));
				user.setSite(rs.getString("site"));
				user.setRemark(rs.getString("remark"));
				user.setMoney(rs.getShort("money"));
				user.setCardnumber(rs.getString("cardnumber"));
				user.setPassword(rs.getString("password"));
				user.setIdentity(rs.getInt("identity"));
				user.setStatu(rs.getInt("statu"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return user;
	}
	/**
	 * 获得db_user表中最大id
	 * @return
	 */
	public int getMaxID() {
		
		String sql = ud.selectMaxID(new Users());
		ResultSet rs = aj.getResultSet(sql);
		
		int count = 0;
		try {
			
			while(rs.next()) {
				
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 插入一个user对象
	 * @param user
	 * @return true成功 false失败
	 */
	public boolean insertUser(Users user) {
		
		boolean bln = false;
		try {
			
			String sql = ud.save(new Users());
			int count = aj.updateshow(sql,Integer.toString(user.getId()),
									user.getName(),
									user.getCardid(),
									Integer.toString(user.getSex()),
									user.getBirthday(),
									user.getSite(),
									user.getRemark(),
									Double.toString(user.getMoney()),
									user.getCardnumber(),
									user.getPassword(),
									Integer.toString(user.getIdentity()),
									Integer.toString(user.getStatu()));
			
			if (count>0) {
				
				bln = true;
			}
			
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		}
		return bln;
	}
	
	/**
	 * 修改db_user表中的用户状态为销户
	 * @param cardNumber
	 * @return
	 */
	public boolean updateUser(String cardNumber) {
		
		String sql = "UPDATE db_user SET statu = 2 WHERE cardNumber = ?";
		
		int count = aj.updateshow(sql,cardNumber);
		boolean bln = false;
		
		if (count>0) {
			
			bln = true;
		}
		
		return bln;
	}
	
	/**
	 * 修改db_user表中的用户状态为锁定
	 * @param cardNumber
	 * @return
	 */
	public boolean updateLockUser(String cardNumber) {
		
		String sql = "UPDATE db_user SET statu = 3 WHERE cardNumber = ?";
		
		int count = aj.updateshow(sql,cardNumber);
		boolean bln = false;
		
		if (count>0) {
			
			bln = true;
		}
		
		return bln;
	}
	
	/**
	 * 查询所有普通用户
	 * @return
	 */
	public ArrayList<Users> getCommonUser(){
		
		String sql = "select * from db_user where identity = 1";
		
		ResultSet rs = aj.getResultSet(sql);
		ArrayList<Users> userArr = new ArrayList<>();
		
		try {
			
			while(rs.next()) {
				
				Users user = new Users();
				
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setCardid(rs.getString("cardid"));
				user.setSex(rs.getInt("sex"));
				user.setBirthday(rs.getString("birthday"));
				user.setSite(rs.getString("site"));
				user.setRemark(rs.getString("remark"));
				user.setMoney(rs.getShort("money"));
				user.setCardnumber(rs.getString("cardnumber"));
				user.setPassword(rs.getString("password"));
				user.setIdentity(rs.getInt("identity"));
				user.setStatu(rs.getInt("statu"));
				
				userArr.add(user);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return userArr;
	}
	
	/**
	 * 查询所有已经销户的用户
	 * @return
	 */
	public ArrayList<Users> getAccountCancellationUser(){
		
		String sql = "select * from db_user where statu = 2";
		
		ResultSet rs = aj.getResultSet(sql);
		ArrayList<Users> userArr = new ArrayList<>();
		
		try {
			
			while(rs.next()) {
				
				Users user = new Users();
				
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setCardid(rs.getString("cardid"));
				user.setSex(rs.getInt("sex"));
				user.setBirthday(rs.getString("birthday"));
				user.setSite(rs.getString("site"));
				user.setRemark(rs.getString("remark"));
				user.setMoney(rs.getShort("money"));
				user.setCardnumber(rs.getString("cardnumber"));
				user.setPassword(rs.getString("password"));
				user.setIdentity(rs.getInt("identity"));
				user.setStatu(rs.getInt("statu"));
				
				userArr.add(user);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return userArr;
	}
	/**
	 * 查询所有已经锁定的用户
	 * @return
	 */
	public ArrayList<Users> getLockUser(){
		
		String sql = "select * from db_user where statu = 3";
		
		ResultSet rs = aj.getResultSet(sql);
		ArrayList<Users> userArr = new ArrayList<>();
		
		try {
			
			while(rs.next()) {
				
				Users user = new Users();
				
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setCardid(rs.getString("cardid"));
				user.setSex(rs.getInt("sex"));
				user.setBirthday(rs.getString("birthday"));
				user.setSite(rs.getString("site"));
				user.setRemark(rs.getString("remark"));
				user.setMoney(rs.getShort("money"));
				user.setCardnumber(rs.getString("cardnumber"));
				user.setPassword(rs.getString("password"));
				user.setIdentity(rs.getInt("identity"));
				user.setStatu(rs.getInt("statu"));
				
				userArr.add(user);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return userArr;
	}
	
	/**
	 * 修改密码
	 * @param cardNumber
	 * @param password
	 * @return
	 */
	public boolean updateUserPassword(String cardNumber,String password) {
		
		String sql = "UPDATE db_user SET password =? WHERE cardNumber = ?";
		
		int count = aj.updateshow(sql,password,cardNumber);
		boolean bln = false;
		
		if (count>0) {
			
			bln = true;
		}
		
		return bln;
	}
}
