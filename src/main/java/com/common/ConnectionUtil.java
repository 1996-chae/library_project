package com.common;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionUtil {

	private static final String RESORUCE = "java:/comp/env";
	private static final String RESORUCE_NAME = "jdbc/oracle";
	
	public static DataSource getDatasource() {
		DataSource dataSource = null; 
		try {
			Context ctx = new InitialContext();
			Context envCtx = (Context) ctx.lookup(RESORUCE);
			dataSource = (DataSource) envCtx.lookup(RESORUCE_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return dataSource;
	}
}
