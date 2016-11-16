package service;

import java.util.Vector;

import dao.LocationDM;
import entity.Location;

public class LocationService {
	public boolean addLocation(Location location){
		return new LocationDM().add(location);
	}
	public boolean delLocation(long id){
		return new LocationDM().delete(id);
	}
	public boolean updateLocation(Location location){
		LocationDM locationDM= new LocationDM();
		boolean res=locationDM.update(location);
		locationDM.close();
		return res;
	}
	public Location getLocation(long id){
		LocationDM locationDM= new LocationDM();
		Location res=(Location) locationDM.get(id);
		locationDM.close();
		return res;
	}
	public String getLocationName(long id){
		LocationDM locationDM= new LocationDM();
		String res=locationDM.getLocName(id);
		locationDM.close();
		return res;
	}
	public Vector<Location> getAllLocations(){
		LocationDM locationDM= new LocationDM();
		Vector<Location> res=locationDM.getAllLocations();
		locationDM.close();
		return res;
	}
	public int getPicnum(long id){
		LocationDM locationDM= new LocationDM();
		int res=locationDM.getPicnum(id);
		locationDM.close();
		return res;
	}
}
