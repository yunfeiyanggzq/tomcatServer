package tomcatServer;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private static Properties  prop;
	private static int   port;
	private ServerSocket server;
	Server(){
		prop=new Properties();
	    try {
			prop.load(new  FileInputStream(new  File("E:/download/Java-master/tomcatServer3.0/src/source/property.properties")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String porttmp=prop.getProperty("port");
	    this.port=Integer.parseInt(porttmp);
	}
	public void  provideServer() throws IOException, InterruptedException{
		System.out.println("开始监听的端口为："+port);
		this.server = new  ServerSocket(port);
		ExecutorService pool = Executors.newFixedThreadPool(30);
	    while(true){
			  Socket client= server.accept();
			  ServerThread mythread = new  ServerThread(client);
			  pool.execute(mythread);
			 
	    }
	}
	
	
	public  static  void  main(String[]  args) throws IOException, InterruptedException{
	  
		 Server  server=new Server();
		 server.provideServer();
		 
//		 GetPosyTest.sendPost("http://localhost:8882/login.html", "name=123&age=223");
	}
	
}
