package service;

import dao.FollowartifactDM;
import dao.FollowcraftsmanDM;
import dao.FolloweventDM;
import dao.FollowfineartDM;
import dao.FollowprofartistDM;
import entity.Followartist;
import entity.Followartwork;
import entity.Followevent;

public class FollowService {
	public boolean followartifact(Followartwork followartwork){
		FollowartifactDM followartifactDM= new FollowartifactDM();
		boolean res=followartifactDM.add(followartwork);
		followartifactDM.close();
		return res;
	}
	public boolean followfineart(Followartwork followartwork){
		FollowfineartDM followfineartDM= new FollowfineartDM();
		boolean res=followfineartDM.add(followartwork);
		followfineartDM.close();
		return res;
	}
	public boolean followprofartist(Followartist followartist){
		FollowprofartistDM followprofartistDM= new FollowprofartistDM();
		boolean res=followprofartistDM.add(followartist);
		followprofartistDM.close();
		return res;
	}
	public boolean followcraftsman(Followartist followartist){
		FollowcraftsmanDM  followcraftsmanDM =new FollowcraftsmanDM();
		boolean res=followcraftsmanDM.add(followartist);
		followcraftsmanDM.close();
		return res;
	}
	public boolean followevent(Followevent followevent){
		FolloweventDM followeventDM= new FolloweventDM();
		boolean res=followeventDM.add(followevent);
		followeventDM.close();
		return res;
	}
}
