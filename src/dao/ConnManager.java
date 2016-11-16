package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ConnManager{

	public ConnManager() {
	}
	public Connection getConn(){
		Connection connect=null;
		Context context=null;
		try {
			context = new InitialContext();
			DataSource ds =  (DataSource) context.lookup("java:comp/env/mysqlDataSource");
			if (ds==null) {
				System.err.println("���ݿ�����ʧ��");
			}
			else{
				connect= ds.getConnection();
			}
		} catch (NamingException e) {
			System.out.println("���ݿ�����ʧ��");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}
}
