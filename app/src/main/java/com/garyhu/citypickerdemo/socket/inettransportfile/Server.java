package com.garyhu.citypickerdemo.socket.inettransportfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		ServerSocket ss  = null;
		int len = 0;
		long length = 0;//��¼��ȡ�ļ��ĳ��ȵ��ܺ�
		String send = null;
		try {
			ss  = new ServerSocket(9200);
			System.out.println("������������");
			Socket s = ss.accept();
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			byte[] buf = new byte[1024];
			//���տͻ��˵��ļ���
			len = in.read(buf,0,buf.length);
			String filename = new String(buf,0,len);
			File file = new File("D:\\��ϰ8",filename);
			FileOutputStream fos = new FileOutputStream(file);
			//�ظ��ͻ���
			send = "�ļ������յ�";
			out.write(send.getBytes());
			
			//�����ļ��ĳ���
			len = in.read(buf, 0, buf.length);
			long filelength = Long.parseLong(new String(buf,0,len));
			
			//�ظ��ͻ���
			send = "�ļ��������յ�";
			out.write(send.getBytes());
			
			//�����ļ�����
			while(length<filelength&&(len = in.read(buf, 0, buf.length))!=-1){
				fos.write(buf, 0, len);
				length = length + len;
			}
			//�ظ��ͻ���
			send = "�ļ��������յ�";
			out.write(send.getBytes());
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
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
