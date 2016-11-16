package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Single;

import entity.Account;
import service.LoginService;
import service.SignupService;

@Controller
@RequestMapping("/user/account")
public class AccountController {
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest req,HttpServletResponse rep){
		System.out.println("??");
		String email=req.getParameter("email");
		String pw=req.getParameter("password");
		LoginService login=new LoginService();
		Account account=login.login(email, pw);
		if(account==null) return null;
		JSONObject jo=new JSONObject();
		JSONArray ja=new JSONArray();
		try {
			jo.put("id", account.getId());
			jo.put("email", account.getEmail());
			jo.put("name", account.getName());
			ja.put(jo);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(account.getEmail()+" "+account.getPassword());
		Cookie namecookie=new Cookie("email", account.getName());
		Cookie pwcookie=new Cookie("password", account.getPassword());
		namecookie.setMaxAge(3600*24);
		pwcookie.setMaxAge(3600*24);
		rep.addCookie(namecookie);
		rep.addCookie(pwcookie);
		//TODO 
		return ja.toString();
	}
	
	@RequestMapping("/signup")
	@ResponseBody
	public String signup(HttpServletRequest req){
		String name=req.getParameter("username");
		String pw=req.getParameter("password");
		String email=req.getParameter("email");
		System.out.println(name+"--"+pw+"--"+email);
		SignupService signup=new SignupService();
		JSONObject jo=new JSONObject();
		JSONArray ja=new JSONArray();
		try {
			jo.put("res", signup.signup(name, email, pw));
			ja.put(jo);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ja.toString();
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public void logout(){
		//É¾³ý·þÎñÆ÷¶Ësession
	}
	
	@RequestMapping("/findpassword")
	@ResponseBody
	public void findpassword(){
		
	}
	
	
	private String getBoolResult(boolean res){
		if(res) return "[{\"result\":\"yes\"}]";
		return "[{\"result\":\"no\"}]";
	}
	
}
