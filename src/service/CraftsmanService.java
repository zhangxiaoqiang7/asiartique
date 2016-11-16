package service;

import java.util.Vector;

import dao.ArtifactDM;
import dao.CraftsmanDM;
import dao.LocationDM;
import entity.Craftsman;

public class CraftsmanService {
	public boolean addCraftsman(Craftsman craftsman){
		CraftsmanDM craftsmanDM= new CraftsmanDM();
		boolean res=craftsmanDM.add(craftsman);
		craftsmanDM.close();
		return res;
	}
	public boolean delCraftsman(long id){
		CraftsmanDM craftsmanDM= new CraftsmanDM();
		boolean res=craftsmanDM.delete(id);
		craftsmanDM.close();
		return res;
	}
	public boolean updateCraftsman(Craftsman craftsman){
		CraftsmanDM craftsmanDM= new CraftsmanDM();
		boolean res=craftsmanDM.update(craftsman);
		craftsmanDM.close();
		return res;
	}
	public Craftsman getCraftsman(long id){
		CraftsmanDM craftsmanDM=new CraftsmanDM();
		Craftsman craftsman=(Craftsman) craftsmanDM.get(id);
		craftsmanDM.close();
		if(craftsman!=null){
			LocationDM locationDM=new LocationDM();
			craftsman.setLocname(locationDM.getLocName(craftsman.getLocation()));
			locationDM.close();
			ArtifactDM artifactDM=new ArtifactDM();
			craftsman.setWorks(artifactDM.getArtifactIds(id));
			artifactDM.close();
		}
		return craftsman;
	}
	public Vector<Craftsman> getAllCraftsmans(){
		CraftsmanDM craftsmanDM=new CraftsmanDM();
		Vector<Craftsman> res=craftsmanDM.getAllCraftsmans();
		craftsmanDM.close();
		return res;
	}
}
