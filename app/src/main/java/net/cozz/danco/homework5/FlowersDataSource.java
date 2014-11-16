package net.cozz.danco.homework5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danco on 10/26/14.
 */
public class FlowersDataSource {

    private SQLiteDatabase database;
    private DBHandler dbHelper;
    private String[] columns = {DBHandler.KEY_ID, DBHandler.KEY_STATE, DBHandler.KEY_FLOWER};


    public FlowersDataSource(Context context) {
        dbHelper = new DBHandler(context);
    }


    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }


    public void close() {
        dbHelper.close();
    }


    public Flower addFlower(String stateName, String cityName) {
        Flower Flower = null;
        ContentValues values = new ContentValues();
        values.put(DBHandler.KEY_STATE, stateName);
        values.put(DBHandler.KEY_FLOWER, cityName);
        long insertId = database.insert(DBHandler.TABLE_FLOWERS, null, values);
        Cursor cursor = database.query(DBHandler.TABLE_FLOWERS, columns,
                DBHandler.KEY_ID + "=" + insertId, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Flower = new Flower(cursor);
            cursor.close();
        }

        return Flower;
    }


    public List<Flower> getFlowers() {
        List<Flower> captials = new ArrayList<Flower>();

        Cursor cursor = database.query(DBHandler.TABLE_FLOWERS, columns,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Flower Flower = new Flower(cursor);
            captials.add(Flower);
            cursor.moveToNext();
        }

        cursor.close();

        return captials;
    }


    public Flower getFlower(String state) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DBHandler.TABLE_FLOWERS,
                columns,
                DBHandler.KEY_STATE + " =?" + state,
                null, null, null, null);
        cursor.moveToFirst();
        Flower flower = new Flower(cursor);

        return flower;
    }
}
