package controller;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.LocationService;
import util.Uploader;
import entity.Location;

@Controller
public class LocationController {

	@RequestMapping("/user/location")
	@ResponseBody
	public String getLocation(HttpServletRequest req) {
		String id = req.getParameter("id");
		Location location = new LocationService().getLocation(new Long(id));
		if (location == null)
			return null;
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		try {
			jo.put("id", location.getId());
			jo.put("name", location.getName());
			jo.put("description", location.getDescription());
			jo.put("picnum", location.getPicnum());
			ja.put(jo);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return ja.toString();
	}

	@RequestMapping("/user/allLocations")
	@ResponseBody
	public String getAllLocations(HttpServletRequest request) {
		// 由于管理员数目相对于用户是非常小的，因此不需设定缓存
		int page = new Integer(request.getParameter("page"));
		Vector<Location> locations = new LocationService().getAllLocations();
		JSONArray ja = new JSONArray();
		int st = 0, ed = 0;
		if (page == 0) {
			st = 0;
			ed = locations.size();
		} else {
			if ((page - 1) * 20 > locations.size())
				return ja.toString();
			st = (page - 1) * 20;
			ed = locations.size() > page * 20 ? page * 20 : locations.size();
		}
		try {
			for (int i = st; i < ed; i++) {
				JSONObject jo = new JSONObject();
				jo.put("id", locations.get(i).getId());
				jo.put("name", locations.get(i).getName());
				jo.put("description", locations.get(i).getDescription());
				jo.put("picnum", locations.get(i).getPicnum());
				ja.put(jo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ja.toString();
	}

	@RequestMapping("/admin/location/addLocation")
	@ResponseBody
	public String addLocation(HttpServletRequest request,HttpServletResponse response){
		Location location=new Location();
		location.setId(System.currentTimeMillis());
		location.setName(request.getParameter("name"));
		location.setDescription(request.getParameter("description"));
		System.out.println(request.getParameter("picnum"));
		location.setPicnum(new Integer(request.getParameter("picnum")));
		boolean res=new LocationService().addLocation(location);
		if(!res) return getBoolResult(false);
		//存储文本到本地
		Uploader uploader=new Uploader();
		res=uploader.loadMultipleFiles(request, response, request.getSession().getServletContext().getRealPath("/WEB-INF/images/location")+"/"+location.getId(),".jpg");
		return getBoolResult(res);
	}

	@RequestMapping("/admin/location/delLocation")
	@ResponseBody
	public String delLocation(HttpServletRequest request){
		long id=new Long(request.getParameter("id"));
		int picnum=new LocationService().getPicnum(id);
		boolean res=new LocationService().delLocation(id);
		if(!res) return getBoolResult(false);
		System.out.println(id);
		deleteFile(request.getSession().getServletContext().getRealPath("/WEB-INF/images/location")+"/"+id,".jpg",picnum);
		return getBoolResult(res);
	}

	@RequestMapping("/admin/location/modifyLocation")
	@ResponseBody
	public String modifyLocation(HttpServletRequest request){
		Location location=new Location();
		location.setId(System.currentTimeMillis());
		location.setName(request.getParameter("name"));
		location.setDescription(request.getParameter("description"));
		location.setPicnum(new Integer(request.getParameter("picnum")));
		boolean res=new LocationService().updateLocation(location);
		return getBoolResult(res);
	}

	public boolean deleteFile(String sPath,String suffix,int picnum) {  
	    boolean flag = false;  
	    // 路径为文件且不为空则进行删除  
	    for(int i=1;i<=picnum;i++){
	    	System.out.println(sPath+"_"+i+suffix);
	    	File file = new File(sPath+"_"+i+suffix);  
	    	if (file.isFile() && file.exists()) {  
		        file.delete();  
		        flag = true;  
		    }
	    	file=new File(sPath+"_"+i+"_"+suffix);
	    	if (file.isFile() && file.exists()) {  
		        file.delete();  
		        flag = true;  
		    }
	    }
	    return flag;  
	}
	
	private String getBoolResult(boolean res) {
		if (res)
			return "[{\"result\":\"yes\"}]";
		return "[{\"result\":\"no\"}]";
	}

}
