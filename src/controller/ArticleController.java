package controller;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Article;
import service.ArticleService;
import util.Uploader;

@Controller
public class ArticleController {
	
	public static Vector<Article> allarticles;
	public ArticleController() {
		allarticles=new ArticleService().getAllArticles();
	}
	@RequestMapping("/admin/article/addArticle")
	@ResponseBody
	private String addArticle(HttpServletRequest request,HttpServletResponse response){
		//String id=System.currentTimeMillis()+"";
		Article article=new Article();
		article.setId(System.currentTimeMillis());
		article.setTitle(request.getParameter("title"));
		article.setAuthor(request.getParameter("author"));
		article.setDate(request.getParameter("date"));
		System.out.println(article.getTitle()+" "+article.getAuthor()+" "+article.getDate());
		boolean res=new ArticleService().addArticle(article);
		if(!res) return getBoolResult(false); 
		allarticles.add(article);//添加数据到本地内存
		//存储文本到本地
		Uploader uploader=new Uploader();
		res=uploader.loadSingleFile(request, response, request.getSession().getServletContext().getRealPath("/WEB-INF/articles")+"/"+article.getId(),".txt");
		//System.out.println(request.getSession().getServletContext().getRealPath("/WEB-INF/articles"));
		return getBoolResult(res);
	}
	
	@RequestMapping("/admin/article/delArticle")
	@ResponseBody
	private String delArticle(HttpServletRequest request,HttpServletResponse response){
		//TODO 删除内容和数据库中数据
		Long id=new Long(request.getParameter("id"));
		boolean res=new ArticleService().delArticle(id);
		if(res) delArticle(id);//删除本地内存中的数据
		deleteFile(request.getSession().getServletContext().getRealPath("/WEB-INF/articles")+"/"+id+".txt");
		return getBoolResult(res);
	}
	
	@RequestMapping("/user/article")
	@ResponseBody
	private String getArticle(HttpServletRequest request,HttpServletResponse response){
		//String id=request.getParameter("id");
		Article article=new ArticleService().getArticle(new Long(request.getParameter("id")));
		JSONObject jo=new JSONObject();
		JSONArray ja=new JSONArray();
		if (article!=null) {
			try {
				jo.put("id", article.getId());
				jo.put("title", article.getTitle());
				jo.put("author", article.getAuthor());
				jo.put("date", article.getDate());
			} catch (JSONException e) {
				e.printStackTrace();
				return ja.toString();
			}
		}
		ja.put(jo);
		return ja.toString();
	}
	
	@RequestMapping("/user/allarticles")
	@ResponseBody
	private String getAllArticles(HttpServletRequest request){
		int page=new Integer(request.getParameter("page"));
		System.out.println(page);
		Vector<Article> articles=getMutArticles(page);
		JSONArray ja=new JSONArray();
		try {
			for (Article article : articles) {
				JSONObject jo=new JSONObject();
				jo.put("id", article.getId()+"");
				jo.put("title", article.getTitle());
				jo.put("author", article.getAuthor());
				jo.put("date", article.getDate());
				ja.put(jo);
			}
		} catch (Exception e) {
			return new JSONArray().toString();
		}
		return ja.toString();
	}
	
	private Vector<Article> getMutArticles(int page){
		if(page==0) return allarticles;
		Vector<Article> mutArticles=new Vector<Article>();
		if((page-1)*20>=allarticles.size()) return mutArticles;
		for (int i = (page-1)*20; i < page*20; i++) {
			if(i>=allarticles.size()) return mutArticles;
			mutArticles.add(allarticles.get(i));
		}
		return mutArticles;
	}
	
	private void delArticle(long id){
		boolean tag=false;
		int i=0;
		for (; i < allarticles.size(); i++) {
			if(Math.abs(allarticles.get(i).getId()-id)<0.000001) {
				tag=true;
				break;
			}
		}
		if (tag) allarticles.remove(i);
	}
	public boolean deleteFile(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	} 
	
	private String getBoolResult(boolean res){
		if(res) return "[{\"result\":\"yes\"}]";
		return "[{\"result\":\"no\"}]";
	}
}
