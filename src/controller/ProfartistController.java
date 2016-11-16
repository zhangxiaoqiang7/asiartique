package controller;

import java.io.File;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.ProfartistService;
import util.Uploader;
import entity.Profartist;

@Controller
public class ProfartistController {
	
	public static Vector<Profartist> allprofartists;
	public ProfartistController() {
		allprofartists=new ProfartistService().getAllProfartists();
	}
	
	@RequestMapping("/user/profartist")
	@ResponseBody
	public String getProfartist(HttpServletRequest req) {
		String id = req.getParameter("id");
		Profartist profartist = new ProfartistService().getProfartist(new Long(
				id));
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		try {
			jo.put("id", profartist.getId());
			jo.put("name", profartist.getName());
			jo.put("description", profartist.getDescription());
			jo.put("location", profartist.getLocation());
			jo.put("locname", profartist.getLocname());
			jo.put("works", profartist.getWorks());
			ja.put(jo);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return ja.toString();
	}
	
	@RequestMapping("/user/allprofartists")
	@ResponseBody
	public String getAllProfartist(HttpServletRequest req) {
		String page = req.getParameter("page");
		Vector<Profartist> profartists = getMutProfartists(new Integer(page));
		JSONArray ja = new JSONArray();
		try {
			for (Profartist profartist : profartists) {
				JSONObject jo = new JSONObject();
				jo.put("id", profartist.getId());
				jo.put("name", profartist.getName());
				jo.put("location", profartist.getLocation());
				jo.put("locname", profartist.getLocname());
				jo.put("works", profartist.getWorks());
				ja.put(jo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return ja.toString();
	}
	
	@RequestMapping("/admin/artist/addProfartist")
	@ResponseBody
	public String addProfartist(HttpServletRequest request,HttpServletResponse response){
		Profartist profartist=new Profartist();
		profartist.setId(System.currentTimeMillis());
		profartist.setDescription(request.getParameter("description"));
		profartist.setLocation(new Long(request.getParameter("location")));
		profartist.setName(request.getParameter("name"));
		//将数据保存到数据库
		boolean res=new ProfartistService().addProfartist(profartist);
		if(!res) return getBoolResult(false);
		//将数据保存到本地内存中
		profartist.setDescription("");
		allprofartists.add(profartist);
		//将图片保存到服务器
		Uploader uploader=new Uploader();
		res=uploader.loadSingleFile(request, response, request.getSession().getServletContext().getRealPath("/WEB-INF/images/profartist")+"/"+profartist.getId(),".jpg");
		return getBoolResult(res);
	}
	
	@RequestMapping("/admin/artist/delProfartist")
	@ResponseBody
	public String delProfartist(HttpServletRequest request){
		long id=new Long(request.getParameter("id"));
		boolean res=new ProfartistService().delProfartist(id);
		if(!res) return getBoolResult(false);
		//删除内存中的数据
		delProfartist(id);
		//删除头像
		deleteFile(request.getSession().getServletContext().getRealPath("/WEB-INF/images/profartist")+"/"+id+".jpg");
		deleteFile(request.getSession().getServletContext().getRealPath("/WEB-INF/images/profartist")+"/"+id+"_.jpg");
		return getBoolResult(res);
	}
	
	private Vector<Profartist> getMutProfartists(int page){
		if(page==0) return allprofartists;
		Vector<Profartist> mutprofartists=new Vector<Profartist>();
		if((page-1)*20>=allprofartists.size()) return mutprofartists;
		for (int i = (page-1)*20; i < page*20; i++) {
			if(i>=allprofartists.size()) break;
			mutprofartists.add(allprofartists.get(i));
		}
		return mutprofartists;
	}
	
	private void delProfartist(long id){
		boolean tag=false;
		int i=0;
		for (; i < allprofartists.size(); i++) {
			if(Math.abs(allprofartists.get(i).getId()-id)<0.000001) {
				tag=true;
				break;
			}
		}
		if (tag) allprofartists.remove(i);
	}
	private boolean deleteFile(String sPath) {  
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
