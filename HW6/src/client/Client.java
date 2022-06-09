package client;

import java.util.Scanner;
import java.util.logging.Logger;
import java.io.*;
import java.net.*;


public class Client {
    private static boolean connecting;
    private static Logger log = Logger.getLogger("client");
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;

    public Client(InetAddress addr, int port) {
        setConnecting(true);
        try {
            setSocket(new Socket(addr, port));
            setIn(new DataInputStream(socket.getInputStream()));
            setOut(new DataOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
            log.warning("Client init error:" + e);
        }
    }

    public static void main(String[] args) throws IOException
    {
        Scanner scn = new Scanner(System.in);
        Client c = new Client(
            InetAddress.getByName("localhost"),
            9090);

        try {

            // In the following loop, the client and client handle exchange data.
            while (connecting) {
                // get message from server.
                log.info(c.in.readUTF());

                // get message from STDIN.
                String msg = scn.nextLine();

                // send message.
                c.out.writeUTF(msg);

                // check exist
                c.checkExist(msg);
                
                // printing date or time as requested by client
                String rec = c.in.readUTF();
                System.out.println(rec);
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            c.close();
            scn.close();
        }
    }

    private void checkExist(String msg) {
        // Exiting from a while loo should be done when a client gives an exit message.
        if(msg.equals("Exit")) {
            log.info("Connection closing... : " + this.socket);
            this.close();
        }
    }

    private void close() {
        log.info("existing ...");
        try {
            this.in.close();
            this.out.flush();
            this.out.close();
            this.socket.close();
        } catch (IOException e) {
            log.warning("closing client err:" + e);   
        } finally {
            System.exit(0);
        }
    }

    public static boolean isConnecting() {
        return connecting;
    }

    public static void setConnecting(boolean connecting) {
        Client.connecting = connecting;
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
}
