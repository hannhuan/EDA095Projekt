package Util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Doc implements Serializable{
		private String title;
		private byte[] content;
		private byte[] newContent;
		private boolean newest;
		
		public Doc(String title, byte[] content){
			this.title = title;
			this.content = content;
			this.newContent = content;
			this.newest = true;
		}
		public String getTitle(){
			return title;
		}
		public byte[] getContent(){
			return content;
		}
		
		public boolean ifNewest(){
			return newest;
		}
		
		public void setnewContent(byte[] newContent){	
			if(!(Arrays.equals(content, newContent))){
				this.newContent = newContent;
				this.newest = false;
				System.out.println("Fick ny kod!");
			} else {
			System.out.println("senaste kod");
			}
		}
		
		public void refresh(){
			content = newContent;
			newest = true;
		}
}
