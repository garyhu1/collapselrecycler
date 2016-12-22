package com.garyhu.citypickerdemo.socket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * �ļ���������
 * @author kevin
 * @createdTime 2016��4��18�� ����5:48:20
 */
public class FileServer {

	public static void main(String[] args) {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(9001);
			System.out.println("�ļ����շ�����������");
			while(true){
				Socket s = ss.accept();//ѭ�������û�������
				Thread t = new ReceiveFileThread(s);
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
