package service;

import java.util.Vector;

import dao.ArtifactDM;
import dao.CraftsmanDM;
import dao.LocationDM;
import entity.Artifact;

public class ArtifactService {
	public Artifact getArtifact(long id){
		ArtifactDM artifactDM=new ArtifactDM();
		Artifact artifact=(Artifact) artifactDM.get(id);
		artifactDM.close();
		if (artifact!=null) {
			LocationDM locationDM=new LocationDM();
			artifact.setLocname(locationDM.getLocName(artifact.getLocation()));
			locationDM.close();
			CraftsmanDM craftsmanDM=new CraftsmanDM();
			artifact.setArtistname(craftsmanDM.getArtist(artifact.getArtist()));
			artifact.setType(2);
			craftsmanDM.close();
		}
		return artifact;
	}
	
	public boolean delArtifact(long id){
		ArtifactDM artifactDM=new ArtifactDM();
		boolean res=artifactDM.delete(id);
		artifactDM.close();
		return res;
	}
	
	public boolean updateArtifact(Artifact artifact){
		ArtifactDM artifactDM=new ArtifactDM();
		boolean res= artifactDM.update(artifact);
		artifactDM.close();
		return res;
	}
	
	public boolean addArtifact(Artifact artifact){
		ArtifactDM artifactDM=new ArtifactDM();
		boolean res= artifactDM.add(artifact);
		artifactDM.close();
		return res;
	}
	
	public Vector<Artifact> getAllArtifacts(){
		ArtifactDM artifactDM=new ArtifactDM();
		Vector<Artifact> res=artifactDM.getAllArtifacts();
		artifactDM.close();
		return res;
	}
	
	public int getPictnum(long id){
		ArtifactDM artifactDM=new ArtifactDM();
		int res=artifactDM.getPicnum(id);
		artifactDM.close();
		return res;
	}
}
