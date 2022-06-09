package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class ClientHandler extends Thread {
	private DataInputStream in;
	private DataOutputStream out;
	private Socket ns;
	

	// Constructor
	public ClientHandler(Socket ns, DataInputStream in, DataOutputStream out)
	{
        setNs(ns);
        setIn(in);
        setOut(out);
	}

	@Override
	public void run()
	{
		String receivedString;
		while (true)
		{
			try {
				out.writeUTF("Choose: [Date | Time | Exit]");
				
				// getting answers from client
				receivedString = in.readUTF();
				
				if(receivedString.equals("Exit"))
				{
					System.out.println("Client " + this.ns + " sends exit...");
					System.out.println("Connection closing...");
					this.ns.close();
					System.out.println("Closed");
					break;
				}
				
				switch (receivedString) {
				
					case "Date" :
						out.writeUTF("Date");
						break;
						
					case "Time" :
						out.writeUTF("Time");
						break;
						
					default:
						out.writeUTF("Invalid input");
						break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try
		{
			// closing resources
			this.in.close();
			this.out.close();
			
		}catch(IOException e){
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

    public Socket getNs() {
        return ns;
    }

    public void setNs(Socket ns) {
        this.ns = ns;
    }
}
