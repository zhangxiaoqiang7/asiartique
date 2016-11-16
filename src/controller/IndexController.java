package controller;

import org.apache.catalina.deploy.LoginConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/adminstrator.do")
	private String admin(){
		return "/adminstrator";
	}
	@RequestMapping("/login.do")
	private String login(){
		return "/admin_login";
	}
	@RequestMapping("/article.do")
	private String article(){
		return "/admin_article";
	}
	@RequestMapping("/location.do")
	private String location(){
		return "/admin_location";
	}
	@RequestMapping("/profartist.do")
	private String profartist(){
		return "/admin_profartist";
	}
	@RequestMapping("/craftsman.do")
	private String craftsman(){
		return "/admin_craftsman";
	}
	@RequestMapping("/artifact.do")
	private String artifact(){
		return "/admin_artifact";
	}
	@RequestMapping("/fineart.do")
	private String fineart(){
		return "/admin_fineart";
	}
	@RequestMapping("/message.do")
	private String contact(){
		return "/admin_message";
	}
}
