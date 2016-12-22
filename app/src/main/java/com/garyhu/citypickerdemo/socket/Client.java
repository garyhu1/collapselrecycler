package com.garyhu.citypickerdemo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * �ͻ���
 * @author kevin
 * @createdTime 2016��4��18�� ����10:30:59
 */
public class Client {

	public static void main(String[] args) {
		Socket s = null;
		try {
			InetAddress address = InetAddress.getLocalHost();
			InetAddress address1 = InetAddress.getByName("192.168.84.181");
//			s = new Socket(address1,9000);
			s = new Socket("192.168.84.181",9000);
//			InputStream in = s.getInputStream();
//			OutputStream out = s.getOutputStream();
//			String send_msg = "��ã������������ǿͻ���";
//			out.write(send_msg.getBytes());//�����˷�����Ϣ
//			byte[] buf = new byte[1024];
//			int len = in.read(buf);//���շ���˷��͹�������Ϣ
//			String receive_msg = new String(buf,0,len);
//			System.out.println(receive_msg);
			
//			Scanner scanner = new Scanner(System.in);
//			String send_msg = "";//���͵�����
//			String receive_msg = "";//���յ���Ϣ
//			byte[] buf = new byte[1024];
//			int len = 0;
//			while(true){
//				send_msg = scanner.next();
//				if("exit".equals(send_msg)){
//					break;
//				}
//				out.write(send_msg.getBytes());
//				len = in.read(buf);
//				receive_msg = new String(buf,0,len);
//				System.out.println(receive_msg);
//			}
			
			SendThread t1 = new SendThread(s);
			ReceiveThread t2 = new ReceiveThread(s);
			t1.setName("�ͻ��˷�����Ϣ�߳�");
			t2.setName("�ͻ��˽�����Ϣ�߳�");
			t1.start();
			t2.start();
			
			
//			byte[] buf = new byte[1024];
//			int len = in.read(buf);//���շ���˷��͹�������Ϣ
//			String receive_msg = new String(buf,0,len);
//			System.out.println(receive_msg);
//			
//			
//			String send_msg = "��ã������������ǿͻ���";
//			out.write(send_msg.getBytes());//�����˷�����Ϣ
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
//			if(s!=null){
//				try {
//					s.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
		}
		
	}
}
