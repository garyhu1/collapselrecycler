package com.garyhu.citypickerdemo.socket.inetaddress;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
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
//			s = new Socket(address,9100);
			//ʵ�����ͻ��˵��׽��֣���192.168.84.130��Ϊ�ͻ���IP��ַ
			s = new Socket("192.168.84.130",9110);
			//��ȡ�����
			OutputStream out = s.getOutputStream();
			String s1 = "��ã��������ǿͻ��ˡ�";
			//���ַ�������ֽ�����д���������
			out.write(s1.getBytes());
			//��ȡ������
			InputStream in = s.getInputStream();
			//����һ��������
			byte[] buf = new byte[1024];
			//�Ѷ�ȡ�����ݷŵ��������У����õ���ȡ�ĳ���
			int len = in.read(buf,0,buf.length);
			//�ѻ��������ֽڱ�Ϊ�ַ���
			String s2 = new String(buf,0,len);
			System.out.println(s2);
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
