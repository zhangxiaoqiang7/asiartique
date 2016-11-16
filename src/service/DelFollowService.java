package service;

import dao.FollowartifactDM;
import dao.FollowcraftsmanDM;
import dao.FolloweventDM;
import dao.FollowfineartDM;
import dao.FollowprofartistDM;
import entity.Followartist;
import entity.Followartwork;
import entity.Followevent;

public class DelFollowService {
	public boolean delFollowArtifact(Followartwork followartwork){
		FollowartifactDM followartifactDM=new FollowartifactDM();
		boolean res=followartifactDM.delete(followartwork);
		followartifactDM.close();
		return res;
	}
	public boolean delFollowFineart(Followartwork followartwork){
		FollowfineartDM followfineartDM= new FollowfineartDM();
		boolean res=followfineartDM.delete(followartwork);
		followfineartDM.close();
		return res;
	}
	public boolean delFollowProfartist(Followartist followartist){
		FollowprofartistDM followprofartistDM= new FollowprofartistDM();
		boolean res=followprofartistDM.delete(followartist);
		followprofartistDM.close();
		return res;
	}
	public boolean delFollowCraftsman(Followartist followartist){
		FollowcraftsmanDM followcraftsmanDM= new FollowcraftsmanDM();
		boolean res=followcraftsmanDM.delete(followartist);
		followcraftsmanDM.close();
		return res;
	}
	public boolean delFollowEvent(Followevent followevent){
		FolloweventDM followeventDM= new FolloweventDM();
		boolean res=followeventDM.delete(followevent);
		followeventDM.close();
		return res;
		
	}
}
