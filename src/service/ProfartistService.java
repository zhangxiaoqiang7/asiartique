package service;

import java.util.Vector;

import dao.FineartDM;
import dao.LocationDM;
import dao.ProfartistDM;
import entity.Fineart;
import entity.Profartist;

public class ProfartistService {
	public Profartist getProfartist(long id){
		ProfartistDM profartistDM=new ProfartistDM();
		Profartist profartist=(Profartist) profartistDM.get(id);
		profartistDM.close();
		if(profartist!=null){
			LocationDM locationDM=new LocationDM();
			profartist.setLocname(locationDM.getLocName(profartist.getLocation()));
			locationDM.close();
			FineartDM fineartDM=new FineartDM();
			profartist.setWorks(fineartDM.getFineartIds(id));
			fineartDM.close();
		}
		return profartist;
	}
	public boolean addProfartist(Profartist profartist){
		ProfartistDM profartistDM=new ProfartistDM();
		boolean res= profartistDM.add(profartist);
		profartistDM.close();
		return res;
	}
	public boolean delProfartist(long id){
		ProfartistDM profartistDM=new ProfartistDM();
		boolean res= profartistDM.delete(id);
		profartistDM.close();
		return res;
	}
	public boolean updateProfartist(Profartist profartist){
		ProfartistDM profartistDM=new ProfartistDM();
		boolean res= profartistDM.update(profartist);
		profartistDM.close();
		return res;
	}
	public Vector<Profartist> getAllProfartists(){
		ProfartistDM profartistDM= new ProfartistDM();
		Vector<Profartist> res=profartistDM.getAllProfartists();
		profartistDM.close();
		return res;
	}
	
}
