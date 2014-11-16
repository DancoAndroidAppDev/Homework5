package net.cozz.danco.homework5;

import android.database.Cursor;

/**
 * Created by danco on 10/25/14.
 */
public class Flower {
    private long id;
    private String flower;

    public Flower(Cursor cursor) {
        this.id = cursor.getLong(0);
        this.flower = cursor.getString(2);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFlower() {
        return flower;
    }

    public void setFlower(String flower) {
        this.flower = flower;
    }


    @Override
    public String toString() {
        return flower;
    }
}
