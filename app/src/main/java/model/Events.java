package model;

public class Events {

    public static final String TABLE_NAME2 = "Calendar_Events";
    public static final String COLUMN_ID2 = "event_id";
    public static final String COLUMN_EVENTS = "events";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_MONTH = "month";
    public static final String COLUMN_DAY = "day";

    private int id;
    private String date;


    private int later_year;
    private int later_month;
    private int later_day;
    private String event;

    // Create table SQL query
    public static final String CREATE_TABLE2 =
            "CREATE TABLE " + TABLE_NAME2 + "("
                    + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_EVENTS + " TEXT,"
                    + COLUMN_YEAR + " YEAR,"
                    + COLUMN_MONTH + " MONTH,"
                    + COLUMN_DAY + " DAY"
                    + ")";

    //Empty Constructor
    public Events()
    {

    }

    //Constructor
   public Events(int id, String event, int year, int month, int day){

        this.id = id;
        this.event = event;
        this.later_day = day;
        this.later_month = month;
        this.later_year = year;

   }

   //Getters and Setters

    public int getId(){
        return this.id;
    }

    public String getEvent(){
        return this.event;
    }

    public int  get_later_calendar_year(){
        return this.later_year;
    }

    public int get_later_calendar_month(){
        return this.later_month;
    }

    public int get_later_calendar_day(){
        return this.later_day;
    }

    public void set_calendar_year(int year){
        this.later_year = year;
    }

    public void set_calendar_month(int month){
        this.later_month = month;
    }

    public void set_calendar_day(int day){
        this.later_day = day;
    }


    public void setId(int id ){
        this.id = id;
    }

    public void setEvent(String event){
        this.event = event;
    }





}
