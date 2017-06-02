package xyz.winardiaris.android.mypocket.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ars on 5/31/17.
 */

public class DataDbHelper extends SQLiteOpenHelper {
    public DataDbHelper(Context context) {
        super(context, DataDbSchema.DB_NAME,
                null, DataDbSchema.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataDbSchema.SQL_CREATE_DATA_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DataDbSchema.SQL_DROP_DATA_ENTRIES);
        onCreate(db);
    }
}
