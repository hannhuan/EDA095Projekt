package update3;

public class LobbyClass {
	private String name;
	private String content;
	private boolean ifUpdate;
	
	
	public LobbyClass(String name, String content){
		this.name = name;
		this.content = content;
		ifUpdate = false;
	}
	
	//Get the last updatet version. The text edit field will be covered by new content.
	public String update(){
		//Ny notificationFrame 
		return content;
	}
	
	//Check if the user is able to update
	public boolean ifUpdate (){
		return ifUpdate;
	}
	
	
	//Submit the content in the text 
	public void submit (String content){
		this.content = content;
		ifUpdate = true;
	}
	
	public String getName(){
		return name;
	}
	
	
}
