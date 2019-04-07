package tomcatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;





public class ServerThread extends  Thread {

	private  Socket  client;
	ServerThread(Socket  client){
		this.client=client;
	}
	@Override
	public  void  run(){
		try {
			Response  response=new  Response(client);
			Request  request=new  Request(client);
			response.read(request.getUrl());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		                                                                                                                                                                                                                                            
		
	}
	
	public  static  void  main(String[]  args) throws IOException, InterruptedException{
		  ServerSocket server = new  ServerSocket(8881);
		 
		  while(true){
			  Socket sc = server.accept();
			  ServerThread mythread = new  ServerThread(sc);
			  mythread.start();
			  mythread.join();
		  }
		 
	}
}
