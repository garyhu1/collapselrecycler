package com.garyhu.citypickerdemo.socket.inettransportfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * �ͻ���
 * @author Garyhu
 *2016��4��18�� ����7:24:25
 */
public class FileClient {

	public static void main(String[] args) {
		InetAddress address=null;
		Socket s = null;
		try {
			address = InetAddress.getLocalHost();
	        s = new Socket("192.168.84.175",9100);
			OutputStream out = s.getOutputStream();
			InputStream in = s.getInputStream();
			File file = new File("D:\\��ϰ7\\��ΰΰ.txt");
			byte[] buf = new byte[1024];
			int len = 0;
			//�����˷����ļ���
			String s1 = file.getName();
			out.write(s1.getBytes());
			//���շ���˵ķ���
			in.read(buf,0,buf.length);
			System.out.println(new String(buf));
			//�����˷����ļ���С
			String s2 = String.valueOf(file.length()) ;
			out.write(s2.getBytes());
			//���շ���˵Ļظ�
			in.read(buf, 0, buf.length);
			System.out.println(new String(buf));
			//�����˴����ļ�����
			InputStream input = new FileInputStream(file);
			while((len=input.read(buf,0,buf.length))!=-1){
				out.write(buf,0,len);
			}
			//���շ���˵ķ���
			byte[] b = new byte[1024];
			in.read(b);
			System.out.println(new String(b));
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
