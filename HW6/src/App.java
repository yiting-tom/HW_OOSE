import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import server.Server;

public class App {
    private static Logger log = Logger.getLogger(App.class.getName());

    private static void initLogger(String logPath) {
        try {
            FileHandler fh = new FileHandler(logPath);

            log.addHandler(fh);
            log.setLevel(Level.ALL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        // Init logger
        initLogger(App.class.getName());

        // Create a new server
        Server server = new Server(log, 9090);
    }
}
