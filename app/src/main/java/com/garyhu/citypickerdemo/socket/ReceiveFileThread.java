package com.garyhu.citypickerdemo.socket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * �����ļ����߳�
 * @author kevin
 * @createdTime 2016��4��19�� ����10:52:56
 */
public class ReceiveFileThread extends Thread{
	
	private Socket s;
	
	public ReceiveFileThread(Socket s){
		this.s = s;
	}

	public void run(){
		try {
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			int len = 0;
			byte[] buf = new byte[1024];
			String receive_msg = "";
			String send_msg = "";
//			�����ļ�����ʼ
			len = in.read(buf);
			String filename = new String(buf,0,len);
//			�����ļ�������
			
//			�����ļ������ճɹ���Ϣ
			send_msg = "�ļ������ճɹ�";
			out.write(send_msg.getBytes());
			
			
			File file = new File("C:\\test\\4",filename);
			if(!file.exists()){
				file.createNewFile();
			}
			
//			�����ļ�����
			len = in.read(buf);
			receive_msg = new String(buf,0,len);
			long length = Long.parseLong(receive_msg);
//			�����ļ����Ƚ���
			
			//�����ļ����Ƚ��ճɹ�����Ϣ
			send_msg = "�ļ����ȷ��ͳɹ�";
			out.write(send_msg.getBytes());
			
			FileOutputStream fos = new FileOutputStream(file);
			
//			int length = 0;
//			ѭ�����ա�ѭ��д��
//			while((len=in.read(buf, 0, buf.length))!=-1){
//				fos.write(buf, 0, len);
//			}
			
			long length1 = 0;//ÿ�ζ�ȡ���ֽ�����
			while(length1<length){
				len = in.read(buf, 0, buf.length);
				fos.write(buf, 0, len);
				length1 = length1 + len;
			}
			
			out.write("�ļ����ͳɹ�".getBytes());//�������ͻ��˷����ļ����ճɹ���Ϣ
			String ip = s.getInetAddress().getHostAddress();
			System.out.println(ip + "���͵��ļ��յ���");
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
