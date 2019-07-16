package model;

public class Checklist {

    public static final String TABLE_NAME1 = "checklist";

    public static final String COLUMN_ID1 = "id";
    public static final String COLUMN_TASK2 = "task1";
    public static final String COLUMN_TIMESTAMP2 = "timestamp";
    public static final String COLUMN_HOUR = "hour";
    public static final String COLUMN_MINUTE = "minute";

    private int id;
    private String task;
    private String timestamp;
    private int hour;
    private int minute;


    // Create table SQL query
    public static final String CREATE_TABLE1 =
            "CREATE TABLE " + TABLE_NAME1 + "("
                    + COLUMN_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TASK2 + " TEXT,"
                    + COLUMN_TIMESTAMP2 + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                    + COLUMN_HOUR + " HOUR,"
                    + COLUMN_MINUTE + " MINUTE"
                    + ")";

    public Checklist() {
    }

    public Checklist(int id, String task, String timestamp, int hour, int minute) {
        this.id = id;
        this.task = task;
        this.timestamp = timestamp;
        this.hour = hour;
        this.minute = minute;
    }

    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setHour(int h){
        this.hour = h;
    }

    public void setMin(int m){
        this.minute = m;
    }

    public int getHour(){
        return this.hour;
    }
    public int getMinute(){
        return  this.minute;
    }
}
