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
	
	public String update(){
		return content;
	}
	
	public boolean ifUpdate (){
		return ifUpdate;
	}

	public void submit (String content){
		this.content = content;
		ifUpdate = true;
	}
	
	public String getName(){
		return name;
	}
	
	
}
