package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import entity.Event;

public class EventDM implements DBOperator {
	
	private Connection connect;
	
	public EventDM() {
		this.connect=new ConnManager().getConn();
	}

	@Override
	public boolean add(Object object) {
		if(object==null) return false;
		Event event=(Event)object;
		String sql="insert into event(id,name,date,description,picnum) values(?,?,?,?,?)";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, event.getId());
			psmt.setString(2, event.getName());
			psmt.setString(3, event.getDate());
			psmt.setString(4, event.getDescription());
			psmt.setInt(5, event.getPicnum());
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
		String sql="delete from event where id=?";
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
		Event event=(Event) object;
		String sql="update event set name=?,date=?,description=?,picnum=? where id=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setString(1, event.getName());
			psmt.setString(2, event.getDate());
			psmt.setString(3, event.getDescription());
			psmt.setInt(4, event.getPicnum());
			psmt.setLong(5, event.getId());
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
		String sql="select * from event where id=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, id);
			ResultSet rs=psmt.executeQuery();
			if(rs.next()){
				Event event=new Event();
				event.setId(id);
				event.setName(rs.getString("name"));
				event.setDate(rs.getString("date"));
				event.setDescription(rs.getString("description"));
				event.setPicnum(rs.getInt("picnum"));
				rs.close();
				psmt.close();
				return event;
			}
			rs.close();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public Vector<Event> getAllEvents(){
		String sql="select id,name,date,picnum from event";
		Vector<Event> events=new Vector<Event>();
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				Event event=new Event();
				event.setId(rs.getLong("id"));
				event.setName(rs.getString("name"));
				event.setDate(rs.getString("date"));
				event.setPicnum(rs.getInt("picnum"));
				events.add(event);
			}
			rs.close();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return events;
	}
	public void close(){
		try {
			connect.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
