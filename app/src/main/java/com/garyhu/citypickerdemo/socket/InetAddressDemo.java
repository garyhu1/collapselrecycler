package com.garyhu.citypickerdemo.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {

	public static void main(String[] args) {
		try {
			InetAddress address1 = InetAddress.getLocalHost();
			System.out.println(address1);
			InetAddress address2 = InetAddress.getByName("XIAOKAIBO7F3B");
			System.out.println(address2);
//			InetAddress address3 = InetAddress.getByName("www.baidu.com");
//			System.out.println(address3);
			InetAddress address4 = InetAddress.getByName("192.168.84.181");
			System.out.println(address4);
			
			String hostAddress = address1.getHostAddress();
			System.out.println(hostAddress);
			String hostName = address1.getHostName();
			System.out.println(hostName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
