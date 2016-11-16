package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import entity.Craftsman;

public class CraftsmanDM implements DBOperator {

	private Connection connect;
	
	public CraftsmanDM() {
		this.connect=new ConnManager().getConn();
	}
	@Override
	public boolean add(Object object) {
		if(object==null) return false;
		Craftsman craftsman=(Craftsman)object;
		String sql="insert into craftsman(id,name,description,location) values(?,?,?,?)";
		try {
			//insert data into artist table.
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, craftsman.getId());
			System.out.println(craftsman.getName());
			psmt.setString(2, craftsman.getName());
			psmt.setString(3, craftsman.getDescription());
			psmt.setLong(4, craftsman.getLocation());
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
		String sql="delete from craftsman where id=?";
		try {
			//delete data in artist table.
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
		Craftsman craftsman=(Craftsman)object;
		String sql="update craftsman set name=?,description=?,location=? where id=?";
		try {
			//update data into artist
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setString(1, craftsman.getName());
			psmt.setString(2, craftsman.getDescription());
			psmt.setLong(3, craftsman.getLocation());
			psmt.setLong(4, craftsman.getId());
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
		Craftsman craftsman=new Craftsman();
		long id=(long)object;
		String sql="select * from craftsman where id=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, id);
			ResultSet rs=psmt.executeQuery();
			if(rs.next()){
				craftsman.setName(rs.getString("name"));
				craftsman.setDescription(rs.getString("description"));
				craftsman.setLocation(rs.getLong("location"));
				craftsman.setId(rs.getLong("id"));
				rs.close();
				psmt.close();
				return craftsman;
			}
			rs.close();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return craftsman;
	}
	
	public String getArtist(long id){
		String sql="select name from craftsman where id=?";
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
	
	public Vector<Craftsman> getAllCraftsmans(){
		String sql="select * from craftsman";
		Vector<Craftsman> craftsmans=new Vector<Craftsman>();
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				Craftsman craftsman=new Craftsman();
				craftsman.setName(rs.getString("name"));
				craftsman.setLocation(rs.getLong("location"));
				craftsman.setId(rs.getLong("id"));
				craftsmans.add(craftsman);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return craftsmans;
	}
	public void close(){
		try {
			connect.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
