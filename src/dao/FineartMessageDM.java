package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import entity.Message;

public class FineartMessageDM implements DBOperator{
	private Connection connect;
	public FineartMessageDM() {
		this.connect=new ConnManager().getConn();
	}
	
	@Override
	public boolean add(Object object) {
		if(object==null) return false;
		Message message=(Message)object;
		String sql="insert into fineartmessage(fineart,name,email,message) values(?,?,?,?)";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, message.getArtwork());
			psmt.setString(2, message.getName());
			psmt.setString(3,message.getEmail());
			psmt.setString(4, message.getMessage());
			int res=psmt.executeUpdate();
			if(res==0){
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
		Message message=(Message)object;
		String sql="delete from fineartmessage where fineart=? and email=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, message.getArtwork());
			psmt.setString(2, message.getEmail());
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
	public boolean update(Object object) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	/**
	 * get all contacts interested in the artifact
	 */
	public Object get(Object object) {
		Vector<Message> contacts=new Vector<Message>();
		if(object==null) return contacts;
		long fineart=(long)object;
		String sql="select name,email,message from fineartmessage where fineart=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, fineart);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				Message message=new Message();
				message.setArtwork(fineart);
				message.setName(rs.getString("name"));
				message.setEmail(rs.getString("email"));
				message.setMessage(rs.getString("message"));
				contacts.add(message);
			}
			rs.close();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contacts;
	}
	
	/**
	 * get all contacts interested in the artifact
	 */
	public Vector<Message> getAllMessages() {
		Vector<Message> messages=new Vector<Message>();
		String sql="select * from fineartmessage";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				Message message=new Message();
				message.setArtwork(rs.getLong("fineart"));
				message.setName(rs.getString("name"));
				message.setEmail(rs.getString("email"));
				message.setMessage(rs.getString("message"));
				messages.add(message);
			}
			rs.close();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}
	
	public void close(){
		try {
			this.connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
