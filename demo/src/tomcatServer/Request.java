package tomcatServer;


import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;



public class Request {

	private  Socket  client;
	private  String  url;
	private  String  method;
	private  String protocal;
	Request(Socket  client) throws IOException{
		this.client=client;		
		BufferedReader br=new  BufferedReader(new  InputStreamReader(client.getInputStream()));
		String line1=br.readLine();
		System.out.println("客户端提交的基本信息："+line1);
		String[] fields = line1.split(" ");
		method=fields[0];
		url=fields[1];
		protocal=fields[2];
		if(method.equalsIgnoreCase("get")){
			if(url.contains("?")){
				String[] tmp=url.split("[?]");
				url=tmp[0];
				String property=tmp[1];
			
			}
		}else{
			int length = 0;
			while(br.ready()){
				String line = br.readLine();
				if (line.contains("Content-Length")) {
					String[] split2 = line.split(" ");
					length = Integer.parseInt(split2[1]);
				}
				if(line.equals("")){
					break;
				}
			}
			String info = null;
			char[] ch = new char[length];
			br.read(ch, 0, length);
			info = new String(ch, 0, length);
			String[] prams = info.split("&");
			System.out.println("提交到服务器信息为：");
			for(String pram_:prams){
				System.out.println(pram_);
			}
		}
		
	}
	public Socket getClient() {
		return client;
	}
	public void setClient(Socket client) {
		this.client = client;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getProtocal() {
		return protocal;
	}
	public void setProtocal(String protocal) {
		this.protocal = protocal;
	}
	public static  void  main(String[]  args) throws IOException{
	    ServerSocket server = new  ServerSocket(8880);
	    Socket sc = server.accept();
		new  Request(sc);
	}
}
