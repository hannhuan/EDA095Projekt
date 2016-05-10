package Util;

import java.io.Serializable;

public class Paket implements Serializable {
	
	private String type;
	private Doc doc;
	
	public Paket(String type, Doc doc ){
		this.type = type;
		this.doc = doc;		
	}
	
	public String getType(){
		return type;
	}
	
	public Doc getDoc(){
		return doc;
	}
}
