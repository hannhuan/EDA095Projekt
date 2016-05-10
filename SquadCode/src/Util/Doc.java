package Util;

import java.io.Serializable;

public class Doc implements Serializable{
		private String title;
		private byte[] content;
		
		public Doc(String title, byte[] content){
			this.title = title;
			this.content = content;
		}
		public String getTitle(){
			return title;
		}
		public byte[] getContent(){
			return content;
		}
}
