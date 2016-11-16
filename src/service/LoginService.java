package service;
import dao.AccountDM;
import entity.Account;

public class LoginService {
	public Account login(String email,String pw){
		AccountDM accountDM=new AccountDM();
		Account account=(Account) accountDM.get(email);
		if(account==null||!pw.equals(account.getPassword())) return null;
		accountDM.close();
		return account;
	}
}
