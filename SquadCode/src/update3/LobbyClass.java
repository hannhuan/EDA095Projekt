package update3;

public class LobbyClass {
	private String content;
	private boolean ifUpdate;
	
	
	public LobbyClass(){
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
	
	
}
