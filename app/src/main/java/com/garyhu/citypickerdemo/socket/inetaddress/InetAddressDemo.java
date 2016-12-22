package com.garyhu.citypickerdemo.socket.inetaddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ������ַ������
 * @author Garyhu
 *2016��4��18�� ����9:59:10
 */
public class InetAddressDemo {

	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getLocalHost();
			System.out.println(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		InetAddress address1;
		try {
			address1 = InetAddress.getByName("192.168.84.175");
			System.out.println(address1);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
