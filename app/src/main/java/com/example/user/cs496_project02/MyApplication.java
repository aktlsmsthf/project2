package com.example.user.cs496_project02;

/**
 * Created by user on 2017-12-30.
 */

import android.app.Application;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

public class MyApplication extends Application {

    public ArrayList<Contact> ContactList;

    @Override
    public void onCreate(){
        super.onCreate();
        ContactList = new ArrayList<Contact>();

    }

    public void loadData()
    {
        ContactList = fetchAllContacts();
    }


    private ArrayList<Contact> fetchAllContacts(){
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        ArrayList<Contact> contacts = new ArrayList<Contact>();

        String[] projection = new String[]{
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        };

        String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" COLLATE LOCALIZED ASC";
        Cursor cursor = getApplicationContext().getContentResolver().query(
                uri,
                projection,
                null,
                null,
                sortOrder
        );

        Log.i("CONTACT", "start");
        while(cursor.moveToNext()){
            String email = "";
            String number = cursor.getString(1).replaceAll("-","");
            String name = cursor.getString(2);
            if (number.length() == 10) {
                number = number.substring(0, 3) + "-"
                        + number.substring(3, 6) + "-"
                        + number.substring(6);
            } else if (number.length() > 8) {
                number = number.substring(0, 3) + "-"
                        + number.substring(3, 7) + "-"
                        + number.substring(7);
            }
            Cursor emailCursor = getApplicationContext().getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Email.DATA},
                    "DISPLAY_NAME"+"='"+name+"'",
                    null, null);
            if(emailCursor.moveToFirst()){
                email = emailCursor.getString(0);
            }
            Contact c = new Contact(
                    name,
                    number,
                    email
            );

            Log.i("CONTACT", c.name);
            Log.i("CONTACT", c.number);
            Log.i("CONTACT", c.email);

            contacts.add(c);
        }
        return contacts;
    }

    public ArrayList<Contact> getContactList(){return ContactList;}
}