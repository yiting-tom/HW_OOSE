package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * The LogFormatter class.
 * 
 * provides a custom formatter for the logger.
 */
public class LogFormatter extends Formatter {
    // ANSI escape code
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String BOLD = "\033[1m";

    // Here you can configure the format of the output and 
    // its color by using the ANSI escape codes defined above.

    // format is called for every console log message
    @Override
    public String format(LogRecord record) {
        // This example will print date/time, class, and log level in yellow,
        // followed by the log message and it's parameters in white .
        StringBuilder builder = new StringBuilder();
        switch (record.getLevel().getName()) {
            case "SEVERE":
                builder.append(RED);
                break;
            case "WARNING":
                builder.append(YELLOW);
                break;
            case "INFO":
                builder.append(BLUE);
                break;
            case "FINE":
                builder.append(GREEN);
                break;
            default:
                builder.append(WHITE);
                break;
        }
        builder.append("[");
        builder.append(record.getLevel().getName());
        builder.append("] ");
        builder.append(calcDate(record.getMillis()));
        builder.append(" ");
        builder.append(record.getSourceClassName());
        builder.append(".");
        builder.append(record.getSourceMethodName());
        builder.append(WHITE);
        builder.append(" ");
        builder.append(record.getMessage());
        builder.append(RESET);
        builder.append("\n");
        return builder.toString();
    }

    private String calcDate(long ms) {
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date resultDate = new Date(ms);
        return date_format.format(resultDate);
    }

    public static Logger getConsoleLogger(String name) {
        Logger log = Logger.getLogger(name);
		log.setUseParentHandlers(false);

		ConsoleHandler handler = new ConsoleHandler();

		Formatter formatter = new LogFormatter();
		handler.setFormatter(formatter);

		log.addHandler(handler);

        return log;
    }

    public static Logger getFileHandlerLogger(String name) {
        Logger log = Logger.getLogger(name);

		log.setUseParentHandlers(false);

        try {
            FileHandler handler = new FileHandler(name + ".log");
            Formatter formatter = new SimpleFormatter();
            handler.setFormatter(formatter);
            log.addHandler(handler);

            return log;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
}
