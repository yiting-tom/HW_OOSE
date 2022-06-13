package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

import data.MockDB;
import models.course.Course;
import models.exceptions.CourseException;
import models.users.Admin;
import models.users.Student;
import models.users.User;
import services.AdminService;
import services.StudentService;
import utils.LogFormatter;

/**
 * The client handler.
 * 
 * provides the services for the client to communicate with the server.
 * extends the thread class, and implements the run() method.
 */
class ClientHandler extends Thread {
	// The logger for this class.
	private final static Logger log = LogFormatter.getFileHandlerLogger(Server.class.getName());

	// A client handler running state.
	private boolean isRunning = true;

	// The client socket
	private Socket socket;

	// The input stream from the client socket.
	private DataInputStream in;

	// The output stream from the client socket.
	private DataOutputStream out;

	// The user that is currently logged in.
	private User user;

	// The database.
	private MockDB db;

	// Operation provide a tag for student and course relationship operation.
	private enum Operation {
		doRegister,
		// doUnregister,
		// doPass,
		// doFail,
	};
	

	/**
	 * The constructor of ClientHandler.
	 * 
	 * @param socket
	 * @param in
	 * @param out
	 * @param db
	 */
	public ClientHandler(Socket socket, DataInputStream in, DataOutputStream out, MockDB db) {
        setSocket(socket);
        setIn(in);
        setOut(out);
		setDb(db);
	}

	@Override
	public void run() {
		try {
			// Welcome message.
			responseMsg("Welcome to the register server!");

			// login for next step.
			handleLogin();

			// Get the course, student and operation from the client.
			Course course = selectCourse();
			Student student = selectStudent();
			String operation = selectOperation();

			// handle the operation.
			switch (operation) {
				case "doRegister":
					doRegisterOperation(student, course);
					break;

				// We can add more operations here.
				// like following:
				// case "doUnregister":
				// 	doUnregisterOperation(student, course);
				// 	break;
				// case "doPass":
				// 	doPassOperation(student, course);
				// 	break;
				// case "doFail":
				// 	doFailOperation(student, course);
				// 	break;
				
			}
		
		} catch (IOException | NullPointerException e) {
			log.warning(clientString() + " disconnected caused by: " + e);
		} finally {
			close();
		}
	}

	/**
	 * Select the operation.
	 * 
	 * @return the operation string.
	 * @throws IOException if the client is disconnected.
	 */
	private String selectOperation() throws IOException {
		if (!isRunning) return null;
		// get the operation string list.
		ArrayList<String> operations = new ArrayList<>(){{
			for (Operation opt : Operation.values()) {
				add(opt.name());
			}
		}};

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%6s %s\n", "Select", "Operation"));
        for (int i=0; i<operations.size(); i++) {
            String opt = operations.get(i);
            sb.append(String.format("  [%2d] %s\n", i, opt));
        }

		log.info(operations.toString());

		return selectFromList(
			operations,
			"Select the operation:\n" +
				sb.toString()
		);
	}

	/**
	 * Select a course from the list of courses.
	 * 
	 * @return the selected course.
	 * @throws IOException if the client is disconnected.
	 */
	private Course selectCourse() throws IOException {
		if (!isRunning) return null;
		return selectFromList(
			db.courses,
			"Select a course for next step: \n" +
				Course.getCourseListString(db.courses)
		);
	}

	/**
	 * Select a student from the list.
	 * 
	 * @return the selected student.
	 * @throws IOException if the client is closed.
	 */
	private Student selectStudent() throws IOException {
		if (!isRunning) return null;

		return (user instanceof Admin) ? 
			// get student to take this course.
			selectFromList(
				db.getStudents(),
				"Select a student for next operation: \n" +
					Student.getStudentLiString(db.getStudents())
			) :
			(Student) user;
	}

	/**
	 * Handle the register course request.
	 * 
	 * @param student the student to register.
	 * @param course the course to register.
	 * @throws IOException if any error occurs.
	 */
	private void doRegisterOperation(Student student, Course course) throws IOException, NullPointerException {
		if (!isRunning) return;

		silentResponseMsg("Registering course " + course.getName() + " for student " + student.getName());

		try {
			if (user instanceof Admin) {
				AdminService.takeCourse(student, course);
			} else if (user instanceof Student) {
				StudentService.takeCourse(student, course);
			}
			silentResponseMsg("You have successfully registered\n" + course.getInfoString());

		} catch (CourseException ce) {
			silentResponseMsg(ce.getMessage());
		}
	}

