package kr.ac.daegu.app.listedit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by daeguunivmac26 on 2015. 7. 8..
 */
class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "alarmdb.db";
    public static String DATABASE_TABLE = "alarm_table";

    public static int DATABASE_VERSION = 1;
    public static final String _ID = "id";
    public static final String TITLE = "title";
    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY = "day";
    public static final String HOUR = "hour";
    public static final String MINUTE = "minute";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATABASE_TABLE + "(" + _ID + "INTEGER PRIMARY KEY,"
            +TITLE + " VARCHAR," + YEAR + "VARCHAR" + MONTH + "VARCHAR" + DAY + "VARCHAR" + HOUR
                + MINUTE + "VARCHAR" +");" );


        String insertMe = "INSERT INTO " + DATABASE_TABLE + "(" + TITLE + "," + YEAR + "," + MONTH
                + "," + DAY + "," + HOUR + "," + MINUTE +")" + "VALUES";


        db.execSQL(insertMe + "('샘플','2015','10','10','12','00');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public void retriveData(ArrayList<Alarm> items){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(DATABASE_TABLE, new String[] { _ID, TITLE, YEAR, MONTH, DAY, HOUR, MINUTE,},
                null, null, null, null, null);
        try {
            items.clear();

            if(c.moveToFirst()){
                do {
                    Alarm alarm = getalarm(c);
                    items.add(alarm);
                } while (c.moveToNext());
            }
        } finally {
            if (c != null)
                c.close();
        }
    }


    private Alarm getalarm(Cursor c) {
        int id = c.getInt(c.getColumnIndex(_ID));
        String title = c.getString(c.getColumnIndex(TITLE));
        int year = c.getInt(c.getColumnIndex(YEAR));
        int month = c.getInt(c.getColumnIndex(MONTH));
        int day = c.getInt(c.getColumnIndex(DAY));
        int hour = c.getInt(c.getColumnIndex(HOUR));
        int minute = c.getInt(c.getColumnIndex(MINUTE));


        Alarm alarm = new Alarm( id, title, year, month, day, hour, minute);
        return alarm;
    }

    public Alarm retrive(int id) {
        Alarm alarm = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(DATABASE_TABLE,
                new String[] { _ID, TITLE, YEAR, MONTH, DAY, HOUR, MINUTE },
                _ID + "=?", new String[] {id+""}, null, null, null);

        if( c.moveToFirst() ) {
            alarm = getalarm(c);
            c.close();
        }
        return alarm;
    }

    public void insert(Alarm alarm) {
        SQLiteDatabase db = getReadableDatabase();
        String insertMe = "INSERT INTO " + DATABASE_TABLE
                + "(" + TITLE + "," + YEAR + "," + MONTH + "," + DAY + ","
                + HOUR + "," + MINUTE + ")" + "VALUES ";
        db.execSQL(insertMe + "('" + alarm.title +"',"
                + "'" + alarm.year + "'," + "'" + alarm.month + "'," + "'" + alarm.day
                + alarm.hour + "'" + alarm.minute + "');");

    }

    public int update(Alarm memo) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, memo.title);
        values.put(YEAR, memo.year);
        values.put(MONTH, memo.month);
        values.put(DAY, memo.day);
        values.put(HOUR, memo.hour);
        values.put(MINUTE, memo.minute);
        return db.update( DATABASE_TABLE, values, "_id=?",
                new String[]{ memo._ID + ""});
    }

    public void delete(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String delete = "DELETE from " + DATABASE_TABLE
                + " WHERE _id=" + id;
        db.execSQL(delete);
    }





}

class Alarm {
    int _ID;
    String title;
    int year;
    int month;
    int day;
    int hour;
    int minute;

    String getAlarm(){
        return title;
    }
    public Alarm(int id, String title, int year, int month, int day, int hour, int minute){
        this._ID = id;
        this.title = title;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }
}
