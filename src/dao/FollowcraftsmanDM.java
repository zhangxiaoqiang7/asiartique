package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import entity.Followartist;

public class FollowcraftsmanDM implements DBOperator{

	private Connection connect;
	
	public FollowcraftsmanDM() {
		this.connect=new ConnManager().getConn();
	}
	@Override
	public boolean add(Object object) {
		if(object==null) return false;
		Followartist follow=(Followartist)object;
		String sql="insert into followcraftsman(account,craftsman) values(?,?)";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, follow.getAccount());
			psmt.setLong(2, follow.getArtist());
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
		Followartist follow=(Followartist)object;
		String sql="delete from followcraftsman where account=? and craftsman=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, follow.getAccount());
			psmt.setLong(2, follow.getArtist());
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
		String sql="select craftsman from followcraftsman where account=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, account);
			ResultSet rs=psmt.executeQuery();
			Vector<Long> artists=new Vector<Long>();
			while(rs.next()){
				artists.add(rs.getLong("craftsman"));
			}
			rs.close();
			psmt.close();
			return artists;
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
