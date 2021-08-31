package com.example.cred_operation_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cred_operation_sql.SQL.DBHelper;
import com.example.cred_operation_sql.SQL.Data;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {
    private ListView listView;
    private static ArrayList<String> list = new ArrayList<>();
    DBHelper db = new DBHelper(ReadActivity.this);
    private static ArrayList<Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        listView = findViewById(R.id.list);
        data = new ArrayList<>(db.readData());
        getUser();
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(ReadActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(stringArrayAdapter);
    }

    public static void getUser() {
        for (int i = 0; i < data.size(); i++) {
            list.add(data.get(i).getUserName());
        }
    }

    @Override
    public void onBackPressed() {
        list.clear();
        finish();

        super.onBackPressed();
    }
}