package com.garyhu.citypickerdemo.socket;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class FileClient {

	public static void main(String[] args) {
		Socket s = null;
		try {
			s = new Socket("192.168.84.181",9001);
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			int len = 0;
			byte[] buf = new byte[1024];
			File srcFile = new File("C:\\test\\Java�����ܿ�����.doc");
//			�����ļ�����ʼ
			String filename = srcFile.getName();
			String send_msg = "";//Ҫ���͵���Ϣ
			String receive_msg = "";//���յ�����Ϣ
			send_msg = filename;
			out.write(send_msg.getBytes());
//			�����ļ�������
			
//			�����ļ������ͳɹ���Ϣ
			len = in.read(buf);
			receive_msg = new String(buf,0,len);
			System.out.println(receive_msg);
//			�����ļ������ͳɹ�����
			
//			�����ļ�����
			long filelen = srcFile.length();
			send_msg = filelen + "";
			out.write(send_msg.getBytes());
//			�����ļ����Ƚ���
			
//			�����ļ����ȷ��ͳɹ�����Ϣ
			len = in.read(buf);
			receive_msg = new String(buf,0,len);
			System.out.println(receive_msg);
//			�����ļ����ȷ��ͳɹ�����Ϣ����
			
//			�����ļ�����
			FileInputStream fis = new FileInputStream(srcFile);
			while((len= fis.read(buf, 0, buf.length))!=-1){
				out.write(buf, 0, len);
			}
//			�����ļ����ݽ���
//			���շ��ͳɹ�����Ϣ
			len = in.read(buf);
//			System.out.println(len);
			receive_msg = new String(buf,0,len);
			System.out.println(receive_msg);
//			���շ��ͳɹ�����Ϣ����
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(s!=null){
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
