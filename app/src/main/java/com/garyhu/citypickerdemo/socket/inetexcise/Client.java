/**
 * 1���ͻ��������˷���1-128�����֣�������ҵ���Щ������ASCII���з��ţ�
�����͸��ͻ���
 */
package com.garyhu.citypickerdemo.socket.inetexcise;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		Socket s = null;
		try {
			s = new Socket("192.168.84.175",9100);
//			Scanner scanner = new Scanner(System.in);
			OutputStream out = s.getOutputStream();
			InputStream in = s.getInputStream();
			int len = 0;
			byte[] buf = new byte[1024];
			for(int i = 1;i <= 128;i++){
				String str = String.valueOf(i);
				out.write(str.getBytes());
				len = in.read(buf,0,buf.length);
				String s1 = new String(buf,0,len);
				char[] c = s1.toCharArray();
				for(char b:c){
					System.out.println(b);
				}
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
