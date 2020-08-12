package com.suza.labexam2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewActivity extends AppCompatActivity {
    ArrayList<HashMap<String, String>> list;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        getSupportActionBar().setTitle("List of Candidates");

        listView = findViewById(R.id.list_view);
        String[] itemsNames = {"name", "age"};
        int[] itemsRef = {R.id.name, R.id.age};

        Database db = new Database(this);
        list = db.getAll();

        ListAdapter adapter = new SimpleAdapter(this, list, R.layout.list_view, itemsNames, itemsRef);
        listView.setAdapter(adapter);
    }
}