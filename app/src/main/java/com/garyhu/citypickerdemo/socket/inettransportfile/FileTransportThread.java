package com.garyhu.citypickerdemo.socket.inettransportfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileTransportThread extends Thread{

	private Socket s;
	
	public FileTransportThread(Socket s){
		this.s = s;
	}
	
	public void run(){
		try {
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			byte[] buf = new byte[1024];
			int len = 0;//��¼ÿ�ζ�ȡ�ĳ���
			//��ȡ�ļ���
			len = in.read(buf,0,buf.length);
			String filename = new String(buf,0,len);
			File file = new File("D:\\��ϰ8",filename);
			if(!file.exists()){
				file.createNewFile();
			}
			//�ظ��ͻ���
			String s1 = "�ļ����Ѿ��յ�";
			out.write(s1.getBytes());
			//��ȡ�ļ���С
			len = in.read(buf,0,buf.length);
			String s3 = new String(buf,0,len);
			long filelength = Long.valueOf(s3);
			//�ظ��ͻ���
			String s5 = "�ļ���С�Ѿ��յ�";
			out.write(s5.getBytes());
			//��ʼ�����ļ����ݵĴ���
			OutputStream output = new FileOutputStream(file);
			long length = 0;//��¼��ȡ�ļ��ĳ���
			System.out.println(filelength);
			while(length!=filelength&&(len=in.read(buf,0,buf.length))!=-1){
//				System.out.println(new String(buf));
				output.write(buf,0,len);
				length = length+len;
			}
			//�ظ��ͻ���
			String s4 = "�ļ������Ѿ��յ�";
			out.write(s4.getBytes());
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
