package service;

import java.util.Vector;

import dao.EmailDM;
import entity.Email;

public class EmailService {
	public boolean addEmail(Email email){
		EmailDM emailDM= new EmailDM();
		boolean res=emailDM.add(email);
		emailDM.close();
		return res;
	}
	@SuppressWarnings("unchecked")
	public Vector<String> getEmails(long id){
		EmailDM emailDM=new EmailDM();
		Vector<String> res=(Vector<String>) emailDM.get(id);
		emailDM.close();
		return res;
	}
	public boolean delEvents(long id){
		EmailDM emailDM= new EmailDM();
		boolean res=emailDM.delete(id);
		emailDM.close();
		return res;
	}
}
