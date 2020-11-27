package com.example.contactmanagerui.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.contactmanagerui.R;
import com.example.contactmanagerui.model.Contact;
import com.example.contactmanagerui.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(Context context ) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    //We create our table
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL - Structured Query Language
        /*
           create table _name(id, name, phone_number);
         */
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY," + Util.KEY_NAME + " TEXT,"
                + Util.KEY_PHONE_NUMBER + " " +"TEXT" + ")";
        db.execSQL(CREATE_CONTACT_TABLE); //creating our table

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf(R.string.db_drop);
        db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

        //Create a table again
        onCreate(db);


    }

    /*
       CRUD = Create, Read, Update, Delete

     */
    //Add Contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        //Insert to row
        db.insert(Util.TABLE_NAME, null, values);

        Log.d("DBHandler", "addContact: " + "item added "+ contact.getPhoneNumber() + contact.getName());
        db.close(); //closing db connection!



    }

    //Get a contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[]{ Util.KEY_ID, Util.KEY_NAME, Util.KEY_PHONE_NUMBER},
                Util.KEY_ID +"=?",new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhoneNumber(cursor.getString(2));

        return contact;
    }

    //Get all Contacts
    public List<Contact> getAllContacts() {

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        //Select all contacts
        Cursor cursor = db.rawQuery(selectAll, null);

        List<Contact> contactList = new ArrayList<>();
        //Loop through our data
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                //add contact objects to our list
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    //Update contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        //update the row
        //update(tablename, values, where id = 2)
        return db.update(Util.TABLE_NAME, values, Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});
    }

    //Delete single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});
        Log.d("Delete", "deleteContact: "+ "deleted");
        db.close();
    }
    public void deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                new String[]{String.valueOf(id)});
        Log.d("Delete", "deleteContact: "+ "deleted");
        db.close();
    }

    //Get contacts count
    public int getCount() {
        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();

    }
}
