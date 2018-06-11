/**
 * @Title Sql.java
 * @Project Test
 * @Package 
 * @author hztianduoduo
 * @Date 2016年10月10日
 * @version V1.0
 *
 * Copyright 2016 Netease, Inc. All rights reserved.
 */

/**
 * sql拼装
 * 
 * @author hztianduoduo
 *
 */
public class Sql{

	private StringBuilder sql = new StringBuilder();

	public Sql() {
	}

	public Sql select(String column) {
		this.sql = this.sql.append("select ").append(column);
		return this;
	}

	public Sql from(String table) {
		this.sql = this.sql.append(" from ").append(table);
		return this;
	}

	public Sql where(String where) {
		this.sql = this.sql.append(" ").append(where);
		return this;
	}

	public Sql between(String between) {
		this.sql = this.sql.append(" ").append(between);
		return this;
	}

	public Sql and(String and) {
		this.sql = this.sql.append(" ").append(and);
		return this;
	}

	public Sql like(String like) {
		this.sql = this.sql.append(" ").append(like);
		return this;
	}

	public Sql or(String or) {
		this.sql = this.sql.append(" ").append(or);
		return this;
	}

	public StringBuilder getSql() {
		return sql;
	}

	public void setSql(StringBuilder sql) {
		this.sql = sql;
	}

	public static void main(String[] args) {
		System.out.println(new Sql().select("*").from("workbill").where("nodeName=?").getSql().toString());
	}
	
}