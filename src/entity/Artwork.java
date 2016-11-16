package entity;

import java.util.Vector;

public abstract class Artwork {
	private long id;
	private String name;
	private String description;
	private long artist;
	private String artistname;
	private float price;
	private long location;
	private String locname;
	private int picNum;
	private Vector<String> tags;
	private int type;
	private String sold;
	
	public String getSold() {
		return sold;
	}
	public void setSold(String sold) {
		this.sold = sold;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getArtistname() {
		return artistname;
	}
	public void setArtistname(String artistname) {
		this.artistname = artistname;
	}
	public long getArtist() {
		return artist;
	}
	public void setArtist(long artist) {
		this.artist = artist;
	}
	public String getLocname() {
		return locname;
	}
	public void setLocname(String locname) {
		this.locname = locname;
	}
	public int getPicNum() {
		return picNum;
	}
	public void setPicNum(int picNum) {
		this.picNum = picNum;
	}
	public Vector<String> getTags() {
		return tags;
	}
	public void setTags(Vector<String> tags) {
		this.tags = tags;
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public long getLocation() {
		return location;
	}
	public void setLocation(long location) {
		this.location = location;
	}
	
}
