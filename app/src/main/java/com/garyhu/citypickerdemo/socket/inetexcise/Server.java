package com.garyhu.citypickerdemo.socket.inetexcise;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * �����
 * @author Garyhu
 *2016��4��18�� ����9:42:38
 */
public class Server {

	public static void main(String[] args) {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(9100);
			Socket s = ss.accept();
			OutputStream out = s.getOutputStream();
			InputStream in = s.getInputStream();
			byte[] buf = new byte[1024];
			int len = 0;
			for(int i = 1;i <= 128;i++){
				len = in.read(buf,0,buf.length);
				String str = new String(buf,0,len);
				int n = Integer.valueOf(str);
				char c = (char) n;
//				System.out.println(c);
				String str1 = String.valueOf(c);
				out.write(str1.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
