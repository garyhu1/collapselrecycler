package com.garyhu.citypickerdemo.socket.inettransportfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		InetAddress address = null;
		Socket s = null;
		try {
			address = InetAddress.getLocalHost();
			s = new Socket(address,9200);
			OutputStream out = s.getOutputStream();
			InputStream in = s.getInputStream();
			File file = new File("D:\\��ϰ7\\Java�����ܿ�����.doc");
			FileInputStream fis = new FileInputStream(file);
			byte[] buf = new byte[1024];
			int len = 0;
			//�����ļ���
			String filename = file.getName();
			out.write(filename.getBytes());
			//���շ���˵ķ�����Ϣ
			len = in.read(buf, 0, buf.length);
			System.out.println(new String(buf,0,len));
			//�����ļ�����
			String filelength = file.length() +"";
			out.write(filelength.getBytes());
			//���շ���˵ķ�����Ϣ
			len = in.read(buf, 0, buf.length);
			System.out.println(new String(buf,0,len));
			//�����˷����ļ�����
			while((len=fis.read(buf, 0, buf.length))!=-1){
				out.write(buf, 0, len);
			}
			//���շ���˵ķ�����Ϣ
			len = in.read(buf, 0, buf.length);
			System.out.println(new String(buf,0,len));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
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
