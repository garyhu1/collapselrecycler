package com.garyhu.citypickerdemo.socket.inettransportfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * �����
 * @author Garyhu
 *2016��4��18�� ����7:04:36
 */
public class FileServer {

	public static void main(String[] args) {
		
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(9100);
			while(true){
				Socket s = ss.accept();
				Thread t = new FileTransportThread(s);
				t.start();
			}
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
