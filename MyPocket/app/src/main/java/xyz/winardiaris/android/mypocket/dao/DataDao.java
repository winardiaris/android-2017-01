package xyz.winardiaris.android.mypocket.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import xyz.winardiaris.android.mypocket.domain.DataDomain;

import static xyz.winardiaris.android.mypocket.dao.DataDbSchema.TableData.*;

/**
 * Created by ars on 5/31/17.
 */

public class DataDao {
    private Context context;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

    public DataDao (Context ctx){
        this.context = ctx;
    }

    public  void insertData(DataDomain dd){
        DataDbHelper dbHelper = new DataDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Integer userId = 1;

        ContentValues cv = new ContentValues();
        cv.put(DATA_VALUE, dd.getValue().doubleValue());
        cv.put(DATA_TYPE, dd.getType());
        cv.put(DATA_DATE, dd.getDate().getTime());
        cv.put(DATA_DESCRIPTION, dd.getDescription());
        cv.put(DATA_RECEIPT,dd.getReceiptImage());
        cv.put(DATA_USER_ID,userId);

        db.insert(TABLE_NAME,null,cv);
        db.close();

    }
    public List<DataDomain> allData() throws ParseException {
        List<DataDomain> dataPocket = new ArrayList<>();
        DataDbHelper dbHelper = new DataDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] dataColoumn = {
//                DATA_ID,
                DATA_VALUE,
                DATA_TYPE,
                DATA_DATE,
                DATA_DESCRIPTION,
                DATA_RECEIPT,
                DATA_USER_ID,
        };

        String dataSort = DATA_DATE + " DESC";
        Cursor c = db.query(TABLE_NAME,dataColoumn,null,null,null,null,dataSort);

        if(c.moveToFirst()){
            while (c.moveToNext()){
                DataDomain dd = new DataDomain();
                dd.setValue(new BigDecimal(c.getDouble(0)));
                dd.setType(c.getString(1));
                dd.setDate(format.parse(c.getString(2)));
                dd.setDescription(c.getString(3));
                dd.setReceiptImage(c.getString(4));
                dd.setUserId(c.getInt(5));

                dataPocket.add(dd);
            }
        }

        Log.d("DATA DATABASES", c.toString());
        c.close();
        db.close();
        return  dataPocket;
    }
}
