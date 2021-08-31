package com.example.cred_operation_sql;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import com.example.cred_operation_sql.SQL.DBHelper;
import com.example.cred_operation_sql.SQL.Data;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private Spinner spinnerMain;
    private Button createMain,readMain,updateMain,deleteMain;
    private String s;
    private String s1;
    DBHelper db = new DBHelper(MainActivity.this);
    private static ArrayList<Data> data ;
    private static ArrayList<String> users;

    public  ArrayList<String> getUsersRead() {
        return users;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data =new ArrayList<>(db.readData());
        users = new ArrayList<>();
        getUser();
        spinnerMain = findViewById(R.id.spinnerMain);
        createMain = findViewById(R.id.createEditText);
        readMain = findViewById(R.id.readEditText);
        updateMain = findViewById(R.id.updateEditText);
        deleteMain = findViewById(R.id.deleteEditText);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,users);
        spinnerMain.setAdapter(adapter);
        spinnerMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s1=adapterView.getItemAtPosition(i).toString();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        createMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = "CreateMain";
                Intent i = new Intent(MainActivity.this, RIU_Activity.class);
                i.putExtra("Button",s);
                startActivity(i);
            }
        });
        readMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = "ReadMain";
                Intent i = new Intent(MainActivity.this, ReadActivity.class);
                i.putExtra("Button",s);
                i.putExtra("GetUser",s1);
                startActivity(i);
            }
        });
        updateMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = "UpdateMain";
                Intent i = new Intent(MainActivity.this, RIU_Activity.class);
                i.putExtra("Button",s);
                i.putExtra("GetUser",s1);
                startActivity(i);
            }
        });
        deleteMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (db.deleteData(s1)){
//                    Toast.makeText(MainActivity.this,"Data Droped",Toast.LENGTH_SHORT).show();
//
//                }
                s = "DeleteMain";
                Intent i = new Intent(MainActivity.this, RIU_Activity.class);
                i.putExtra("Button",s);
                i.putExtra("GetUser",s1);
                startActivity(i);
            }
        });
    }
    public static void getUser(){
        for (int i =0 ; i<data.size();i++){
            users.add(data.get(i).getUserName());
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}