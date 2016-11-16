package service;

import java.util.Vector;

import dao.EventDM;
import entity.Event;

public class EventService {
	public boolean addEvent(Event event){
		EventDM eventDM= new EventDM();
		boolean res=eventDM.add(event);
		eventDM.close();
		return res;
	}
	public boolean delEvent(long id){
		EventDM eventDM= new EventDM();
		boolean res=eventDM.delete(id);
		eventDM.close();
		return res;
	}
	public boolean updateEvent(Event event){
		EventDM eventDM= new EventDM();
		boolean res=eventDM.update(event);
		eventDM.close();
		return res;
	}
	public Event getEvent(long id){
		EventDM eventDM= new EventDM();
		Event res=(Event) eventDM.get(id);
		eventDM.close();
		return res;
	}
	public Vector<Event>  getAllEvents(){
		EventDM eventDM= new EventDM();
		Vector<Event> res=eventDM.getAllEvents();
		eventDM.close();
		return res;
	}
}
