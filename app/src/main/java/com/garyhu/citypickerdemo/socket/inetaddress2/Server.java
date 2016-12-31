package com.garyhu.citypickerdemo.socket.inetaddress2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/**
 * �������
 * @author Garyhu  
 *2016��4��18�� ����2:13:14
 */
public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(9100);
			System.out.println("����������");
			while(true){
				final Socket s = ss.accept();
				Thread t1 = new Thread(){
					public void run(){
						try {
							InputStream in = s.getInputStream();
							byte[] buf = new byte[1024];
							int len = 0;
							String s1 = null;
							while((len=in.read(buf,0,buf.length))!=-1){
								s1 = new String(buf,0,len);
								System.out.println(s1);
							}
							System.out.println(Thread.currentThread().getName()+"������");
						} catch (IOException e) {
//							e.printStackTrace();
							System.out.println(Thread.currentThread().getName()+"������");
						}
					}
				};
				
				Thread t2 = new Thread(){
					public void run(){
						try {
							OutputStream out = s.getOutputStream();
							Scanner scanner = new Scanner(System.in);
							String s1 = null;
							while(true){
								s1 = scanner.next();
								if("exit".equals(s1)){
									System.out.println(Thread.currentThread().getName()+"������");
									s.close();
									break;
								}
								out.write(s1.getBytes());
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
				};
				t1.setName("����˽���");
				t2.setName("����˷���");
				t1.start();
				t2.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
