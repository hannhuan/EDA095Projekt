package Util;

import java.io.Serializable;

public class Paket implements Serializable {
	
	private String type;
	private byte[] contentB;
	
	public Paket(String type, byte[] contentB ){
		this.type = type;
		this.contentB = contentB;		
	}
	
	public String getType(){
		return type;
	}
	
	public byte[] getContent(){
		return contentB;
	}
}
