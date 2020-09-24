package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> list = new ArrayList<String>();

        CustomAdapter adapter = new CustomAdapter(list, this);


        final ListView listView = (ListView)findViewById(R.id.task_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>();
        Button addButton = (Button)findViewById(R.id.button_add);
        EditText getText = (EditText)findViewById(R.id.task_add);

        listView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView
            }
        });
    }
}