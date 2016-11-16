package service;

import java.util.Vector;

import dao.ArtifactMessageDM;
import dao.FineartMessageDM;
import entity.Message;

public class MessageService {

	public boolean addArtifactMessage(Message contact){
		ArtifactMessageDM messageDM=new ArtifactMessageDM();
		boolean res=messageDM.add(contact);
		messageDM.close();
		return res;
	}
	
	public Vector<Message> getArtifactMessages(long artifact){
		ArtifactMessageDM messageDM=new ArtifactMessageDM();
		Vector<Message> contacts=(Vector<Message>) messageDM.get(artifact);
		messageDM.close();
		return contacts;
	}
	
	public boolean delArtifactMessage(Message contact){
		ArtifactMessageDM messageDM=new ArtifactMessageDM();
		boolean res=messageDM.delete(contact);
		messageDM.close();
		return res;
	}
	
	public Vector<Message> getAllMessages(int type){
		if(type==2){
			ArtifactMessageDM messageDM=new ArtifactMessageDM();
			Vector<Message> contacts=messageDM.getAllMessages();
			messageDM.close();
			return contacts;
		}
		if (type==1) {
			FineartMessageDM messageDM=new FineartMessageDM();
			Vector<Message> contacts=(Vector<Message>) messageDM.getAllMessages();
			messageDM.close();
			return contacts;
		}
		return new Vector<Message>();
	}
	
	public boolean addFineartMessage(Message contact){
		FineartMessageDM messageDM=new FineartMessageDM();
		boolean res=messageDM.add(contact);
		messageDM.close();
		return res;
	}
	
	public Vector<Message> getFineartMessages(long artifact){
		FineartMessageDM messageDM=new FineartMessageDM();
		Vector<Message> contacts=(Vector<Message>) messageDM.get(artifact);
		messageDM.close();
		return contacts;
	}
	
	public boolean delFineartMessage(Message contact){
		FineartMessageDM messageDM=new FineartMessageDM();
		boolean res=messageDM.delete(contact);
		messageDM.close();
		return res;
	}
	
}
