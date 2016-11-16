package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import entity.Followartwork;

public class FollowartifactDM implements DBOperator {

	private Connection connect;
	public FollowartifactDM() {
		this.connect=new ConnManager().getConn();
	}
	@Override
	public boolean add(Object object) {
		if(object==null) return false;
		Followartwork follow=(Followartwork)object;
		String sql="insert into followartifact(account,artifact) values(?,?)";
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
		String sql="delete from followartifact where account=? and artifact=?";
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
		String sql="select artifact from followartifact where account=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, account);
			ResultSet rs=psmt.executeQuery();
			Vector<Long> artworks=new Vector<Long>();
			while(rs.next()){
				artworks.add(rs.getLong("artifact"));
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
