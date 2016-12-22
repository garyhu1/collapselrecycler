package com.garyhu.citypickerdemo.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {

	public static void main(String[] args) {
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket();
			byte[] buf = "��ã����ǿͻ���".getBytes();
			DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(),9002);
			ds.send(dp);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(ds!=null){
				ds.close();
			}
		}
	}
}
