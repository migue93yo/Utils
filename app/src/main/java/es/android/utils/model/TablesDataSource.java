package es.android.utils.model;

/**
 * Created by miguelconde on 11/01/17.
 */

public class TablesDataSource {

    //TABLE DIRECTORIES
    public static String TABLE_DOCUMENTS_NAME = "directories";
    public static String DOCUMENTS_ID = "id";
    public static String DOCUMENTS_RUTE_ORIGIN = "rute_origin";
    public static String DOCUMENTS_RUTE_DESTINY = "rute_destiny";
    public static String DOCUMENTS_ACTIVE = "active";
    public static String DOCUMENTS_ID_DOW = "id_dow";

    //TABLE DAYS_OF_WEEK
    public static String TABLE_DAYS_OF_WEEK_NAME = "days_of_week";
    public static String DAYS_OF_WEEK_ID = "id";
    public static String DAYS_OF_WEEK_MONDAY = "monday";
    public static String DAYS_OF_WEEK_TUESDAY = "tuesday";
    public static String DAYS_OF_WEEK_WEDNESDAY = "wednesday";
    public static String DAYS_OF_WEEK_THURSDAY = "thursday";
    public static String DAYS_OF_WEEK_FRIDAY = "friday";
    public static String DAYS_OF_WEEK_SATURDAY = "saturday";
    public static String DAYS_OF_WEEK_SUNDAY = "sunday";

    protected static String TABLE_DOCUMENTS = "CREATE TABLE " + TABLE_DOCUMENTS_NAME + " (" +
            DOCUMENTS_ID  + " integer," +
            DOCUMENTS_RUTE_ORIGIN + " char(80) NOT NULL," +
            DOCUMENTS_RUTE_DESTINY + " char(80) NOT NULL," +
            DOCUMENTS_ACTIVE + " tinyint(1) DEFAULT 0 NOT NULL," +
            DOCUMENTS_ID_DOW + " integer NOT NULL," +
            "CONSTRAINT TRABAJADOR PRIMARY KEY (id)" +
            "CONSTRAINT FK_DAYS_OF_WEEK FOREIGN KEY (" + DOCUMENTS_ID_DOW +") REFERENCES Empresa (" + DAYS_OF_WEEK_ID + ")" +
            ")";

    protected static String TABLE_DAYS_OF_WEEK = "CREATE TABLE " + TABLE_DAYS_OF_WEEK_NAME + " (" +
            DAYS_OF_WEEK_ID + " integer," +
            DAYS_OF_WEEK_MONDAY + " tinyint(1) DEFAULT 0 NOT NULL," +
            DAYS_OF_WEEK_TUESDAY + " tinyint(1) DEFAULT 0 NOT NULL," +
            DAYS_OF_WEEK_WEDNESDAY + " tinyint(1) DEFAULT 0 NOT NULL," +
            DAYS_OF_WEEK_THURSDAY + " tinyint(1) DEFAULT 0 NOT NULL," +
            DAYS_OF_WEEK_FRIDAY + " tinyint(1) DEFAULT 0 NOT NULL," +
            DAYS_OF_WEEK_SATURDAY + " tinyint(1) DEFAULT 0 NOT NULL," +
            DAYS_OF_WEEK_SUNDAY + " tinyint(1) DEFAULT 0 NOT NULL," +
            "CONSTRAINT TRABAJADOR PRIMARY KEY (" + DAYS_OF_WEEK_ID + ")" +
            ")";

}
