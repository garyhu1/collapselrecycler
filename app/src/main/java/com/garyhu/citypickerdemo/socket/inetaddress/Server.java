package com.garyhu.citypickerdemo.socket.inetaddress;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
			ss = new ServerSocket(9200);
			
			System.out.println("����������");
			//���ܿͻ����׽���
			Socket s = ss.accept();
			//ʵ����һ��������
			byte[] buf = new byte[1024];
			//���������
			InputStream in = s.getInputStream();
			//����Ϣ����������
			int len = in.read(buf,0,buf.length);
			//��������Ĵ���Ϣ
			System.out.println(new String(buf,0,len));
			String ss1 = "�ڰ������Ƿ���������ô����";
			//��������
			OutputStream out = s.getOutputStream();
			//���ַ�������ֽ�����д���������
			out.write(ss1.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
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
