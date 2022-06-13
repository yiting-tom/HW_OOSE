package client;

import java.util.logging.Logger;

import utils.LogFormatter;

import java.io.*;
import java.net.*;


/**
 * The client class.
 */
public class Client {
    // The logger for this class.
    private final static Logger log = LogFormatter.getConsoleLogger(Client.class.getName());

    // The client running state.
    private boolean isRunning= true;

    // The client socket
    private Socket socket;
    
    // The input stream from the client socket.
    private DataInputStream in;

    // The output stream from the client socket.
    private DataOutputStream out;


    /**
     * The constructor of Client.
     * would handle the IOException.
     * 
     * @param addr The address of the server.
     * @param port The port of the server.
     */
    public Client(String addr, int port) {
        try {
            setSocket(new Socket(addr, port));
            setIn(new DataInputStream(socket.getInputStream()));
            setOut(new DataOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
            log.warning("Client init error:" + e);
        }
    }

    /**
     * The main method of Client.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Create a client.
        Client c = new Client("localhost", 9090);

        // try to start the client.
        try {
            c.start();

        // if occur any IOException, print the error message
        // and exit with status code -1.
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Start the client.
     * 
     * @throws IOException If the client occur any IOException.
     */
    public void start() throws IOException {
        String rec; // Received string
        String res; // responseMsg string

        try {
            rec = receiveMsg();
            // In the following loop, the client and client handle exchange data.
            while (isRunning()) {
                // get message from server.
                rec = receiveMsg();

                // get response message from user input.
                // here we use a simple console input.
                // you can use a GUI input or others.
                res = getUserInput(rec);

                // send message to server.
                responseMsg(res);
            }

        // close the client.
        } finally {
            close();
        }
    }

    /**
     * Receive the message from the server.
     * 
     * @return the message string.
     */
    private String receiveMsg() {
        // check the client is running.
        if (!isRunning()) return null;

        try {
            // try to receive message.
            String rec = in.readUTF();

            // print the received message.
            log.info("From server: " + rec);

            // check if the message is "Exit".
            if (rec.equals("handler closed")) {
                // close the client.
                close();
                // exit the method.
                return null;
            }

            // return the received message.
            return rec;

        // if receive message error, then close the client.
        } catch (IOException e) {
            log.warning("Receive message error:" + e);
            close();
        }

        return null;
    }

    /**
     * Send the message to the server.
     * 
     * @param msg the message to send.
     */
    private void responseMsg(String msg) {
        // check the client is running.
        if (!isRunning()) return;

        // try to send message.
        try {
            out.writeUTF(msg);

        // if send message error, then close the client.
        } catch (IOException e) {
            log.warning("Send message error:" + e);
        }
    }

    /**
     * Get the user input by console.
     * 
     * @param rec the received message from server.
     * @return the message.
     */
    private String getUserInput(String rec) {
        // check if the client is running.
        if (!isRunning()) return null;

        // Create a console to get user input.
        Console con = System.console();

        // if password is needed.
        if (rec.equals("Please input your password.")) {
            return String.valueOf(con.readPassword("> "));
        }

        // get user input.
        String msg = con.readLine("> ");

        // if the user input is "Exit", then close the client.
        if (msg.equals("Exit")) {
            close();
        }

        return msg;
    }

	/**
	 * Close the client.
	 * 
	 * This function would do following step:
	 * 1. try to send close message for server.
	 * 2. close the socket and input/output stream,
	 * 3. set isRunning to false.
	 */
	private void close() {
        // check the client is running.
        if (!isRunning()) return;

		log.info("Disconnecting ...");

        // try to send close message for server.
        try {
            out.writeUTF("Exit");
        } catch(IOException e) {}

		try {
			// closing resources
			in.close();
			out.flush();
			out.close();
			socket.close();
			setIsRunning(false);
			log.info("Disconnect success");
		} catch (IOException e) {
			log.severe("Client can't be closed");
			e.printStackTrace();
		}
	}

    public DataInputStream getIn() {
        return in;
    }

    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
