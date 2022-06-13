package server;

import java.io.*;
import java.net.*;
import java.util.logging.Logger;

import data.MockDB;
import utils.LogFormatter;

/**
 * The server class.
 */
public class Server {
	// The logger for this class.
	private final static Logger log = LogFormatter.getFileHandlerLogger(Server.class.getName());

	// The server running state.
	private static boolean isRunning = true;

	// The server socket
	private static ServerSocket socket;

	// The port number of the server.
	private int port = 9090;

	// The database.
	private MockDB db = new MockDB();


	/**
	 * The constructor of Server.
	 * 
	 * @throws IOException if the server socket can not be created.
	 */
	public Server() throws IOException {
		try {
			socket = new ServerSocket(port);
			log.info("Server is running on port " + port);
		} catch (IOException e) {
			log.severe("Server can't be started on port " + port);
			throw e;
		}
	}

	/**
	 * Start the server.
	 * 
	 * @throws IOException if the server socket can not be created.
	 */
	public void serving() throws IOException {
		while (isRunning) {
			try {
				Socket clientSocket = null;
				// getting client request.
				clientSocket = socket.accept();
				log.info("Client " + clientSocket + " connected");

				// obtaining input and out streams from client socket.
				DataInputStream in = new DataInputStream(clientSocket.getInputStream());
				DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

				// starting new thread for client.
				Thread clientHandler = new ClientHandler(clientSocket, in, out, db);
				clientHandler.start();
			} catch (Exception e) {
				log.severe("Server can't be started on port " + port);
				throw new IOException(e);
			}
		}
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Server s = new Server();
		s.serving();
		s.close();
	}

	/**
	 * Close the server.
	 */
	private void close() {
		try {
			setRunning(false);
			socket.close();
			log.fine("Server resources are closed");
		} catch (IOException e) {
			e.printStackTrace();
			log.severe("Server can't be closed");
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public static ServerSocket getSocket() {
		return socket;
	}

	public static void setSocket(ServerSocket socket) {
		Server.socket = socket;
	}

	public static boolean isRunning() {
		return isRunning;
	}

	public static void setRunning(boolean isRunning) {
		Server.isRunning = isRunning;
	}

}