package com.garyhu.citypickerdemo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * ������Ϣ���߳�
 * @author kevin
 * @createdTime 2016��4��18�� ����3:33:38
 */
public class ReceiveThread extends Thread{
	
	private Socket s;
	
	public ReceiveThread(Socket s){
		this.s = s;
	}

	public void run(){
		try {
			InputStream in = s.getInputStream();
			byte[] buf = new byte[1024];
			int len = 0;
			String receive_msg = "";
			while((len = in.read(buf))!=-1){
				receive_msg = new String(buf,0,len);
				InetAddress address = s.getInetAddress();
				String ip = address.getHostAddress();
				System.out.println(ip + ":" + receive_msg);
			}
			System.out.println(Thread.currentThread().getName() + ":������");
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println(Thread.currentThread().getName() + ":������");
		}
	}
}
