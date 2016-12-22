package com.garyhu.citypickerdemo.socket.httpexcise;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 用户注册和登陆
 * @author Garyhu
 *2016年4月20日 下午7:11:41
 */
public class Register {

	public static void main(String[] args) throws IOException {
		String address = "http://192.168.84.182:8080/demo2/RegisterServlet";
		String pamse = "username=哈林&password=123456&rpassword=123456&sex=男&age=40";
		register(address,pamse);
		String address1 = "http://192.168.84.182:8080/demo2/LoginServlet";
		String pamse1  = "username=哈林&password=123456";
		login(address1,pamse1);
	}
	
	public static void register(String address,String pamse) throws IOException{
		URL url = new URL(address);
   		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
   		huc.setRequestMethod("POST");
   		huc.setDoOutput(true);
   		OutputStream out = huc.getOutputStream();
   		byte[] buf = pamse.getBytes();
   		out.write(buf);
   		huc.connect();
   		if(huc.getResponseCode()==HttpURLConnection.HTTP_OK){
   			InputStream in = huc.getInputStream();
   			byte[] buf1 = new byte[1024];
   			int len = in.read(buf1, 0, buf1.length);
   			System.out.println(new String(buf1,0,len));
   		}
	}
	
	public static void login(String address,String pamse) throws IOException{
		URL url = new URL(address);
		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		huc.setRequestMethod("POST");
		huc.setDoOutput(true);
		OutputStream out = huc.getOutputStream();
		byte[] buf = pamse.getBytes();
		out.write(buf);
		huc.connect();
		if(huc.getResponseCode()==HttpURLConnection.HTTP_OK){
			byte[] buf1 = new byte[1024];
			InputStream in = huc.getInputStream();
			int len = in.read(buf1, 0, buf1.length);
			System.out.println(new String(buf1,0,len));
		}
	}
}
