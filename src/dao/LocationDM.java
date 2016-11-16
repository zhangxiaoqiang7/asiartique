package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import entity.Location;

public class LocationDM implements DBOperator {

	private Connection connect;
	
	public LocationDM() {
		this.connect=new ConnManager().getConn();
	}
	@Override
	public boolean add(Object object) {
		if(object==null) return false;
		Location location=(Location)object;
		String sql="insert into location(id,name,description,picnum) values(?,?,?,?)";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, location.getId());
			psmt.setString(2, location.getName());
			psmt.setString(3, location.getDescription());
			psmt.setInt(4, location.getPicnum());
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
		String sql="delete from location where id=?";
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
		Location location=(Location) object;
		String sql="update location set name=?,description=?,picnum=? where id=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setString(1, location.getName());
			psmt.setString(2, location.getDescription());
			psmt.setInt(3, location.getPicnum());
			psmt.setLong(4, location.getId());
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
		String sql="select * from location where id=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, id);
			ResultSet rs=psmt.executeQuery();
			if(rs.next()){
				Location location=new Location();
				location.setId(id);
				location.setName(rs.getString("name"));
				location.setDescription(rs.getString("description"));
				location.setPicnum(rs.getInt("picnum"));
				rs.close();
				psmt.close();
				return location;
			}
			rs.close();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public Vector<Location> getAllLocations(){
		String sql="select * from location";
		Vector<Location> locations=new Vector<Location>();
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				Location location=new Location();
				location.setId(rs.getLong("id"));
				location.setName(rs.getString("name"));
				location.setPicnum(rs.getInt("picnum"));
				locations.add(location);
			}
			rs.close();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return locations;
		}
		return locations;
	}
	
	public String getLocName(long id){
		String sql="select name from location where id=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, id);
			ResultSet rs=psmt.executeQuery();
			if (rs.next()) {
				String name=rs.getString("name");
				rs.close();
				psmt.close();
				return name;
			}
			rs.close();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public int getPicnum(long id){
		String sql="select picnum from location where id=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, id);
			ResultSet rs=psmt.executeQuery();
			int picnum=rs.getInt("picnum");
			if(rs.next()) {
				rs.close();
				psmt.close();
				return picnum;
			}
			rs.close();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}
	public void close(){
		try {
			connect.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
