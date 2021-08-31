package com.example.cred_operation_sql.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private ArrayList<Data> list = new ArrayList<>();
    private ContentValues contentValues;
    private String userDetails="userDetails";
    public DBHelper(Context context) {
        super(context,"DataBase",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
    DB.execSQL("Create table UserDetails(userName VARCHAR primary key, email VARCHAR, number TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
    DB.execSQL("drop Table if exists UserDetails");
    }
    public Boolean insertData(String userName,String email,String number){
        SQLiteDatabase DBW = this.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("UserName",userName);
        contentValues.put("Email",email);
        contentValues.put("Number",number);
        long l = DBW.insert(userDetails,null,contentValues);
        if (l == -1){
            return false;
        }else {
            return true;
        }
    }
    public Boolean updateData(String userName,String email,String number){
        SQLiteDatabase DBW = this.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("UserName",userName);
        contentValues.put("Email",email);
        contentValues.put("Number",number);
        Cursor cursor = DBW.rawQuery("Select * from userDetails where userName = ?",new String[]{userName});
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            long l = DBW.update("UserDetails",contentValues,"userName=?",new String[]{userName});
            if (l == -1) {
                return false;
            }else {
                return true;
            }
            }else {
            return false;
        }
        }
    public Boolean deleteData(String userName){
        SQLiteDatabase DBW = this.getWritableDatabase();
        Cursor cursor = DBW.rawQuery("Select * from UserDetails where userName = ?",new String[]{userName});
        if (cursor.getCount()>0){
            long l =DBW.delete("UserDetails","userName = ?",new String[]{userName});
            if (l == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
        }
        public ArrayList<Data> readData(){
            String name,email,number;
            SQLiteDatabase DBR = this.getReadableDatabase();
            Cursor cursor = DBR.rawQuery("Select * from UserDetails",null);
            cursor.moveToFirst();
            if (cursor.moveToFirst()){
                do {
                    name =cursor.getString(0);
                    email = cursor.getString(1);
                    number = cursor.getString(2);
                    list.add(new Data(name,email,number));
                }while (cursor.moveToNext());
            }
            DBR.close();
            return list;
        }
    }

