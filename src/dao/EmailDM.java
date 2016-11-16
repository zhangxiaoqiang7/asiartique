package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import entity.Email;

public class EmailDM implements DBOperator {

	private Connection connect;
	
	public EmailDM() {
		this.connect=new ConnManager().getConn();
	}
	@Override
	public boolean add(Object object) {
		if(object==null) return false;
		Email email=(Email)object;
		String sql="insert into email(fineart,email) values(?,?)";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, email.getFineart());
			psmt.setString(2, email.getEmail());
			int res=psmt.executeUpdate();
			psmt.close();
			if(res==0) return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Object object) {
		if(object==null) return false;
		long id=(long)object;
		String sql="delete from email where fineart=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, id);
			int res=psmt.executeUpdate();
			psmt.close();
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
		Email email=(Email)object;
		String sql="update email set email=? where fineart=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setString(1, email.getEmail());
			psmt.setLong(2, email.getFineart());
			psmt.executeUpdate();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Object get(Object object) {
		if(object==null) return null;
		long id=(long)object;
		String sql="select * from email where fineart=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, id);
			ResultSet rs=psmt.executeQuery();
			Vector<String> emails=new Vector<String>();
			while(rs.next()){
				emails.add(rs.getString("email"));
			}
			rs.close();
			psmt.close();
			return emails;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void close(){
		try {
			connect.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
