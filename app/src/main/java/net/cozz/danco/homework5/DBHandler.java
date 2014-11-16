package net.cozz.danco.homework5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by danco on 10/25/14.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "stateFlowers";
    public static final String TABLE_FLOWERS = "flowers";
    public static final String KEY_ID = "_id";
    public static final String KEY_STATE = "state";
    public static final String KEY_FLOWER = "flower";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_FLOWERS + " (" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_STATE + " TEXT, " +
                KEY_FLOWER + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLOWERS);

        onCreate(db);
    }
}
