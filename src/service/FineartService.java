package service;

import java.util.Vector;

import dao.FineartDM;
import dao.LocationDM;
import dao.ProfartistDM;
import entity.Fineart;

public class FineartService {
	public boolean addFineart(Fineart fineart){
		FineartDM fineartDM=new FineartDM();
		boolean res=fineartDM.add(fineart);
		fineartDM.close();
		return res;
	}
	public boolean delFineart(long id){
		FineartDM fineartDM=new FineartDM();
		boolean res=fineartDM.delete(id);
		fineartDM.close();
		return res;
	}
	public boolean updateFineart(Fineart fineart){
		FineartDM fineartDM=new FineartDM();
		boolean res=fineartDM.update(fineart);
		fineartDM.close();
		return res;
	}
	public Fineart getFineart(long id){
		FineartDM fineartDM=new FineartDM();
		Fineart fineart=(Fineart)fineartDM.get(id);
		fineartDM.close();
		if(fineart!=null){
			LocationDM locationDM=new LocationDM();
			fineart.setLocname(locationDM.getLocName(fineart.getLocation()));
			locationDM.close();
			ProfartistDM profartistDM=new ProfartistDM();
			fineart.setArtistname(profartistDM.getArtist(fineart.getArtist()));
			fineart.setType(1);
			profartistDM.close();
		}
		return fineart;
	}
	public Vector<Fineart> getAllFinearts(){
		FineartDM fineartDM=new FineartDM();
		Vector<Fineart> res=fineartDM.getAllFinearts();
		fineartDM.close();
		return res;
	}
	
	public Vector<String> getinterestEmails(long fineart){
		FineartDM fineartDM=new FineartDM();
		Vector<String> res=fineartDM.getInteredEmails(fineart);
		fineartDM.close();
		return res;
	}
	
//	public boolean addInterests(long fineart,String email){
//		FineartDM fineartDM= new FineartDM();
//		boolean res=fineartDM.addInterests(fineart, email);
//		fineartDM.close();
//		return res;
//	}
	
	public int getPicnum(long id){
		FineartDM fineartDM= new FineartDM();
		int res=fineartDM.getPicnum(id);
		fineartDM.close();
		return res;
		
	}
}
