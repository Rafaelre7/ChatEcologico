package TESTE;

import java.net.InetAddress;

public class PegarIp {
	public static void main(String[] args) {
		 
		try {
	            System.out.println(InetAddress.getLocalHost().getHostAddress());
	        } catch (Exception e) {
	        }
	}
}
//172.16.0.197
