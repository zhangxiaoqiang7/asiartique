package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import entity.Artifact;
import entity.Artist;
public class ArtifactDM implements DBOperator {
	private Connection connect;
	
	public ArtifactDM() {
		this.connect=new ConnManager().getConn();
	}
	@Override
	public boolean add(Object object) {
		if(object==null) return false;
		Artifact artwork=(Artifact)object;
		try {
			//insert data into artwork table
			String sql="insert into artifact(id,name,description,price,location,picnum,tags,buyinglink,artist,sold) values(?,?,?,?,?,?,?,?,?,?)";
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
			psmt.setString(8, artwork.getLink());
			psmt.setLong(9, artwork.getArtist());
			psmt.setString(10, artwork.getSold());
			int res=psmt.executeUpdate();
			psmt.close();
			if(res!=1) return false;
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
		String sql="delete from artifact where id=?";
		try {
			//delete data in artifact
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
		Artifact artifact=(Artifact)object;
		String sql="update artifact set name=?,description=?,price=?,location=?,picnum=?,tags=?,buyinglink=?,artist=?,sold=? where id=?";
		try {
			//update artwork
			System.out.println(artifact.getId()+" "+artifact.getLink()+" "+artifact.getLocation()+" "+artifact.getDescription()+" "+artifact.getName()+" "+artifact.getPicNum()+" "+artifact.getPrice());
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setString(1, artifact.getName());
			psmt.setString(2, artifact.getDescription());
			psmt.setFloat(3, artifact.getPrice());
			psmt.setLong(4, artifact.getLocation());
			psmt.setInt(5, artifact.getPicNum());
			StringBuilder builder=new StringBuilder();
			for (String tag : artifact.getTags()) {
				builder.append(tag+" ");
			}
			psmt.setString(6, builder.toString());
			psmt.setString(7, artifact.getLink());
			psmt.setLong(8, artifact.getArtist());
			psmt.setString(9, artifact.getSold());
			psmt.setLong(10, artifact.getId());
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
		Artifact artifact=new Artifact();
		long id=(long)object;
		String sql="select * from artifact where id=?";
		try {
			//get data in artwork table.
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, id);
			ResultSet rs=psmt.executeQuery();
			if (rs.next()) {
				artifact.setId(rs.getLong("id"));
				artifact.setName(rs.getString("name"));
				artifact.setDescription(rs.getString("description"));
				artifact.setPrice(rs.getFloat("price"));
				artifact.setLocation(rs.getLong("location"));
				artifact.setPicNum(rs.getInt("picnum"));
				String[] tagsInDB=rs.getString("tags").split(" ");
				if(tagsInDB!=null){
					Vector<String> tagsinObject = new Vector<String>();
					for (String  tag: tagsInDB) {
						tagsinObject.add(tag);
					}
					artifact.setTags(tagsinObject);
				}
				artifact.setLink(rs.getString("buyinglink"));
				artifact.setArtist(rs.getLong("artist"));
				artifact.setSold(rs.getString("sold"));
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
		return artifact;
	}
	
	public Vector<Long> getArtifactIds(long artist){
		String sql="select id from artifact where artist=?";
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
	
	public Vector<Artifact> getAllArtifacts(){
		String sql="select * from artifact";
		Vector<Artifact> artifacts=new Vector<Artifact>();
		try {
			//get data in artwork table.
			PreparedStatement psmt=connect.prepareStatement(sql);
			ResultSet rs=psmt.executeQuery();
			while (rs.next()) {
				Artifact artifact=new Artifact();
				artifact.setId(rs.getLong("id"));
				artifact.setName(rs.getString("name"));
				artifact.setPrice(rs.getFloat("price"));
				artifact.setLocation(rs.getLong("location"));
				artifact.setPicNum(rs.getInt("picnum"));
				String[] tagsInDB=rs.getString("tags").split(" ");
				if(tagsInDB!=null){
					Vector<String> tagsinObject = new Vector<String>();
					for (String  tag: tagsInDB) {
						tagsinObject.add(tag);
					}
					artifact.setTags(tagsinObject);
				}
				artifact.setLink(rs.getString("buyinglink"));
				artifact.setArtist(rs.getLong("artist"));
				artifacts.add(artifact);
			}
			rs.close();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		System.out.println(artifacts.size());
		return artifacts;
	}
	
	public int getPicnum(long id){
		String sql="select picnum from artifact where id=?";
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
