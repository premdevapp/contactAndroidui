package com.example.contactmanagerui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.contactmanagerui.adapter.RecyclerViewAdapter;
import com.example.contactmanagerui.data.DatabaseHandler;
import com.example.contactmanagerui.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private RecyclerViewAdapter recyclerViewAdapter;

    private ArrayList<Contact> contactArrayList;

    private ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactArrayList = new ArrayList<>();

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);

        List<Contact> contactList = db.getAllContacts();

        for (Contact contact: contactList){
            Log.d("MainActivity", "onCreate: "+ contact.getName());
            contactArrayList.add(contact);
        }

        // Adapter

        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);

        recyclerView.setAdapter(recyclerViewAdapter);




    }

}
