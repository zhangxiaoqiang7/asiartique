package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dao.AccountDM;
import dao.DBOperator;
/**
 * 这个类包括六种“服务器向客户端传递数据”的方式，访问url分别为：index1，index2_1，index2_2，index3_1，index3_2，index3_3。
 * 其中index2_1，index2_2通过test package下的junit测试类TestController可测试（运行整个程序后，单独运行TestController这个类就行了）
 * 其中index1，index3_1，index3_2，index3_3通过运行程序之后的主页上的四个按钮便可测试。
 * @author chun
 *
 */
@Controller
public class MainController {
	public MainController() {
		System.err.println("created");
	}
	//功能：向客户端发送静态网页
	/**请求信息从request中获得；
	 * 视图由返回值string所决定（交由springAnnotation.xml中配置的视图解析器解析成对应的视图）；
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("/testlogin")
	private String testCookie(HttpServletRequest request,HttpServletResponse response){
		System.out.println("....");
		boolean res=false;
		if(request.getParameter("email").equals("123@qq.com")&&request.getParameter("password").equals("0000")) res=true;
		if (res) {
			Cookie namecookie=new Cookie("email", "123@qq.com");
			Cookie pwcookie=new Cookie("password", "0000");
			namecookie.setMaxAge(3600*24);
			pwcookie.setMaxAge(3600*24);
			response.addCookie(namecookie);
			response.addCookie(pwcookie);
		}
		return "/login";
	}
	
	@RequestMapping("/index1")
	private String test1(HttpServletRequest request){
		try {
			request.setCharacterEncoding("utf-8");
			String name = request.getParameter("name");//获得从客户端传来的数据
			System.err.println(name);
			//DBOperator opeartor = new AccountDM();
			//opeartor.add(new Object());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "/test1page";//决定传test1.jsp视图到客户端
	}
	
	// 功能：向客户端发送非视图数据（json、xml、string等）
	/** 第一种：利用@ResponseBody
	 * 相对于上面的方法，下面的两种方法同样返回了string，但是由于@ResponseBody，表示返回的string不再交由
	 * 视图解析器解析，而是只是作为string返回给客户端。
	 * @return
	 */
	@RequestMapping("/index2_1")
	@ResponseBody
	private String test2_1(HttpServletRequest request){
		String name = request.getParameter("name");
		System.out.println(name);
		if(name.equals("chun")) while(true);
		return "test index2_1";//这种方法用于不需要穿视图的情况，往往传json或者xml数据。
	}
	/**
	 * 第二种：通过response返回应答信息(和servlet中相同)
	 * @param response
	 */
	@RequestMapping("index2_2")
	private void test2_2(HttpServletRequest request,HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
			String parameter = request.getParameter("parameter1");//获得从客户端传来的数据
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * 定义你自己的逻辑代码
		 */
		
		try {
			String result = "test index2_2";
			OutputStream out=null;
			out = response.getOutputStream();
			out.write(result.getBytes());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//功能：返回动态页面
	/**
	 * 第一种：利用ModelAndView
	 * @return
	 */
	@RequestMapping("/index3_1")
	private ModelAndView test3_1(){
		ArrayList<String> list = new ArrayList<String>();//决定传哪些数据给这个视图
		list.add("java");
		list.add("c++");
		list.add("python");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("key1","value1");//决定传哪些数据
		modelAndView.addObject("key2","value2");
		modelAndView.addObject("list",list);
		modelAndView.setViewName("test3_1page");//决定跳转到哪个视图
		return modelAndView;
		
	}
	/**
	 * 第二种：利用model
	 * @param model
	 * @return
	 */
	@RequestMapping("/index3_2")
	private String test3_2(Model model){
		String result = "test3_2page";//决定跳转到那个视图
		ArrayList<String> list = new ArrayList<String>();//决定传哪些数据给这个视图
		list.add("java");
		list.add("c++");
		list.add("python");
		model.addAttribute("key1", "value1");
		model.addAttribute("key2", "value2");
		model.addAttribute("list",list);
		System.out.println(list.size());
		return result;
	}
	/**
	 * 第三种：使用传统servlet+jsp方式
	 * @param request
	 * @param response
	 */
	@RequestMapping("/index3_3")
	private void test3_3(HttpServletRequest request,HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
			String name = request.getParameter("name");//获得从客户端传来的数据
			HttpSession session = request.getSession();
			request.setAttribute("key1", "value1");//定义哪些数据要传给视图
			request.setAttribute("key2", "value2");
			request.getRequestDispatcher("/test3_3page.jsp").forward(request, response); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
