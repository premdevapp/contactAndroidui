package com.example.contactmanagerui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.contactmanagerui.data.DatabaseHandler;
import com.example.contactmanagerui.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView list_view;
    private ArrayList<String> contactArrayList;

    private ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_view = findViewById(R.id.listView);

        contactArrayList = new ArrayList<>();

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);

       /* db.addContact(new Contact("Premnath", "6382709971"));
        db.addContact(new Contact("Priyadharsini", "9791793785"));
        db.addContact(new Contact("Pichaimuhu", "9791793785"));
        db.addContact(new Contact("Helena", "6382709971"));
        db.addContact(new Contact("Praseetha", "6382709971"));
        db.addContact(new Contact("Menackshi gurupriya", "6382709971"));
        db.addContact(new Contact("Vijayalakshmi", "6382709971"));
        db.addContact(new Contact("Haretha", "6382709971"));
*/
        List<Contact> contactList = db.getAllContacts();

        for (Contact contact: contactList){
            Log.d("MainActivity", "onCreate: "+ contact.getName());
            contactArrayList.add(contact.getName());
        }
        // create ArrayAdapter
        arrayAdapter = new ArrayAdapter<>(

                this,
                android.R.layout.simple_list_item_1,
                contactArrayList
        );

        // add to listview
        list_view.setAdapter(arrayAdapter);

        //attach event listner
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("position", "onItemClick: "+ position+ " "+ contactArrayList.get(position));
            }
        });

    }

}
