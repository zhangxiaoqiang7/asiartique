package service;

import java.util.Date;

import dao.AccountDM;
import entity.Account;

public class SignupService {
	public int signup(String name,String email,String pw){
		AccountDM accountDM=new AccountDM();
		Account account=(Account) accountDM.get(email);
		if(account!=null) return 0;
		account=new Account();
		account.setId(new Date().getTime());
		account.setName(name);
		account.setEmail(email);
		account.setPassword(pw);
		boolean res=accountDM.add(account);
		accountDM.close();
		if(res==true) return 1;
		return 0;
	}
}
