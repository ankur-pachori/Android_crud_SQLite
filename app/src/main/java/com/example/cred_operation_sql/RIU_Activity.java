package com.example.cred_operation_sql;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.cred_operation_sql.SQL.DBHelper;
import com.example.cred_operation_sql.SQL.Data;

import java.util.ArrayList;

public class RIU_Activity extends AppCompatActivity {
    private EditText editTextName,editTextEmail,editTextNumber;
    private Button btnRIU;
    private Intent i;
    private String s;
    private ArrayList<Data> list;
    private DBHelper db = new DBHelper(RIU_Activity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rui);
        editTextName = findViewById(R.id.editUserNameRIU);
        editTextEmail = findViewById(R.id.editEmailRIU);
        editTextNumber = findViewById(R.id.editNumberRIU);
        btnRIU = findViewById(R.id.btnRIU);
        i = getIntent();
        s=i.getStringExtra("Button");
        if (s.equals("ReadMain")){
            String name = null, email = null, number = null;
            name =i.getStringExtra("GetUser");
            list = new ArrayList<>(db.readData());
          for (Data d : list) {
            String temp = d.getUserName();
            if (temp.equals(name)) {
                name = d.getUserName();
                email = d.getEmail();
                number = d.getNumber();
                editTextName.setText(name);
                editTextEmail.setText(email);
                editTextNumber.setText(number);
                btnRIU.setText("OK");
                btnRIU.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            Intent i = new Intent(RIU_Activity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                    }
                });
            }
        }

        editTextName.setEnabled(false);
        editTextEmail.setEnabled(false);
        editTextNumber.setEnabled(false);

    }else if (s.equals("CreateMain")){
            editTextName.setText("");
            editTextEmail.setText("");
            editTextNumber.setText("");
            btnRIU.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name ,email,number;
                    name = editTextName.getText().toString();

                    email = editTextEmail.getText().toString();
                    number = editTextNumber.getText().toString();
                    if (db.insertData(name,email,number)){
                        Toast.makeText(RIU_Activity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RIU_Activity.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            });
        }
        else if (s.equals("UpdateMain")){
            editTextName.setEnabled(false);
            String name = null, email = null, number = null;
            name =i.getStringExtra("GetUser");
            list = new ArrayList<>(db.readData());
            for (Data d : list) {
                String temp = d.getUserName();
                if (temp.equals(name)) {
                    name = d.getUserName();
                    email = d.getEmail();
                    number = d.getNumber();
                    editTextName.setText(name);
                    editTextEmail.setText(email);
                    editTextNumber.setText(number);
                    btnRIU.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                           String name1 = editTextName.getText().toString();
                          String email1 = editTextEmail.getText().toString();
                            String number1 =editTextNumber.getText().toString();
                            if (db.updateData(name1,email1,number1)){
                                Toast.makeText(RIU_Activity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RIU_Activity.this,MainActivity.class);
                                startActivity(i);
                                finish();
                            }else {
                                Toast.makeText(RIU_Activity.this,"Data Insertion Failed",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
                }
            }
        }else if (s.equals("DeleteMain")){
            String name = i.getStringExtra("GetUser");
            if (db.deleteData(name)){
                    Toast.makeText(RIU_Activity.this,"Data Droped",Toast.LENGTH_SHORT).show();
                }else {
                Toast.makeText(RIU_Activity.this,"Failed to remove data",Toast.LENGTH_SHORT).show();
            }
            Intent i = new Intent(RIU_Activity.this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}