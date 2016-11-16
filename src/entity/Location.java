package entity;

public class Location {
	private long id;
	private String name;
	private String description;
	private int picnum;
	
	public int getPicnum() {
		return picnum;
	}
	public void setPicnum(int picnum) {
		this.picnum = picnum;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
