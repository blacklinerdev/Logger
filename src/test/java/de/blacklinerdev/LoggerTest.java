package de.blacklinerdev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {

    @Test
    void setFile() {
        Logger.setFile("/home/leroy/Projekte/Logger/src/main/resources/log.txt", LogLevel.WARNING);
        Logger.log(LogLevel.WARNING, "Should be seen in file" );
        Logger.log(LogLevel.INFO, "Should not be seen in file");
        Logger.log(LogLevel.WARNING, "Should be seen in file appended" );
    }

    @Test
    void log() {
        Logger.log(LogLevel.INFO, "Test1");
    }
}