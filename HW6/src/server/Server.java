package server;

import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(9090);

		// getting client request
		// running infinite loop 
		while (true) {
			Socket ns = null;
			
			try {
				// mynewSocket object to receive incoming client requests
				ns = ss.accept();
				
				System.out.println("A new connection identified : " + ns);

				// obtaining input and out streams
				DataInputStream in = new DataInputStream(ns.getInputStream());
				DataOutputStream out = new DataOutputStream(ns.getOutputStream());
				
				System.out.println("Thread assigned");

				Thread myThread = new ClientHandler(ns, in, out);

				// starting
				myThread.start();
				
			} catch (Exception e) {
				ns.close();
				e.printStackTrace();
			}
		}
	}
}