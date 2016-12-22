package com.garyhu.citypickerdemo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * �����
 * @author kevin
 * @createdTime 2016��4��18�� ����10:41:49
 */
public class Server {

	public static void main(String[] args) {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(9000);
			System.out.println("������������");
			while(true){
				Socket s = ss.accept();//��������
				SendThread t1 = new SendThread(s);
				ReceiveThread t2 = new ReceiveThread(s);
				t1.setName("����˷�����Ϣ�߳�");
				t2.setName("����˽�����Ϣ�߳�");
				t1.start();
				t2.start();
			}
			
//			InputStream in = s.getInputStream();
//			OutputStream out = s.getOutputStream();
//			Scanner scanner = new Scanner(System.in);
//			byte[] buf = new byte[1024];
//			int len = 0;
//			String receive_msg = "";//����˽��յ�����
//			String send_msg = "";//Ҫ���͵�����
////			int len = in.read(buf);//���տͻ��˷��͹�������Ϣ
//			
////			String receive_msg = new String(buf,0,len);
////			System.out.println("������յ���" + receive_msg);
////			String send_msg = "�ͻ��ˣ���ã����Ƿ��������㷢����Ϣ�����յ�";
////			out.write(send_msg.getBytes());//��ͻ��˷�����Ϣ
//			while((len=in.read(buf))!=-1){
////				len = in.read(buf);
////				if(len==-1){
////					break;
////				}
//				receive_msg = new String(buf,0,len);
//				System.out.println(receive_msg);
//				send_msg = scanner.next();
//				out.write(send_msg.getBytes());
//			}
			
			
			
		} catch (IOException e) {
//			System.out.println("�Է�������");
//			e.printStackTrace();
		}finally{
//			if(ss!=null){
//				try {
//					ss.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
		}
	}
}
