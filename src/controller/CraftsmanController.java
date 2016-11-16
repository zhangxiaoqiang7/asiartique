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

import service.CraftsmanService;
import util.Uploader;
import entity.Craftsman;

@Controller
public class CraftsmanController {
	
	public static Vector<Craftsman> allcraftsmen;
	public CraftsmanController() {
		allcraftsmen=new CraftsmanService().getAllCraftsmans();
	}
	
	@RequestMapping("/user/craftsman")
	@ResponseBody
	public String getCraftsmen(HttpServletRequest req) {
		String id = req.getParameter("id");
		Craftsman craftsman = new CraftsmanService().getCraftsman(new Long(id));
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		try {
			jo.put("id", craftsman.getId());
			jo.put("name", craftsman.getName());
			jo.put("description", craftsman.getDescription());
			jo.put("location", craftsman.getLocation());
			jo.put("locname", craftsman.getLocname());
			jo.put("works", craftsman.getWorks());
			ja.put(jo);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return ja.toString();
	}
	
	@RequestMapping("/user/allcraftsmen")
	@ResponseBody
	public String getAllCraftsmen(HttpServletRequest req) {
		String page = req.getParameter("page");
		Vector<Craftsman> craftsmen = getMutCraftsmen(new Integer(page));
		JSONArray ja = new JSONArray();
		try {
			for (Craftsman craftsman : craftsmen) {
				JSONObject jo = new JSONObject();
				jo.put("id", craftsman.getId());
				jo.put("name", craftsman.getName());
				jo.put("location", craftsman.getLocation());
				jo.put("locname", craftsman.getLocname());
				jo.put("works", craftsman.getWorks());
				ja.put(jo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return ja.toString();
	}
	
	@RequestMapping("/admin/artist/addCraftsman")
	@ResponseBody
	public String addCraftsman(HttpServletRequest request,HttpServletResponse response){
		Craftsman craftsman=new Craftsman();
		craftsman.setId(System.currentTimeMillis());
		craftsman.setDescription(request.getParameter("description"));
		craftsman.setLocation(new Long(request.getParameter("location")));
		craftsman.setName(request.getParameter("name"));
		//将数据保存到数据库
		boolean res=new CraftsmanService().addCraftsman(craftsman);
		if(!res) return getBoolResult(false);
		//将数据保存到本地内存中
		craftsman.setDescription("");
		allcraftsmen.add(craftsman);
		//将图片保存到服务器
		Uploader uploader=new Uploader();
		res=uploader.loadSingleFile(request, response, request.getSession().getServletContext().getRealPath("/WEB-INF/images/craftsman")+"/"+craftsman.getId(),".jpg");
		return getBoolResult(res);
	}
	
	@RequestMapping("/admin/artist/delCraftsman")
	@ResponseBody
	public String delCraftsman(HttpServletRequest request){
		long id=new Long(request.getParameter("id"));
		boolean res=new CraftsmanService().delCraftsman(id);
		if(!res) return getBoolResult(false);
		//删除内存中的数据
		delCraftsman(id);
		//删除头像
		deleteFile(request.getSession().getServletContext().getRealPath("/WEB-INF/images/craftsman")+"/"+id+".jpg");
		deleteFile(request.getSession().getServletContext().getRealPath("/WEB-INF/images/craftsman")+"/"+id+"_.jpg");
		return getBoolResult(res);
	}
	
	private Vector<Craftsman> getMutCraftsmen(int page){
		if(page==0) return allcraftsmen;
		Vector<Craftsman> mutcraftsmen=new Vector<Craftsman>();
		if((page-1)*20>=allcraftsmen.size()) return mutcraftsmen;
		for (int i = (page-1)*20; i < page*20; i++) {
			if(i>=allcraftsmen.size()) break;
			mutcraftsmen.add(allcraftsmen.get(i));
		}
		return mutcraftsmen;
	}
	
	private void delCraftsman(long id){
		boolean tag=false;
		int i=0;
		for (; i < allcraftsmen.size(); i++) {
			if(Math.abs(allcraftsmen.get(i).getId()-id)<0.000001) {
				tag=true;
				break;
			}
		}
		if (tag) allcraftsmen.remove(i);
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
