package es.android.utils.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import es.android.utils.domain.DaysOfWeek;

/**
 * Created by miguelconde on 11/01/17.
 */

public class DaysOfWeekTable {

    private TablesReader openHelper;
    private SQLiteDatabase database;

    public DaysOfWeekTable(Context context) {
        openHelper = new TablesReader(context);
        database = openHelper.getWritableDatabase();
    }

    public long insert(DaysOfWeek dow){
        ContentValues values = new ContentValues();

        values.put(TablesDataSource.DAYS_OF_WEEK_MONDAY, dow.isMonday());
        values.put(TablesDataSource.DAYS_OF_WEEK_TUESDAY, dow.isTuesday());
        values.put(TablesDataSource.DAYS_OF_WEEK_WEDNESDAY, dow.isWednesday());
        values.put(TablesDataSource.DAYS_OF_WEEK_THURSDAY, dow.isThursday());
        values.put(TablesDataSource.DAYS_OF_WEEK_FRIDAY, dow.isFriday());
        values.put(TablesDataSource.DAYS_OF_WEEK_SATURDAY, dow.isSaturday());
        values.put(TablesDataSource.DAYS_OF_WEEK_SUNDAY, dow.isSunday());

        return database.insert(TablesDataSource.TABLE_DAYS_OF_WEEK_NAME, null, values);
    }

    public boolean update(DaysOfWeek dow){
        ContentValues values = new ContentValues();

        values.put(TablesDataSource.DAYS_OF_WEEK_MONDAY, dow.isMonday());
        values.put(TablesDataSource.DAYS_OF_WEEK_TUESDAY, dow.isTuesday());
        values.put(TablesDataSource.DAYS_OF_WEEK_WEDNESDAY, dow.isWednesday());
        values.put(TablesDataSource.DAYS_OF_WEEK_THURSDAY, dow.isThursday());
        values.put(TablesDataSource.DAYS_OF_WEEK_FRIDAY, dow.isFriday());
        values.put(TablesDataSource.DAYS_OF_WEEK_SATURDAY, dow.isSaturday());
        values.put(TablesDataSource.DAYS_OF_WEEK_SUNDAY, dow.isSunday());

        return database.update(TablesDataSource.TABLE_DAYS_OF_WEEK_NAME, values, TablesDataSource.DAYS_OF_WEEK_ID + "=" + String.valueOf(dow.getId()), null) > -1;
    }

    public DaysOfWeek find(int id){
        Cursor c = database.rawQuery("SELECT * FROM " + TablesDataSource.TABLE_DAYS_OF_WEEK_NAME, null);
        c.moveToFirst();

        DaysOfWeek dow = new DaysOfWeek();

        dow.setId(c.getInt(c.getColumnIndex(TablesDataSource.DAYS_OF_WEEK_ID)));
        if (c.getInt(c.getColumnIndex(TablesDataSource.DAYS_OF_WEEK_MONDAY)) == 1) {
            dow.setMonday(true);
        } else {
            dow.setMonday(false);
        }

        if (c.getInt(c.getColumnIndex(TablesDataSource.DAYS_OF_WEEK_TUESDAY)) == 1) {
            dow.setTuesday(true);
        } else {
            dow.setTuesday(false);
        }

        if (c.getInt(c.getColumnIndex(TablesDataSource.DAYS_OF_WEEK_WEDNESDAY)) == 1) {
            dow.setWednesday(true);
        } else {
            dow.setWednesday(false);
        }
        if (c.getInt(c.getColumnIndex(TablesDataSource.DAYS_OF_WEEK_THURSDAY)) == 1) {
            dow.setThursday(true);
        } else {
            dow.setThursday(false);
        }

        if (c.getInt(c.getColumnIndex(TablesDataSource.DAYS_OF_WEEK_FRIDAY)) == 1) {
            dow.setFriday(true);
        } else {
            dow.setFriday(false);
        }

        if (c.getInt(c.getColumnIndex(TablesDataSource.DAYS_OF_WEEK_SATURDAY)) == 1) {
            dow.setSaturday(true);
        } else {
            dow.setSaturday(false);
        }

        if (c.getInt(c.getColumnIndex(TablesDataSource.DAYS_OF_WEEK_SUNDAY)) == 1) {
            dow.setSunday(true);
        } else {
            dow.setSunday(false);
        }

        return dow;
    }

}
