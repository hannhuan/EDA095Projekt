package ClientSide;

import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Util.Doc;
import Util.Paket;

public class ServerManager {
	
	private ObjectOutputStream oos;
	private String username;
	private Map<String, Doc> classes;
	
	
	public ServerManager(ObjectOutputStream oos, String username, HashMap<String, Doc> classes){
		this.oos = oos;
		this.username = username;
		this.classes = classes;
	}

	/** Edits the CodeArea */
	public void editCode(String title, String code) {
		classes.put(title, new Doc(title, code.getBytes()));
	}

	/** Sends a chatMessage */
	public void sendChat(String msg) {
		try {
			oos.writeObject(new Paket("chat", new Doc(username, msg.getBytes())));
			//writeMessage.setText("");
		} catch (Exception e) {
		}
	}
	
	public void serverWelcomeChat(){
		try {
			oos.writeObject(new Paket("chat", new Doc("/SERVER", (" User " + username + " connected").getBytes())));
			//writeMessage.setText("");
		} catch (Exception e) {
		}
	}
	
	public void serverSendChat(String msg){
		try {
			oos.writeObject(new Paket("chat", new Doc("/SERVER", (" " + msg).getBytes())));
			//writeMessage.setText("");
		} catch (Exception e) {
		}
	}

	/** Adds a new class to the projectList */
	public boolean addNewClass(Doc doc) {
		if(!(classes.containsKey(doc.getTitle()))){
			classes.put(doc.getTitle(), new Doc(doc.getTitle(), "".getBytes()));
			//listModel.addElement(doc.getTitle());
			return true;
		} else {
		return false;
		}
	}

	/** Removes the selected class from the projectList */
	public void removeClass(Doc doc) {
		classes.remove(doc.getTitle());
	}

	/**receives the Document doc from Server*/
	public void receiveDoc(Doc doc) {
		if(classes.containsKey(doc.getTitle())){
			classes.get(doc.getTitle()).setNewContent(doc.getContent());
		} else {
			addNewClass(doc);
		}
	}
	/**Upon choosing to refresh*/
	public void refreshDoc(String klass){
		String classTitle = klass;
		classes.get(classTitle).refresh();		
	}
	
	public boolean allowedToSubmit(String klass){
		return classes.get(klass).ifNewest();
	}
	
	public void saveSubmit(String klass, String content){
		if(allowedToSubmit(klass)){
		classes.get(klass).setNewContent(content.getBytes());
		classes.get(klass).refresh();		
		}
	}
	
	public void sendPaket(Paket pac){
		try {
			oos.writeObject(pac);
		} catch (Exception e) {
		}
	}
	
	public Doc getClass(String title){
		return classes.get(title);
	}
	
	public Set<String> getKeySet(){
		return classes.keySet();
	}
}
