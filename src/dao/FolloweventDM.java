package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import entity.Followevent;


public class FolloweventDM implements DBOperator {

	private Connection connect;
	
	public FolloweventDM() {
		this.connect=new ConnManager().getConn();
	}
	@Override
	public boolean add(Object object) {
		if(object==null) return false;
		Followevent follow=(Followevent)object;
		String sql="insert into followevent(account,event) values(?,?)";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, follow.getAccount());
			psmt.setLong(2, follow.getEvent());
			int res=psmt.executeUpdate();
			psmt.close();
			if(res==0) return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean delete(Object object) {
		if(object==null) return false;
		Followevent follow=(Followevent)object;
		String sql="delete from followevent where account=? and event=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, follow.getAccount());
			psmt.setLong(2, follow.getEvent());
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
		return false;
	}

	@Override
	public Object get(Object object) {
		if(object==null) return null;
		long account=(long)object;
		String sql="select event from followevent where account=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, account);
			ResultSet rs=psmt.executeQuery();
			Vector<Long> events=new Vector<Long>();
			while(rs.next()){
				events.add(rs.getLong("event"));
			}
			rs.close();
			psmt.close();
			return events;
		} catch (SQLException e) {
			e.printStackTrace();
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
