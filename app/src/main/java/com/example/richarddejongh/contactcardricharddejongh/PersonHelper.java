package com.example.richarddejongh.contactcardricharddejongh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.SimpleCursorTreeAdapter;

import java.util.ArrayList;

/**
 * Created by Richard de Jongh on 27-9-2016.
 */

public class PersonHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private static final String PERSONS_TABLE_NAME = "persons";
    private static final String DATABASE_NAME = "contactCard";
    private static final String BSN = "bsn";
    private static final String GENDER = "gender";
    private static final String TITLE = "title";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String STREET = "street";
    private static final String CITY = "city";
    private static final String ZIP = "zip";
    private static final String EMAIL = "email";
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String PHONE = "phone";
    private static final String CELL = "cell";
    private static final String LARGE_PICTURE_URL = "largePictureUrl";
    private static final String MEDIUM_PICTURE_URL = "mediumPictureUrl";
    private static final String THUMBNAIL_URL = "thumbnailUrl";
    private static final String NATIONALITY = "nationality";

    private static final String PERSONS_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + PERSONS_TABLE_NAME + " (" +
             BSN + " TEXT NOT NULL, " +
             GENDER + " TEXT NOT NULL," +
             TITLE + " TEXT NOT NULL," +
             FIRST_NAME + " TEXT NOT NULL," +
             LAST_NAME + " TEXT NOT NULL," +
             STREET + " TEXT NOT NULL," +
             CITY + " TEXT NOT NULL," +
             ZIP + " TEXT NOT NULL," +
             EMAIL + " TEXT NOT NULL," +
             DATE_OF_BIRTH + " TEXT NOT NULL," +
             PHONE + " TEXT NOT NULL," +
             CELL + " TEXT NOT NULL," +
             LARGE_PICTURE_URL + " TEXT NOT NULL," +
             MEDIUM_PICTURE_URL + " TEXT NOT NULL," +
             THUMBNAIL_URL + " TEXT NOT NULL," +
             NATIONALITY + " TEXT NOT NULL" + ");";

    PersonHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + PERSONS_TABLE_NAME);
        db.execSQL(PERSONS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PERSONS_TABLE_NAME);
        onCreate(db);
    }

    public void addPerson(Person person)
    {
        ContentValues values = new ContentValues();
        values.put(BSN, person.getBsn());
        values.put(GENDER, person.gender);
        values.put(TITLE, person.getTitle());
        values.put(FIRST_NAME, person.getFirstName());
        values.put(LAST_NAME, person.getLastName());
        values.put(STREET, person.getStreet());
        values.put(CITY, person.getCity());
        values.put(ZIP, person.getZip());
        values.put(EMAIL, person.getEmail());
        values.put(PHONE, person.getPhone());
        values.put(CELL, person.getCell());
        values.put(LARGE_PICTURE_URL, person.getLargePictureUrl());
        values.put(MEDIUM_PICTURE_URL, person.getMediumPictureUrl());
        values.put(THUMBNAIL_URL, person.getThumbnailUrl());
        values.put(NATIONALITY, person.getNationality());
        values.put(DATE_OF_BIRTH, person.getDateOfBirth());
        Log.i("birth", "" + person.dateOfBirth);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(PERSONS_TABLE_NAME, null, values);
        db.close();
        Log.i("personAdded", person.firstName);
    }

    public ArrayList<Person> getPersons(){
        ArrayList<Person> persons = new ArrayList<Person>();

        String query = "SELECT * FROM " + PERSONS_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        while(cursor.moveToNext()){
            Person person = new Person();
            person.setBsn(cursor.getString(cursor.getColumnIndex(BSN)));
            person.setGender(cursor.getString(cursor.getColumnIndex(GENDER)));
            person.setFirstName(cursor.getString(cursor.getColumnIndex(FIRST_NAME)));
            person.setLastName(cursor.getString(cursor.getColumnIndex(LAST_NAME)));
            person.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
            person.setStreet(cursor.getString(cursor.getColumnIndex(STREET)));
            person.setCity(cursor.getString(cursor.getColumnIndex(CITY)));
            person.setZip(cursor.getString(cursor.getColumnIndex(ZIP)));
            person.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
            person.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
            person.setCell(cursor.getString(cursor.getColumnIndex(CELL)));
            person.setLargePictureUrl(cursor.getString(cursor.getColumnIndex(LARGE_PICTURE_URL)));
            person.setMediumPictureUrl(cursor.getString(cursor.getColumnIndex(MEDIUM_PICTURE_URL)));
            person.setThumbnailUrl(cursor.getString(cursor.getColumnIndex(THUMBNAIL_URL)));
            person.setNationality(cursor.getString(cursor.getColumnIndex(NATIONALITY)));
            person.setDateOfBirth(cursor.getString(cursor.getColumnIndex(DATE_OF_BIRTH)));

            persons.add(person);

        }

        return persons;
    }
}