	/**
	 * provide a generic method for client to select the target from a list.
	 * 
	 * @param <T> the type of the target.
	 * @param list the list to select from.
	 * @param prompt the prompt message.
	 * @return the target.
	 * @throws IOException 
	 */
	private <T> T selectFromList(ArrayList<T> list, String prompt) throws IOException {
		if (!isRunning) return null;
		int idx;
		T target;

		// send prompt.
		for (;;) {
			responseMsg(prompt);
			if (!isRunning) return null;

			// get response.
			String rec = receiveMsg();

			// check if the index is valid.
			try {
				// can be parse to int.
				idx = Integer.parseInt(rec);

				// is valid.
				if (idx < 0 || idx >= db.courses.size())
					throw new NumberFormatException();

			} catch (NumberFormatException e) {
				// send error message to client.
				log.warning(e.toString());
				silentResponseMsg("Invalid select");
				continue;
			}

			// get target
			target = list.get(idx);

			silentResponseMsg(
				"You have selected:\n" +
				target.toString().replace(',', '\n'));
			return target;
		}
	}

	/**
	 * Handle the login request.
	 * 
	 * @throws IOException
	 */
	private void handleLogin() {
		if (!isRunning) return;

		String prompt = "Please input your username.";
		responseMsg(prompt);

		for (;;) {
			if (!isRunning) return;

			// receive username from client.
			String username = receiveMsg();

			// check the username is in the database.
			User tmpUser = db.getUserByUsername(username);

			// if the user is not in the database, send error message.
			// and ask the client to input again.
			if (tmpUser == null) {
				responseMsg("username not found!\nusername: ");
				continue;
			}

			// if the user is in the database,
			// ask the client to input the password.
			responseMsg("Please input your password.");
			String password = receiveMsg();

			// check the password.
			if (tmpUser.checkPassword(password)) {
				user = tmpUser;
				silentResponseMsg(
					"Login successfully\n" +
					user.getInfoString());
				break;
			}

			// the password is not correct,
			responseMsg("Invalid password!\n" + prompt);
		}
	}

	/**
	 * Response a message to client.
	 * 
	 * @param msg the message to response.
	 */
	private void responseMsg(String msg) {
		log.fine("Sending message to " + clientString() + ": " + msg);

		// if the handler is not running, then return.
		if (!isRunning) return;

		// try to send message to client.
		try {
			out.writeUTF(msg);
		
		// if the client is disconnected, then stop the handler.
		} catch (IOException e) {
			log.info(clientString() + " be forced disconnected.");
			close();
		}
	}

	/**
	 * Silently response a message to client.
	 * 
	 * @param msg the message to response.
	 */
	private void silentResponseMsg(String msg) {
		responseMsg(msg + "\n[click Enter to continue]");
		receiveMsg();
	}


	/**
	 * Receive the message from client.
	 * 
	 * @return the message.
	 */
	private String receiveMsg() {
		String msg="";
		log.info("Waiting for " + clientString() + " message...");

		// try to receive message.
		try {
			msg = in.readUTF();

		// if the client is disconnected, then stop the handler.
		} catch (IOException e) {
			log.info(clientString() + " be forced disconnected.");
			close();
			return null;
		}

		// show the received message.
		if (msg.equals("Exit")) {
			close();
		}

		log.info(clientString() + " sent: " + msg);
		return msg;
	}

	/**
	 * Close the handler.
	 * 
	 * This function would do following step:
	 * 1. try to send close message for client.
	 * 2. close the socket and input/output stream,
	 * 3. set isRunning to false.
	 */
	private void close() {
		log.info(clientString() + " disconnecting ...");
		try {
			out.writeUTF("handler closed");
		} catch(IOException e) {}

		try {
			// closing resources
			in.close();
			out.flush();
			out.close();
			socket.close();
			setIsRunning(false);
			log.info(clientString() + " disconnected.");
		} catch (IOException e) {
			log.severe("Handler can't be closed");
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the login status.
	 * 
	 * @return true if user is login
	 */
	public boolean isUserLogin() {
		return user != null;
	}

	private String clientString() {
		return "Client " + socket.getInetAddress() + ":" + socket.getPort();
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

	public MockDB getDb() {
		return db;
	}

	public void setDb(MockDB db) {
		this.db = db;
	}

	public void setIsRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public boolean isRunning() {
		return isRunning;
	}
}
