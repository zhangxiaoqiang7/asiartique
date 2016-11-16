package controller;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.EventService;
import entity.Event;

@Controller
@RequestMapping("/user")
public class EventController {
	
	public static Vector<Event> allevents;
	
	public EventController() {
		allevents=new EventService().getAllEvents();
	}
	@RequestMapping("/event")
	@ResponseBody
	public String getEvent(HttpServletRequest req){
		String id=req.getParameter("id");
		Event event=new EventService().getEvent(new Long(id));
		if(event==null) return null;
		JSONObject jo=new JSONObject();
		JSONArray ja=new JSONArray();
		try {
			jo.put("id", event.getId());
			jo.put("name", event.getName());
			jo.put("description", event.getDescription());
			jo.put("date", event.getDate());
			jo.put("picnum", event.getPicnum());
			ja.put(jo);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return ja.toString();
	}
	
	@RequestMapping("/allevents")
	@ResponseBody
	public String getAllEvent(HttpServletRequest req){
		String page=req.getParameter("page");
		Vector<Event> events=getMutEvents(new Integer(page));
		JSONObject jo=new JSONObject();
		JSONArray ja=new JSONArray();
		try {
			for (Event event : events) {
				jo.put("id", event.getId());
				jo.put("name", event.getName());
				jo.put("description", event.getDescription());
				jo.put("date", event.getDate());
				jo.put("picnum", event.getPicnum());
				ja.put(jo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return ja.toString();
	}
	private Vector<Event> getMutEvents(int page){
		if(page==0) return allevents;
		Vector<Event> mutevents=new Vector<Event>();
		for (int i = (page-1)*20; i < page*20; i++) {
			if(i>=allevents.size()) break;
			mutevents.add(allevents.get(i));
		}
		return mutevents;
	}
}
