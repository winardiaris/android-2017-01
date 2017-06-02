package xyz.winardiaris.android.mypocket.dao;

import android.content.Intent;
import android.provider.BaseColumns;
import android.support.design.widget.TabLayout;

/**
 * Created by ars on 5/31/17.
 */

public final class DataDbSchema {
    public static final String DB_NAME = "mypocket_data.db";
    public static final Integer DB_VERSION = 1;

    //create table
    public static final String TEXT_TYPE = " TEXT";
    public static final String REAL_TYPE = " REAL";
    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_DATA_ENTRIES =
            "CREATE TABLE " + TableData.TABLE_NAME+ "(" +
                    TableData.DATA_ID + "INTEGER PRIMAY KEY, " +
                    TableData.DATA_VALUE + REAL_TYPE + COMMA_SEP +
                    TableData.DATA_TYPE + TEXT_TYPE + COMMA_SEP +
                    TableData.DATA_DATE + TEXT_TYPE + COMMA_SEP +
                    TableData.DATA_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    TableData.DATA_RECEIPT + TEXT_TYPE + COMMA_SEP +
                    TableData.DATA_USER_ID + TEXT_TYPE +
            ")";

    //drop table
    public static final String SQL_DROP_DATA_ENTRIES =
            "DROP TABLE IF EXISTS " + TableData.TABLE_NAME;

    private DataDbSchema(){}

    // data table schema
    public abstract class TableData implements BaseColumns{
        public static final String TABLE_NAME = "data";
        public static final String DATA_ID = "data_id";
        public static final String DATA_VALUE = "value";
        public static final String DATA_TYPE = "type";
        public static final String DATA_DATE = "date";
        public static final String DATA_DESCRIPTION = "desc";
        public static final String DATA_RECEIPT = "receipt";
        public static final String DATA_USER_ID = "user_id";
    }
}
