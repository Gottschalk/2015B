package com.example.flo.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Flo_2 on 05.03.2015.
 */

public class DBHelper extends  SQLiteOpenHelper {



    public static final String TABLE_CONTACT = "contact";
    public static final String CONTACT_ID = "id";
    public static final String CONTACT_NAME = "name";
    public static final String CONTACT_NUMBER = "number";
    public static final String CONTACT_ICON = "icon";

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "contact.db";

    private static final String[] COLUMNS = {CONTACT_ID, CONTACT_ICON, CONTACT_NAME,CONTACT_NUMBER};


    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_CONTACT + " (" +
                    CONTACT_ID + " INTEGER PRIMARY KEY," +
                    CONTACT_ICON + " INTEGER," +
                    CONTACT_NAME + TEXT_TYPE + COMMA_SEP +
                    CONTACT_NUMBER + TEXT_TYPE +

            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_CONTACT;




    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void addContact(Contact contact){

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(CONTACT_ICON, contact.getIcon()); // get icon
        values.put(CONTACT_NAME, contact.getName()); // get name
        values.put(CONTACT_NUMBER, contact.getNumber()); // get number


        // 3. insert
        db.insert(TABLE_CONTACT, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Contact getContact(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_CONTACT, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build contact object
        Contact contact = new Contact();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(2));
        contact.setNumber(cursor.getString(3));
        contact.setIcon(Integer.parseInt(cursor.getString(1)));



        // 5. return book
        return contact;
    }

    public void deleteContact(Contact contact) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_CONTACT, //table name
                CONTACT_ID+" = ?",  // selections
                new String[] { String.valueOf(contact.getId()) }); //selections args

        // 3. close
        db.close();


    }

    // Get All Contacts
    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contacts = new ArrayList<Contact>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CONTACT;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build contact and add it to list
        Contact contact = null;
        int counter = 0;
        if (cursor.moveToFirst()) {
            do {
                counter ++;
                contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(2));
                contact.setNumber(cursor.getString(3));
                contact.setIcon(Integer.parseInt(cursor.getString(1)));

                // Add contact to contacts
                contacts.add(contact);

            } while (cursor.moveToNext());
        }



        // return books
        return contacts;
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}