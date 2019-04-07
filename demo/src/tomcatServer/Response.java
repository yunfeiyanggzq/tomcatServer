package tomcatServer;


import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;



public class  Response{
	
	private  static String path;
	private  PrintStream  ps;
	private  Socket  client;
	
	Response(Socket  client_) throws IOException{
		this.client=client_;
		ps=new  PrintStream(client.getOutputStream());
	}
	public void  read() throws IOException{

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(path));
			ps.println("HTTP/1.1 200 OK");
			ps.println();
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = fis.read(buf)) != -1) {
				ps.write(buf, 0, len);
				ps.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }finally{
        	if(ps!=null){
        		ps.close();
        	}else if(fis!=null){
        		fis.close();
        	}else if(client!=null){
        		System.out.println("ִ�е�����");
        		client.close();
        	}
        }
	}
	public void read(String  url) throws IOException{
		if(url.equalsIgnoreCase("/")){
			this.path="src\\source\\2.jpg";
		}else{
			this.path="src/source"+url;
			System.out.println("�ͻ��������ļ����ڵ�ַ��"+path);
		}
		File  file=new  File(path);
		if(!file.exists()){
			path="src\\source\\error.html";
		}
		read();
    }
	
	public static  void  main(String[]  args) throws IOException{
	    ServerSocket server = new  ServerSocket(8880);
	    Socket sc = server.accept();
		Response rs = new  Response(sc);
		rs.read();
	}
}