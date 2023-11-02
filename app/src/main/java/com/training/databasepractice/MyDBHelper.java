package com.training.databasepractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class MyDBHelper extends SQLiteOpenHelper {

   // use static with final to avoid assign any another value.
    private static final String DATABASE_NAME= "ContactsDB";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_CONTACT= "contacts";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_PHONE_NO="phone_no";

    // Constructor create
    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // to create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        // in simple sql create table code:
        //  CREATE TABLE contacts(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_no TEXT);

        db.execSQL("CREATE TABLE "+TABLE_CONTACT + "("+ KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                KEY_NAME +" TEXT,"+ KEY_PHONE_NO + " TEXT"+");"  );


        // to open database in read or write format
       // SQLiteDatabase database = this.getWritableDatabase();
        // to close database
      //  database.close();

    }
    // to upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // first drop the table contact and call oncreate. it will update the database
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CONTACT);
        onCreate(db);
    }

    // create function to insert data
    public void addContact(String name, String phono_no)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        // contentvalues for put key and value of data
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_PHONE_NO, phono_no);
        db.insert(TABLE_CONTACT,null,values);
    }

    public ArrayList<ContactModel> fetchContact(){
        SQLiteDatabase db= this.getReadableDatabase();
        // rawQuery method return cursor
      Cursor cursor= db.rawQuery("SELECT * FROM "+ TABLE_CONTACT,null);

      ArrayList<ContactModel>  arrContact= new ArrayList<>();
        // now move cursor and add data in array list till cursor move to last.

        while (cursor.moveToNext()){
            ContactModel model= new ContactModel();
            model.id=cursor.getInt(0);
            model.name=cursor.getString(1);
            model.phone_no=cursor.getString(2);
            arrContact.add(model);
        }
        return arrContact;

    }

    public void updateContact(ContactModel contactModel){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(KEY_PHONE_NO,contactModel.phone_no);
        database.update(TABLE_CONTACT,cv,KEY_ID+" = "+contactModel.id,null);

    }
}
