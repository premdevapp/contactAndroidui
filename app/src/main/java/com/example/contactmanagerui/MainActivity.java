package com.example.contactmanagerui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


import com.example.contactmanagerui.data.DatabaseHandler;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);


    }

}
