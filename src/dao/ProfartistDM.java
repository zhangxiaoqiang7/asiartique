package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import entity.Profartist;

public class ProfartistDM implements DBOperator {

	private Connection connect;
	
	public ProfartistDM() {
		this.connect=new ConnManager().getConn();
	}
	@Override
	public boolean add(Object object) {
		if(object==null) return false;
		Profartist profartist=(Profartist)object;
		String sql="insert into profartist(id,name,description,location) values(?,?,?,?)";
		try {
			//insert data into artist table.
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, profartist.getId());
			System.err.println(profartist.getName());
			psmt.setString(2, profartist.getName());
			psmt.setString(3, profartist.getDescription());
			psmt.setLong(4, profartist.getLocation());
			int res=psmt.executeUpdate();
			if(res==0) {
				psmt.close();
				return false;
			}
			psmt.close();
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
		String sql="delete from profartist where id=?";
		try {
			//delete data in artist table.
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, id);
			int res=psmt.executeUpdate();
			if(res!=0) {
				psmt.close();
				return true;
			}
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean update(Object object) {
		if(object==null) return false;
		Profartist profartist=(Profartist)object;
		String sql="update artist set name=?,description=?,location=? where id=?";
		try {
			//update data into artist
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setString(1, profartist.getName());
			System.err.println(profartist.getDescription());
			psmt.setString(2, profartist.getDescription());
			psmt.setLong(3, profartist.getLocation());
			psmt.setLong(4, profartist.getId());
			psmt.executeUpdate();
			psmt.close();
			//update data into profartist
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
		String sql="select * from profartist where id=?";
		try {
			Profartist profartist=new Profartist();
			System.out.println("id: "+id);
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, id);
			ResultSet rs=psmt.executeQuery();
			if(rs.next()){
				profartist.setName(rs.getString("name"));
				profartist.setDescription(rs.getString("description"));
				profartist.setLocation(rs.getLong("location"));
				profartist.setId(rs.getLong("id"));
				rs.close();
				psmt.close();
				return profartist;
			}
			rs.close();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	public String getArtist(long id){
		String sql="select name from profartist where id=?";
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
	
	public Vector<Profartist> getAllProfartists(){
		String sql="select * from profartist";
		Vector<Profartist> profartists=new Vector<Profartist>();
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				Profartist profartist=new Profartist();
				profartist.setName(rs.getString("name"));
				profartist.setLocation(rs.getLong("location"));
				profartist.setId(rs.getLong("id"));
				profartists.add(profartist);
			}
			rs.close();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return profartists;
	}
	public void close(){
		try {
			connect.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
