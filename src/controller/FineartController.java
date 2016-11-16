package controller;

import java.io.File;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.MessageService;
import service.FineartService;
import util.Uploader;
import entity.Message;
import entity.Fineart;

@Controller
public class FineartController {
	
	public static Vector<Fineart> allfinearts;
	public FineartController() {
		allfinearts=new FineartService().getAllFinearts();
	}
	
	@RequestMapping("/user/fineart")
	@ResponseBody
	public String getFineart(HttpServletRequest req){
		String id=req.getParameter("id");
		Fineart fineart=new FineartService().getFineart(new Long(id));
		if(fineart==null) return null;
		JSONObject jo=new JSONObject();
		JSONArray ja=new JSONArray();
		try {
			jo.put("id", fineart.getId());
			jo.put("name", fineart.getName());
			jo.put("description", fineart.getDescription());
			jo.put("location", fineart.getLocation());
			jo.put("locname", fineart.getLocname());
			jo.put("artist", fineart.getArtist());
			jo.put("artistname", fineart.getArtistname());
			jo.put("picnum", fineart.getPicNum());
			jo.put("height", fineart.getHeight());
			jo.put("width", fineart.getWidth());
			jo.put("date", fineart.getDate());
			jo.put("kinds", fineart.getKinds());
			jo.put("color", fineart.getColor());
			jo.put("price", fineart.getPrice());
			jo.put("tags", fineart.getTags());
			jo.put("type", fineart.getType());
			ja.put(jo);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return ja.toString();
	}
	
	@RequestMapping("/user/allfinearts")
	@ResponseBody
	public String getAllFineart(HttpServletRequest req){
		String page=req.getParameter("page");
		Vector<Fineart> finearts=getMutFinearts(new Integer(page));
		JSONArray ja=new JSONArray();
		try {
			for (Fineart fineart : finearts) {
				JSONObject jo=new JSONObject();
				jo.put("id", fineart.getId());
				jo.put("name", fineart.getName());
				jo.put("location", fineart.getLocation());
				jo.put("artist", fineart.getArtist());
				jo.put("picnum", fineart.getPicNum());
				jo.put("height", fineart.getHeight());
				jo.put("width", fineart.getWidth());
				jo.put("date", fineart.getDate());
				jo.put("kinds", fineart.getKinds());
				jo.put("color", fineart.getColor());
				jo.put("price", fineart.getPrice());
				jo.put("tags", fineart.getTags());
				jo.put("type", fineart.getType());
				ja.put(jo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return ja.toString();
	}
	
	@RequestMapping("/user/fineart/addMsg")
	@ResponseBody
	private String addContact(HttpServletRequest req){
		Message contact=new Message();
		contact.setArtwork(new Long(req.getParameter("artwork")));
		contact.setName(req.getParameter("name"));
		contact.setEmail(req.getParameter("email"));
		contact.setMessage(req.getParameter("msg"));
		System.out.println("msg: "+contact.getMessage().length());
		MessageService contactService=new MessageService();
		boolean res=contactService.addFineartMessage(contact);
		return getBoolResult(res);
	}
	
	@RequestMapping("/admin/fineart/delMsg")
	@ResponseBody
	private String delContact(HttpServletRequest req){
		Message contact=new Message();
		contact.setArtwork(new Long(req.getParameter("artwork")));
		contact.setEmail(req.getParameter("email"));
		MessageService contactService=new MessageService();
		boolean res=contactService.delFineartMessage(contact);
		return getBoolResult(res);
	}
	/**
	 * get messages of a fineart
	 * @param req
	 * @return
	 */
	@RequestMapping("/admin/fineart/getMsg")
	@ResponseBody
	private String getContacts(HttpServletRequest req){
		long artifact=new Long(req.getParameter("artwork"));
		MessageService contactService=new MessageService();
		Vector<Message> res=contactService.getFineartMessages(artifact);
		JSONArray ja=new JSONArray();
		try {
			for (Message contact : res) {
				JSONObject jo=new JSONObject();
				jo.put("artwork", contact.getArtwork());
				jo.put("name", contact.getName());
				jo.put("email", contact.getEmail());
				jo.put("msg", contact.getMessage());
				ja.put(jo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ja.toString();
	}
	
	/**
	 * get all messages of one artwork type 
	 * @param req
	 * @return
	 */
	@RequestMapping("/admin/msgs")
	@ResponseBody
	private String getBothContacts(HttpServletRequest req){
		int type=new Integer(req.getParameter("type"));
		System.out.println(type);
		MessageService contactService=new MessageService();
		Vector<Message> res=contactService.getAllMessages(type);
		JSONArray ja=new JSONArray();
		try {
			for (Message contact : res) {
				JSONObject jo=new JSONObject();
				jo.put("artwork", contact.getArtwork());
				jo.put("name", contact.getName());
				jo.put("email", contact.getEmail());
				jo.put("msg", contact.getMessage());
				ja.put(jo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ja.toString();
	}
	
	@RequestMapping("/admin/artwork/addFineart")
	@ResponseBody
	public String addFineart(HttpServletRequest req,HttpServletResponse rep){
		Fineart fineart=new Fineart();
		fineart.setName(req.getParameter("name"));
		fineart.setId(System.currentTimeMillis());
		fineart.setDescription(req.getParameter("description"));
		fineart.setPrice(new Float(req.getParameter("price")));
		fineart.setDate(req.getParameter("date"));
		fineart.setLocation(new Long(req.getParameter("location")));
		fineart.setKinds(req.getParameter("kinds"));
		fineart.setColor(new Integer(req.getParameter("color")));
		fineart.setPicNum(new Integer(req.getParameter("picnum")));
		fineart.setWidth(new Integer(req.getParameter("width")));
		fineart.setHeight(new Integer(req.getParameter("height")));
		fineart.setArtist(new Long(req.getParameter("artist")));
		String str=req.getParameter("tags");
		Vector<String> tags=new Vector<String>();
		if(!str.isEmpty()){
			for (String tag : str.split(",")) {
				tags.add(tag);
			}
		}
		fineart.setTags(tags);
		fineart.setSold("n");
		//将数据添加到数据库
		boolean res=new FineartService().addFineart(fineart);
		if(!res) return getBoolResult(false);
		//将数据添加到内存
		fineart.setDescription("");
		allfinearts.add(fineart);
		//将图片保存到本地
		Uploader uploader=new Uploader();
		res=uploader.loadMultipleFiles(req, rep, req.getSession().getServletContext().getRealPath("/WEB-INF/images/fineart/")+"/"+fineart.getId(),".jpg");
		return getBoolResult(res);
	}
	
	@RequestMapping("/admin/artwork/delFineart")
	@ResponseBody
	public String delFineart(HttpServletRequest req){
		long id=new Long(req.getParameter("id"));
		FineartService service=new FineartService();
		int picnum=service.getPicnum(id);
		//删除数据库中的数据
		boolean res=service.delFineart(id);
		if(!res) return getBoolResult(false);
		//删除内存中的数据
		delFineart(id);
		//删除图片
		deleteFile(req.getSession().getServletContext().getRealPath("/WEB-INF/images/fineart/")+"/"+id, ".jpg", picnum);
		return getBoolResult(res);
	}
	
	@RequestMapping("/admin/artwork/modifyFineart")
	@ResponseBody
	public String modifyFineart(HttpServletRequest req){
		FineartService fineartService=new FineartService();
		Fineart fineart=fineartService.getFineart(new Long(req.getParameter("fineart")));
		//System.out.println(req.getParameter("name").isEmpty());
		if(!req.getParameter("name").isEmpty()) fineart.setName(req.getParameter("name"));
		if(!req.getParameter("description").isEmpty()) fineart.setDescription(req.getParameter("description"));
		if(!req.getParameter("price").isEmpty()) fineart.setPrice(new Float(req.getParameter("price")));
		if(!req.getParameter("date").isEmpty()) fineart.setDate(req.getParameter("date"));
		if(!req.getParameter("location").isEmpty()) {
			System.out.println("location"+req.getParameter("location"));
			fineart.setLocation(new Long(req.getParameter("location")));
		}
		if(!req.getParameter("kind").isEmpty()) fineart.setKinds(req.getParameter("kind"));
		if(!req.getParameter("color").isEmpty()) fineart.setColor(new Integer(req.getParameter("color")));
		if(!req.getParameter("width").isEmpty()) fineart.setWidth(new Integer(req.getParameter("width")));
		if(!req.getParameter("height").isEmpty()) fineart.setHeight(new Integer(req.getParameter("height"))); 
		if(!req.getParameter("artist").isEmpty()) fineart.setArtist(new Long(req.getParameter("artist")));
		if(!req.getParameter("tags").isEmpty()){
			String str=req.getParameter("tags");
			Vector<String> tags=new Vector<String>();
			if(!str.isEmpty()){
				for (String tag : str.split(",")) {
					tags.add(tag);
				}
			}
			fineart.setTags(tags);
		}
		if(!req.getParameter("sold").isEmpty()) fineart.setSold(req.getParameter("sold"));
		boolean res=fineartService.updateFineart(fineart);
		return getBoolResult(res);
	}
	
	private void delFineart(long id){
		boolean tag=false;
		int i=0;
		for (; i < allfinearts.size(); i++) {
			if(Math.abs(allfinearts.get(i).getId()-id)<0.000001) {
				tag=true;
				break;
			}
		}
		if (tag) allfinearts.remove(i);
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
	
	private Vector<Fineart> getMutFinearts(int page){
		if(page==0) return allfinearts;
		Vector<Fineart> mutfinearts=new Vector<Fineart>();
		if((page-1)*20>=allfinearts.size()) return mutfinearts;
		for (int i = (page-1)*20; i < page*20; i++) {
			if(i>=allfinearts.size()) break;
			mutfinearts.add(allfinearts.get(i));
		}
		return mutfinearts;
	}
	
	private String getBoolResult(boolean res){
		if(res) return "[{\"result\":\"yes\"}]";
		return "[{\"result\":\"no\"}]";
	}
}
