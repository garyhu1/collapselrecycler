package com.garyhu.citypickerdemo.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * ������Ϣ���߳�
 * @author kevin
 * @createdTime 2016��4��18�� ����3:28:22
 */
public class SendThread extends Thread{
	
	private Socket s;
	
	public SendThread(Socket s){
		this.s = s;
	}

	public void run(){
		OutputStream out = null;
		try {
			out = s.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		String send_msg = "";
		while(true){
			send_msg = scanner.next();
			if("exit".equals(send_msg)){
				try {
					System.out.println(Thread.currentThread().getName() + ":������");
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}//����ʱ���ر��׽���
				break;
			}
			try {
				out.write(send_msg.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
