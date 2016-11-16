 package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import entity.Fineart;

public class FineartDM implements DBOperator {

	private Connection connect;
	
	public FineartDM() {
		this.connect=new ConnManager().getConn();
	}
	@Override
	public boolean add(Object object) {
		if(object==null) return false;
		Fineart artwork=(Fineart)object;
		try {
			//add data into artwork table
			String sql="insert into fineart(id,name,description,price,location,picnum,tags,width,height,kinds,color,date,artist,sold) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, artwork.getId());
			psmt.setString(2, artwork.getName());
			psmt.setString(3, artwork.getDescription());
			psmt.setFloat(4, artwork.getPrice());
			psmt.setLong(5, artwork.getLocation());
			psmt.setInt(6, artwork.getPicNum());
			StringBuilder tags=new StringBuilder();
			for (String tag : artwork.getTags()) {
				tags.append(tag+" ");
			}
			psmt.setString(7, tags.toString());
			psmt.setInt(8, artwork.getWidth());
			psmt.setInt(9, artwork.getHeight());
			psmt.setString(10, artwork.getKinds());
			psmt.setInt(11, artwork.getColor());
			psmt.setString(12, artwork.getDate());
			psmt.setLong(13, artwork.getArtist());
			psmt.setString(14, artwork.getSold());
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
		String sql="delete from fineart where id=?";
		try {
			//É¾³ýartworkÖÐÊý¾Ý
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
		Fineart fineart=(Fineart)object;
		String sql="update fineart set name=?,description=?,price=?,location=?,picnum=?,tags=?,width=?,height=?,kinds=?,color=?,date=?,artist=?,sold=? where id=?";
		try {
			//update artwork
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setString(1, fineart.getName());
			psmt.setString(2, fineart.getDescription());
			psmt.setFloat(3, fineart.getPrice());
			psmt.setLong(4, fineart.getLocation());
			psmt.setInt(5, fineart.getPicNum());
			StringBuilder builder=new StringBuilder();
			for (String tag : fineart.getTags()) {
				builder.append(tag+" ");
			}
			psmt.setString(6, builder.toString());
			psmt.setInt(7, fineart.getWidth());
			psmt.setInt(8, fineart.getHeight());
			psmt.setString(9, fineart.getKinds());
			psmt.setInt(10, fineart.getColor());
			psmt.setString(11, fineart.getDate());
			psmt.setLong(12, fineart.getArtist());
			psmt.setString(13, fineart.getSold());
			psmt.setLong(14, fineart.getId());
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
		Fineart fineart=new Fineart();
		long id=(long)object;
		String sql="select * from fineart where id=?";
		try {
			//get data in artwork table.
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, id);
			ResultSet rs=psmt.executeQuery();
			if (rs.next()) {
				fineart.setId(rs.getLong("id"));
				fineart.setName(rs.getString("name"));
				fineart.setDescription(rs.getString("description"));
				fineart.setPrice(rs.getFloat("price"));
				fineart.setLocation(rs.getLong("location"));
				fineart.setPicNum(rs.getInt("picnum"));
				String[] tagsInDB=rs.getString("tags").split(" ");
				if(tagsInDB!=null){
					Vector<String> tagsinObject = new Vector<String>();
					for (String  tag: tagsInDB) {
						tagsinObject.add(tag);
					}
					fineart.setTags(tagsinObject);
				}
				fineart.setWidth(rs.getInt("width"));
				fineart.setHeight(rs.getInt("height"));
				fineart.setKinds(rs.getString("kinds"));
				fineart.setColor(rs.getInt("color"));
				fineart.setDate(rs.getString("date"));
				fineart.setArtist(rs.getLong("artist"));
				fineart.setSold(rs.getString("sold"));
				rs.close();
				psmt.close();
			}
			else {
				rs.close();
				psmt.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return fineart;
	}
	
	public Vector<Long> getFineartIds(long artist){
		String sql="select id from fineart where artist=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, artist);
			ResultSet rs=psmt.executeQuery();
			Vector<Long> ids=new Vector<Long>();
			while (rs.next()) {
				ids.add(rs.getLong("id"));
			}
			rs.close();
			psmt.close();
			return ids;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Vector<Fineart> getAllFinearts(){
		String sql="select * from fineart";
		Vector<Fineart> finearts=new Vector<Fineart>();
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			ResultSet rs=psmt.executeQuery();
			while (rs.next()) {
				Fineart fineart=new Fineart();
				fineart.setId(rs.getLong("id"));
				fineart.setName(rs.getString("name"));
				//System.out.println(fineart.getName());
				fineart.setPrice(rs.getFloat("price"));
				fineart.setLocation(rs.getLong("location"));
				fineart.setPicNum(rs.getInt("picnum"));
				String[] tagsInDB=rs.getString("tags").split(" ");
				if(tagsInDB!=null){
					Vector<String> tagsinObject = new Vector<String>();
					for (String  tag: tagsInDB) {
						tagsinObject.add(tag);
					}
					fineart.setTags(tagsinObject);
				}
				fineart.setWidth(rs.getInt("width"));
				fineart.setHeight(rs.getInt("height"));
				fineart.setKinds(rs.getString("kinds"));
				fineart.setColor(rs.getInt("color"));
				fineart.setDate(rs.getString("date"));
				fineart.setArtist(rs.getLong("artist"));
				finearts.add(fineart);
			}
			rs.close();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		//System.out.println(finearts.size());
		return finearts;
	}
	
	public Vector<String> getInteredEmails(Long fineart){
		String sql="select * from interestfineart where fineart=?";
		Vector<String> emails=new Vector<String>();
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, fineart);
			ResultSet rs=psmt.executeQuery();
			while (rs.next()) {
				emails.add(rs.getString("email"));
			}
			rs.close();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return emails;
	}
	
	public int getPicnum(long id){
		String sql="select picnum from fineart where id=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, id);
			ResultSet rs=psmt.executeQuery();
			if(rs.next()) {
				int picnum=rs.getInt("picnum");
				rs.close();
				psmt.close();
				return picnum;
			}
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
