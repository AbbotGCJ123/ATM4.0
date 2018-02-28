package com.feicuiedu.atm.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.feicuiedu.atm.dao.ATMjdbc;
import com.feicuiedu.atm.dao.RecordDao;
import com.feicuiedu.atm.mod.Record;
import com.feicuiedu.atm.mod.Users;

/**
 * 储存记录
 * @author Guo
 *
 */
public class SaveRecordService {

	/**
	 * 插入记录
	 * @param re
	 * @return
	 */
	public boolean saveRecord(Record re) {
		
		RecordDao rd = new RecordDao();
		ATMjdbc aj = new ATMjdbc();
		
		boolean bln = false;
		try {
			
			String sql =rd.save(new Record());
			int count = aj.updateshow(sql,String.valueOf(re.getId()),
										re.getAccount(),
										re.getTarget_account(),
										String.valueOf(re.getTarget_type()),
										String.valueOf(re.getTarget_date()),
										String.valueOf(re.getTarget_money()),
										String.valueOf(re.getMoney()));
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
	 * 获得db_user表中最大id
	 * @return
	 */
	public int getMaxID() {
		
		RecordDao rd = new RecordDao();
		ATMjdbc aj = new ATMjdbc();
		
		String sql = rd.selectMaxID(new Record());
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
	 * 查询所有普通用户
	 * @return
	 */
	public ArrayList<Record> getRecord(String account){
		
		ATMjdbc aj = new ATMjdbc();
		
		String sql = "select * from db_record where account = ?";
		
		ResultSet rs = aj.getResultSet(sql,account);
		ArrayList<Record> recordArr = new ArrayList<>();
		
		try {
			
			while(rs.next()) {
				
				Record re = new Record();
				
				re.setId(rs.getInt("id"));
				re.setAccount(rs.getString("account"));
				re.setTarget_account(rs.getString("target_account"));
				re.setTarget_type(rs.getInt("target_type"));
				re.setTarget_date(rs.getString("target_date"));
				re.setTarget_money(rs.getString("target_money"));
				re.setMoney(rs.getDouble("money"));
				
				recordArr.add(re);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return recordArr;
	}
}
