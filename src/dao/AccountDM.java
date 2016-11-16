package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Account;

public class AccountDM implements DBOperator{

	private Connection connect;
	public AccountDM() {
		this.connect=new ConnManager().getConn();
	}
	@Override
	public boolean add(Object object) {
		if(object==null) return false;
		Account account = (Account)object;
		String sql="insert into account (id,name,email,password,type) values(?,?,?,?,?)";
		try {
			PreparedStatement psmt=(PreparedStatement) connect.prepareStatement(sql);
			psmt.setLong(1, account.getId());
			psmt.setString(2, account.getName());
			psmt.setString(3, account.getEmail());
			psmt.setString(4, account.getPassword());
			psmt.setInt(5, account.getType());
			int res = psmt.executeUpdate();
			psmt.close();
			connect.close();
			if(res!=0) return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean delete(Object object) {
		long id=(long)object;
		String sql="delete from account where id=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, id);
			int res=psmt.executeUpdate();
			psmt.close();
			connect.close();
			if(res!=0) return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean update(Object object) {
		if(object==null) return false;
		Account account=(Account)object;
		String sql="update account set name=?,email=?,password=?,type=? where id=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setString(1, account.getName());
			psmt.setString(2, account.getEmail());
			psmt.setString(3, account.getPassword());
			psmt.setInt(4, account.getType());
			psmt.setLong(5, account.getId());
			int res=psmt.executeUpdate();
			psmt.close();
			connect.close();
			if(res!=0) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Object get(Object object) {
		if (object==null) return null;
		String email=(String)object;
		String sql="select * from account where email=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setString(1, email);
			ResultSet rs=psmt.executeQuery();
			if(rs.next()){
				Account account=new Account();
				account.setId(rs.getLong(1));
				account.setName(rs.getString(2));
				account.setEmail(rs.getString(3));
				account.setPassword(rs.getString(4));
				return account;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	public void close(){
		try {
			connect.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
