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
 * �����������֡���������ͻ��˴������ݡ��ķ�ʽ������url�ֱ�Ϊ��index1��index2_1��index2_2��index3_1��index3_2��index3_3��
 * ����index2_1��index2_2ͨ��test package�µ�junit������TestController�ɲ��ԣ�������������󣬵�������TestController���������ˣ�
 * ����index1��index3_1��index3_2��index3_3ͨ�����г���֮�����ҳ�ϵ��ĸ���ť��ɲ��ԡ�
 * @author chun
 *
 */
@Controller
public class MainController {
	public MainController() {
		System.err.println("created");
	}
	//���ܣ���ͻ��˷��;�̬��ҳ
	/**������Ϣ��request�л�ã�
	 * ��ͼ�ɷ���ֵstring������������springAnnotation.xml�����õ���ͼ�����������ɶ�Ӧ����ͼ����
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
			String name = request.getParameter("name");//��ôӿͻ��˴���������
			System.err.println(name);
			//DBOperator opeartor = new AccountDM();
			//opeartor.add(new Object());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "/test1page";//������test1.jsp��ͼ���ͻ���
	}
	
	// ���ܣ���ͻ��˷��ͷ���ͼ���ݣ�json��xml��string�ȣ�
	/** ��һ�֣�����@ResponseBody
	 * ���������ķ�������������ַ���ͬ��������string����������@ResponseBody����ʾ���ص�string���ٽ���
	 * ��ͼ����������������ֻ����Ϊstring���ظ��ͻ��ˡ�
	 * @return
	 */
	@RequestMapping("/index2_1")
	@ResponseBody
	private String test2_1(HttpServletRequest request){
		String name = request.getParameter("name");
		System.out.println(name);
		if(name.equals("chun")) while(true);
		return "test index2_1";//���ַ������ڲ���Ҫ����ͼ�������������json����xml���ݡ�
	}
	/**
	 * �ڶ��֣�ͨ��response����Ӧ����Ϣ(��servlet����ͬ)
	 * @param response
	 */
	@RequestMapping("index2_2")
	private void test2_2(HttpServletRequest request,HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
			String parameter = request.getParameter("parameter1");//��ôӿͻ��˴���������
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * �������Լ����߼�����
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
	
	//���ܣ����ض�̬ҳ��
	/**
	 * ��һ�֣�����ModelAndView
	 * @return
	 */
	@RequestMapping("/index3_1")
	private ModelAndView test3_1(){
		ArrayList<String> list = new ArrayList<String>();//��������Щ���ݸ������ͼ
		list.add("java");
		list.add("c++");
		list.add("python");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("key1","value1");//��������Щ����
		modelAndView.addObject("key2","value2");
		modelAndView.addObject("list",list);
		modelAndView.setViewName("test3_1page");//������ת���ĸ���ͼ
		return modelAndView;
		
	}
	/**
	 * �ڶ��֣�����model
	 * @param model
	 * @return
	 */
	@RequestMapping("/index3_2")
	private String test3_2(Model model){
		String result = "test3_2page";//������ת���Ǹ���ͼ
		ArrayList<String> list = new ArrayList<String>();//��������Щ���ݸ������ͼ
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
	 * �����֣�ʹ�ô�ͳservlet+jsp��ʽ
	 * @param request
	 * @param response
	 */
	@RequestMapping("/index3_3")
	private void test3_3(HttpServletRequest request,HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
			String name = request.getParameter("name");//��ôӿͻ��˴���������
			HttpSession session = request.getSession();
			request.setAttribute("key1", "value1");//������Щ����Ҫ������ͼ
			request.setAttribute("key2", "value2");
			request.getRequestDispatcher("/test3_3page.jsp").forward(request, response); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
