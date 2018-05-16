package ericstephens.candleinventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;

/**
 * Created by roost on 4/12/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper
implements Serializable
{

    public static final String DATABASE_NAME = "candleInventory.db";
    public static final String TABLE_NAME = "candleData";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "CANDLE_NAME";
    public static final String COL_3 = "INVENTORY";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, CANDLE_NAME TEXT, INVENTORY TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String candleName, String inventoryAmt)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, candleName);
        contentValues.put(COL_3, inventoryAmt);

        return db.insert(TABLE_NAME, null, contentValues) != -1;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_NAME, null);
        return result;
    }

    public Cursor getSearch(String candleName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_NAME + " where CANDLE_NAME = " + candleName, null);
        return result;

    }
}
