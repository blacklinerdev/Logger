package de.blacklinerdev;

public enum LogLevel {

    INFO(1), DEBUG(2), WARNING(3), ERROR(4);

    LogLevel(int num){
        level = num;
    }

    private int level;

    /**gets integer representation of LogLevel
     * @return integer representation
     */
    public int getValue(){
        return level;
    }
}
