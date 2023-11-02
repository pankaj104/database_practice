package com.training.databasepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


      //  create dbhelper object to access DBHelper class and pass context this
        MyDBHelper dbHelper=new MyDBHelper(this);
//        dbHelper.addContact("Pankaj","98538753");
//        dbHelper.addContact("Ravi","98538753");

        ContactModel contactModel=new ContactModel();
        contactModel.id=1;
        contactModel.name="Pankaj Kumar";
        contactModel.phone_no="111111";
        dbHelper.updateContact(contactModel);

        ArrayList<ContactModel> arrContact=dbHelper.fetchContact();

        for(int i=0;i<arrContact.size();i++)
        {
            Log.d("CONTACT_INFO","Name: "+arrContact.get(i).name+"Phone no: "+arrContact.get(i).phone_no);

        }

    }
}