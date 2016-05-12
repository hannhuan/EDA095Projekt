package update4;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lobby {
	private String name;
	private List<Socket> people;
	private HashMap <String, String> classes;
	
	public Lobby(String name){
		this.name = name;
		people = new ArrayList<Socket>();
		classes = new HashMap<String, String>();
	}
	
	public void newClass(String name, String content){
		classes.put(name, content);
	}
	public String getLobbyName(){
		return name;
	}
	
	public List<Socket> getPeople(){
		return people;
	}
	
	public HashMap <String, String> getClasses(){
		return classes;
	}
	
	public boolean addPerson(Socket socket){
		return people.add(socket);
	}
	
	public boolean removePerson(Socket socket){
		return people.remove(socket);
	}
	

}