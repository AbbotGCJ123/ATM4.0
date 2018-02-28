package com.feicuiedu.atm.dao;


import java.lang.reflect.Field;
import com.feicuiedu.atm.anno.Column;
import com.feicuiedu.atm.anno.Table;

public abstract class AbstractDao<T> {

	/**
	 * 获得完整的插入sql
	 * @param tObj 操作的表
	 * @return 插入的sql语句
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public String save(T tObj) throws IllegalArgumentException, IllegalAccessException {
    	
    	Table table = tObj.getClass().getAnnotation(Table.class);
    	
    	StringBuilder sbSql = new StringBuilder();
    	
    	sbSql.append("insert into ");
    	sbSql.append(table.value());
    	sbSql.append("(");
    	
    	Field[] fields = tObj.getClass().getDeclaredFields();
    	
    	for (Field field : fields) {
    		
    		field.setAccessible(true);
    		Column column = field.getAnnotation(Column.class);
    		sbSql.append(column.value()).append(",");
    	}
    	
    	sbSql.deleteCharAt(sbSql.length()-1);
    	sbSql.append(") values(");
    	
    	for (Field field : fields) {
    		
    		field.setAccessible(true);
    		sbSql.append("?,");
		}
    	
    	sbSql.deleteCharAt(sbSql.length()-1);
    	sbSql.append(") ");
    	
    	return sbSql.toString();
	}
	/**
	 * 
	 * @param tObj 操作的表
	 * @param columnName
	 * @param value
	 * @return 完整的sql
	 */
	public String selectCount(T tObj,String columnName,String value){
		
		Table table = tObj.getClass().getAnnotation(Table.class);
    	
    	StringBuilder sbSql = new StringBuilder();
    	
    	sbSql.append("select count(*) from");
    	sbSql.append(table.value());
    	sbSql.append("where");
    	sbSql.append(columnName);
    	sbSql.append(" = ");
    	sbSql.append(value);
    	
    	return sbSql.toString();
	}
	
	/**
	 * 通过账号密码查询到用户的sql
	 * @param tObj
	 * @param column1
	 * @param value1
	 * @param column2
	 * @param value2
	 * @return 完整sql
	 */
	public String selectuser(T tObj,String column1,String value1,String column2,String value2) {
		
		Table table = tObj.getClass().getAnnotation(Table.class);
    	
    	StringBuilder sbSql = new StringBuilder();
    	
    	sbSql.append("select * from ");
    	sbSql.append(table.value());
    	sbSql.append("where");
    	sbSql.append(column1);
    	sbSql.append(" = ");
    	sbSql.append(value1);
    	sbSql.append(" and ");
    	sbSql.append(column2);
    	sbSql.append(" = ");
    	sbSql.append(value2);

    	return sbSql.toString();
	}
	/**
	 * 获得最大的id
	 * @param tObj
	 * @return 完整的sql语句
	 */
	public String selectMaxID(T tObj) {
		
		Table table = tObj.getClass().getAnnotation(Table.class);
    	
    	StringBuilder sbSql = new StringBuilder();
		
    	sbSql.append("select max(id) from ");
    	sbSql.append(table.value());
    	
		return sbSql.toString();
	}
	
	
}
