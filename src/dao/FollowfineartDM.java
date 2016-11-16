package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import entity.Followartwork;

public class FollowfineartDM implements DBOperator {

	private Connection connect;
	public FollowfineartDM() {
		this.connect=new ConnManager().getConn();
	}
	@Override
	public boolean add(Object object) {
		if(object==null) return false;
		Followartwork follow=(Followartwork)object;
		String sql="insert into followfineart(account,fineart) values(?,?)";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, follow.getAccount());
			psmt.setLong(2, follow.getArtwork());
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
		Followartwork follow=(Followartwork)object;
		String sql="delete from followfineart where account=? and fineart=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, follow.getAccount());
			psmt.setLong(2, follow.getArtwork());
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
		String sql="select fineart from followfineart where account=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, account);
			ResultSet rs=psmt.executeQuery();
			Vector<Long> artworks=new Vector<Long>();
			while(rs.next()){
				artworks.add(rs.getLong("fineart"));
			}
			rs.close();
			psmt.close();
			return artworks;
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
