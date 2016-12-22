package com.garyhu.citypickerdemo.socket.inetaddress1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
/**
 * �ͻ�����
 * @author Garyhu
 *2016��4��18�� ����2:13:40
 */
public class Client {

	public static void main(String[] args) {
		InetAddress address = null;//����InetAddress����
		Socket s = null;//����Socket����
		try {
			//�õ�������IP��ַ
//			address = InetAddress.getLocalHost();
			//�õ�������IP��ַ
			address = InetAddress.getByName("192.168.84.175");
			//ʵ�����ͻ��˵��׽��֣�����9100Ϊ�ͻ��˿ں�
			s = new Socket(address,9100);
			//ʵ�����ͻ��˵��׽��֣���192.168.84.130��Ϊ�ͻ���IP��ַ
//			s = new Socket("192.168.84.130",9110);
			//��ȡ�����
			OutputStream out = s.getOutputStream();
			//��ȡ������
			InputStream in = s.getInputStream();
			//����һ��������
			byte[] buf = new byte[1024];
			String s2 = null;
			String s1 = null;
			Scanner scanner = new Scanner(System.in);
			int len = 0;
			while(true){
				s1 = scanner.next();
				if("exit".equals(s1)){
					break;
				}
				//���ַ�������ֽ�����д���������
				out.write(s1.getBytes());
				//�Ѷ�ȡ�����ݷŵ��������У����õ���ȡ�ĳ���
				len = in.read(buf,0,buf.length);
				//�ѻ��������ֽڱ�Ϊ�ַ���
				s2 = new String(buf,0,len);
				System.out.println(s2);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(s!=null){//�ر��׽���
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
