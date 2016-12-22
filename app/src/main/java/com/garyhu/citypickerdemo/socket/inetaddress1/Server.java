package com.garyhu.citypickerdemo.socket.inetaddress1;

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
		ServerSocket ss = null;
		try {
			//ʵ���������׽���
			ss = new ServerSocket(9100);
			
			System.out.println("����������");
			//���ܿͻ����׽���
			Socket s = ss.accept();
			//ʵ����һ��������
			byte[] buf = new byte[1024];
			//���������
			InputStream in = s.getInputStream();
			String ss1 = null;
			//��������
			OutputStream out = s.getOutputStream();
			Scanner scanner = new Scanner(System.in);
			int len = 0 ;
			while((len = in.read(buf,0,buf.length))!=-1){
				//��������Ĵ���Ϣ
				System.out.println(new String(buf,0,len));
				ss1 = scanner.next();
				//���ַ�������ֽ�����д���������
				out.write(ss1.getBytes());
				
			}
		} catch (IOException e) {
			System.out.println("���Ѿ�����������������");
//			e.printStackTrace();
		}finally{
			//�رշ����׽���
			if(ss!=null){
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
