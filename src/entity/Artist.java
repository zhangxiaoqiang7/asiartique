package entity;

import java.util.Vector;

public abstract class Artist {
	private long id;
	private String name;
	private String description;
	private String locname;
	private long location;
	private Vector<Long> works;
	
	public Vector<Long> getWorks() {
		return works;
	}
	public void setWorks(Vector<Long> works) {
		this.works = works;
	}
	public String getLocname() {
		return locname;
	}
	public void setLocname(String locname) {
		this.locname = locname;
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
	/*public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}*/
	public long getLocation() {
		return location;
	}
	public void setLocation(long location) {
		this.location = location;
	}
	
}
