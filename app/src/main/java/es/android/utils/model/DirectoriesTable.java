package es.android.utils.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import es.android.utils.domain.DaysOfWeek;
import es.android.utils.domain.Directory;

/**
 * Created by miguelconde on 11/01/17.
 */

public class DirectoriesTable {

    private TablesReader openHelper;
    private SQLiteDatabase database;
    private Context context;

    public DirectoriesTable(Context context) {
        openHelper = new TablesReader(context);
        database = openHelper.getWritableDatabase();
        this.context = context;
    }

    public boolean insert(Directory directory){
        ContentValues values = new ContentValues();
        values.put(TablesDataSource.DOCUMENTS_RUTE_ORIGIN, directory.getRuteOrigin());
        values.put(TablesDataSource.DOCUMENTS_RUTE_DESTINY, directory.getRuteDestiny());
        values.put(TablesDataSource.DOCUMENTS_ACTIVE, directory.isActive());

        DaysOfWeek dow = directory.getDaysOfWeek();
        DaysOfWeekTable dowTable = new DaysOfWeekTable(context);
        long id_dow = dowTable.insert(dow);

        values.put(TablesDataSource.DOCUMENTS_ID_DOW, id_dow);

        return database.insert(TablesDataSource.TABLE_DOCUMENTS_NAME, null, values) > 0;
    }

    public boolean update(Directory directory){
        ContentValues values = new ContentValues();
        values.put(TablesDataSource.DOCUMENTS_RUTE_ORIGIN, directory.getRuteOrigin());
        values.put(TablesDataSource.DOCUMENTS_RUTE_DESTINY, directory.getRuteDestiny());
        values.put(TablesDataSource.DOCUMENTS_ACTIVE, directory.isActive());

        DaysOfWeek dow = directory.getDaysOfWeek();
        DaysOfWeekTable dowTable = new DaysOfWeekTable(context);
        dowTable.update(dow);

        values.put(TablesDataSource.DOCUMENTS_ID_DOW, directory.getId());

        return database.update(TablesDataSource.TABLE_DOCUMENTS_NAME, values, TablesDataSource.DOCUMENTS_ID + "=" + String.valueOf(directory.getId()), null) > 0;
    }

    public List<Directory> getAllDirectories(){
        DaysOfWeekTable dowTable = new DaysOfWeekTable(context);
        List<Directory> directoryList = new ArrayList<>();
        Cursor c = database.rawQuery("SELECT * FROM " + TablesDataSource.TABLE_DOCUMENTS_NAME, null);
        while (c.moveToNext()) {
            Directory doc = new Directory();
            doc.setId(c.getInt(c.getColumnIndex(TablesDataSource.DOCUMENTS_ID)));
            doc.setRuteOrigin(c.getString(c.getColumnIndex(TablesDataSource.DOCUMENTS_RUTE_ORIGIN)));
            doc.setRuteDestiny(c.getString(c.getColumnIndex(TablesDataSource.DOCUMENTS_RUTE_DESTINY)));

            if (c.getInt(c.getColumnIndex(TablesDataSource.DOCUMENTS_ACTIVE)) == 1) {
                doc.setActive(true);
            } else {
                doc.setActive(false);
            }

            int idDow = c.getInt(c.getColumnIndex(TablesDataSource.DOCUMENTS_ID_DOW));
            DaysOfWeek dow = dowTable.find(idDow);
            doc.setDaysOfWeek(dow);
            directoryList.add(doc);
        }

        return directoryList;
    }

}
