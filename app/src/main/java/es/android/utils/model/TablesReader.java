package es.android.utils.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by miguelconde on 11/01/17.
 */

public class TablesReader extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Utils.db";
    private static final int DATABASE_VERSION = 1;

    public TablesReader(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TablesDataSource.TABLE_DOCUMENTS);
        db.execSQL(TablesDataSource.TABLE_DAYS_OF_WEEK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
