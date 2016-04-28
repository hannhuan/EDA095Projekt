

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ServerListenerP extends Thread {
	private BufferedReader br;

	public ServerListenerP(InputStream in) {
		br = new BufferedReader(new InputStreamReader(in));
	}

	public void run() {
		System.out.println("You are now connected to the server!");
		for(;;) {	
			try {	
				String line;
				while(true) {
					line= br.readLine();
					if(line!=null) {
						System.out.println(line);
						
					}
				}
			} catch (IOException e) {
				return;
			}
		}
	}
}
