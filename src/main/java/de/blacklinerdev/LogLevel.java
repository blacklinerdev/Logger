package de.blacklinerdev;

public enum LogLevel {

    INFO(1), DEBUG(2), WARNING(3), ERROR(4);

    LogLevel(int num){
        level = num;
    }

    private int level;

    public int getValue(){
        return level;
    }
}
