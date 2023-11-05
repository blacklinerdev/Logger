package de.blacklinerdev;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private File logfile;
    private LogLevel fileLevel;
    private static Logger logger;

    /**sets the path of the logfile and the minimum severity to write to the logfile
     * @param path location of logfile
     * @param level minimum severity of messages to be written to the logfile
     * @return true, only if logfile could be set and is ready. If return false, path and level are reset to null
     */
    public static boolean setFile(String path, LogLevel level){
        Logger logger = getInstance();
        logger.logfile = new File(path);
        if (!logger.logfile.exists()) {
            try {
                boolean created = logger.logfile.createNewFile();
                if (!created) {
                    throw new IOException();
                }
            }catch(IOException ioe){
                logger.logfile = null;
                return false;
            }
        }
        logger.fileLevel = level;
        return true;
    }

    private static Logger getInstance(){
        if(logger == null) logger = new Logger();
        return logger;
    }

    /** logs a message with the given severity to outputstream.
     * If logfile and fileLevel was set before with @see setFile(),log message is also logged to the logfile only if
     * severity is equal or higher than the loglevel for the log file
     * @param level sets the severity of message
     * @param message sets the string message to tog
     */
    public static synchronized void log(LogLevel level, String message){
        Logger logger = getInstance();
        PrintStream os = System.out;
        if(level == LogLevel.ERROR) os = System.err;
        String logStr = logger.getLogStr(message);
        os.println(logStr);
        if(logger.fileLevel != null && level.getValue() >= logger.fileLevel.getValue()){
            logger.log2File(logStr);
        }

    }

    private String getLogStr(String message){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return LocalDateTime.now().format(dtf) + ": " + message;
    }

    private boolean log2File(String logStr) {
        try {
            if (logfile == null) return false;
            if (!logfile.canWrite()) {
                return false;
            }
            FileWriter fw = new FileWriter(logfile, true);
            fw.append(logStr).append("\n");
            fw.flush();
            return true;
        }catch(IOException ioe) {return false;}
    }
}