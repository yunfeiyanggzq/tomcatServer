package tomcatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class GetPosyTest {

	public  static  String  sendGet(String  url,String  pram) throws IOException{
		URL realurl = new  URL(url);
		URLConnection conn = realurl.openConnection();
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1"+ "; SV1)");
		conn.connect();
		Map<String, List<String>> map = conn.getHeaderFields();
		for(String i:map.keySet()){
			System.out.println("状态为"+map.get(i));
		}
		InputStream input = conn.getInputStream();
		BufferedReader in = new  BufferedReader(new  InputStreamReader(input,"utf-8"));
		String line;

		while((line=in.readLine())!=null){
			System.out.println(line);
		}
		return null;
	}
	
	
	public  static  String  sendPost(String  url,String  pram) throws IOException{
		URL realurl = new  URL(url);
		URLConnection conn = realurl.openConnection();
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1"+ "; SV1)");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		PrintWriter  out=new  PrintWriter(conn.getOutputStream());
		
		out.print(pram);
		out.flush();
		
		

		InputStream input = conn.getInputStream();
		Map<String, List<String>> map = conn.getHeaderFields();
		for(String i:map.keySet()){
			System.out.println("状态为"+map.get(i));
		}
		BufferedReader in = new  BufferedReader(new  InputStreamReader(input,"utf-8"));
		String line;

		while((line=in.readLine())!=null){
			System.out.println(line);
		}
	
		return null;
	}
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();    //获取开始时间
		for(int  i=0;i<10000;i++){
		//发送get请求
		String s = GetPosyTest.sendGet("http://localhost:8882/login.html", null);
		//发送post请求
		String s1 = GetPosyTest.sendPost("http://localhost:8882/login.html", "name=123&age=223");
		}
		long endTime = System.currentTimeMillis();    //获取结束时间
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
//		程序运行时间：17353ms  10
//		程序运行时间：15754ms  100
//		程序运行时间：13996ms  20
//		程序运行时间：11683ms  30
	}
}

