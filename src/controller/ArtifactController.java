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

import service.ArtifactService;
import service.MessageService;
import util.Uploader;
import entity.Artifact;
import entity.Message;

@Controller
public class ArtifactController {
	public static Vector<Artifact> allartifacts;
	public ArtifactController() {
		allartifacts=new ArtifactService().getAllArtifacts();
	}
	
	@RequestMapping("/user/artifact")
	@ResponseBody
	public String getArtifact(HttpServletRequest req){
		String id=req.getParameter("id");
		Artifact artifact=new ArtifactService().getArtifact(new Long(id));
		if(artifact==null) return null;
		JSONObject jo=new JSONObject();
		JSONArray ja=new JSONArray();
		try {
			jo.put("id", artifact.getId());
			jo.put("name", artifact.getName());
			jo.put("description", artifact.getDescription());
			jo.put("location", artifact.getLocation());
			jo.put("locname", artifact.getLocname());
			jo.put("picnum", artifact.getPicNum());
			jo.put("link", artifact.getLink());
			jo.put("artist", artifact.getArtist());
			jo.put("artistname", artifact.getArtistname());
			jo.put("price", artifact.getPrice());
			jo.put("type", artifact.getType());
			Vector<String> tags=artifact.getTags();
			jo.put("tags", tags);
			ja.put(jo);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ja.toString();
	}
	
	@RequestMapping("/user/allartifacts")
	@ResponseBody
	public String getAllArtifact(HttpServletRequest req){
		String page=req.getParameter("page");
		Vector<Artifact> artifacts=getMutArtifacts(new Integer(page));
		JSONArray ja=new JSONArray();
		try {
			for (Artifact artifact : artifacts) {
				JSONObject jo=new JSONObject();
				jo.put("id", artifact.getId());
				jo.put("name", artifact.getName());
				jo.put("location", artifact.getLocation());
				jo.put("picnum", artifact.getPicNum());
				jo.put("link", artifact.getLink());
				jo.put("artist", artifact.getArtist());
				jo.put("price", artifact.getPrice());
				Vector<String> tags=artifact.getTags();
				jo.put("tags", tags);
				ja.put(jo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ja.toString();
	}
	
	@RequestMapping("/admin/artwork/addArtifact")
	@ResponseBody
	public String addArtifact(HttpServletRequest request ,HttpServletResponse response){
		Artifact artifact=new Artifact();
		artifact.setArtist(new Long(request.getParameter("artist")));
		artifact.setDescription(request.getParameter("description"));
		artifact.setId(System.currentTimeMillis());
		artifact.setLink(request.getParameter("link"));
		artifact.setLocation(new Long(request.getParameter("location")));
		artifact.setName(request.getParameter("name"));
		artifact.setPicNum(new Integer(request.getParameter("picnum")));
		artifact.setPrice(new Float(request.getParameter("price")));
		Vector<String> tags=new Vector<String>();
		String string=request.getParameter("tags");
		if (!string.isEmpty()) {
			for (String tag : string.split(",")) {
				tags.add(tag);
			}
		}
		artifact.setTags(tags);
		artifact.setSold("n");
		//将artifact添加到数据库
		boolean res=new ArtifactService().addArtifact(artifact);
		if(!res) return getBoolResult(false);
		//将数据添加到本地缓存
		artifact.setDescription("");
		allartifacts.add(artifact);
		//将图片加载到相应目录
		Uploader uploader=new Uploader();
		res=uploader.loadMultipleFiles(request, response, request.getSession().getServletContext().getRealPath("/WEB-INF/images/artifact/")+"/"+artifact.getId(),".jpg");
		return getBoolResult(res);
	}
	
	@RequestMapping("/admin/artwork/modifyArtifact")
	@ResponseBody
	public String modifyArtifact(HttpServletRequest request){
		ArtifactService artifactService=new ArtifactService();
		Artifact artifact=artifactService.getArtifact(new Long(request.getParameter("artifact")));
		//artifact.setArtist(new Long(request.getParameter("artist")));
		if(!request.getParameter("artist").isEmpty()) artifact.setArtist(new Long(request.getParameter("artist")));
		//artifact.setDescription(request.getParameter("description"));
		if(!request.getParameter("description").isEmpty()) artifact.setDescription(request.getParameter("description"));
		//artifact.setLink(request.getParameter("link"));
		if(!request.getParameter("link").isEmpty()) artifact.setLink(request.getParameter("link"));; 
		//artifact.setLocation(new Long(request.getParameter("location")));
		if(!request.getParameter("location").isEmpty()) artifact.setLocation(new Long(request.getParameter("location")));
		//artifact.setName(request.getParameter("name"));
		if(!request.getParameter("name").isEmpty()) artifact.setName(request.getParameter("name"));
		//artifact.setPrice(new Float(request.getParameter("price")));
		if(!request.getParameter("price").isEmpty()) artifact.setPrice(new Float(request.getParameter("price")));
		if (!request.getParameter("tags").isEmpty()) {
			Vector<String> tags=new Vector<String>();
			String string=request.getParameter("tags");
			if (!string.isEmpty()) {
				for (String tag : string.split(",")) {
					tags.add(tag);
				}
			}
			artifact.setTags(tags);
		}
		if(!request.getParameter("sold").isEmpty()) artifact.setSold(request.getParameter("sold"));
		boolean res=artifactService.updateArtifact(artifact);
		return getBoolResult(res);
	}
	
	@RequestMapping("/admin/artwork/delArtifact")
	@ResponseBody
	public String delArtifact(HttpServletRequest request){
		long id=new Long(request.getParameter("id"));
		ArtifactService service=new ArtifactService();
		int picnum=service.getPictnum(id);
		//删除数据库中的数据
		boolean res=service.delArtifact(id);
		if(!res) return getBoolResult(false);
		//删除内存中的数据
		delartifacts(id);
		//删除图片
		deleteFile(request.getSession().getServletContext().getRealPath("/WEB-INF/images/artifact")+"/"+id,".jpg",picnum);
		return getBoolResult(res);
	}
	
	@RequestMapping("/user/artifact/addMsg")
	@ResponseBody
	private String addContact(HttpServletRequest req){
		Message contact=new Message();
		contact.setArtwork(new Long(req.getParameter("artwork")));
		contact.setName(req.getParameter("name"));
		contact.setEmail(req.getParameter("email"));
		contact.setMessage(req.getParameter("msg"));
		MessageService contactService=new MessageService();
		boolean res=contactService.addArtifactMessage(contact);
		return getBoolResult(res);
	}
	
	@RequestMapping("/admin/artifact/delMsg")
	@ResponseBody
	private String delContact(HttpServletRequest req){
		Message contact=new Message();
		contact.setArtwork(new Long(req.getParameter("artwork")));
		contact.setName(req.getParameter("name"));
		contact.setEmail(req.getParameter("email"));
		contact.setMessage(req.getParameter("msg"));
		MessageService contactService=new MessageService();
		boolean res=contactService.delArtifactMessage(contact);
		return getBoolResult(res);
	}
	/**
	 * get a messages of an artifact
	 * @param req
	 * @return
	 */
	@RequestMapping("/admin/artifact/getMsg")
	@ResponseBody
	private String getContacts(HttpServletRequest req){
		long artifact=new Long(req.getParameter("artwork"));
		MessageService contactService=new MessageService();
		Vector<Message> res=contactService.getArtifactMessages(artifact);
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
	
	
	
	private Vector<Artifact> getMutArtifacts(int page){
		if(page==0) return allartifacts;
		Vector<Artifact> mutartifacts=new Vector<Artifact>();
		if((page-1)*20>=allartifacts.size()) return mutartifacts;
		for (int i = (page-1)*20; i < page*20; i++) {
			if(i>=allartifacts.size()) break;
			mutartifacts.add(allartifacts.get(i));
		}
		return mutartifacts;
	}
	
	private void delartifacts(long id){
		boolean tag=false;
		int i=0;
		for (; i < allartifacts.size(); i++) {
			if(Math.abs(allartifacts.get(i).getId()-id)<0.000001) {
				tag=true;
				break;
			}
		}
		if (tag) allartifacts.remove(i);
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
	
	private String getBoolResult(boolean res){
		if(res) return "[{\"result\":\"yes\"}]";
		return "[{\"result\":\"no\"}]";
	}
}
