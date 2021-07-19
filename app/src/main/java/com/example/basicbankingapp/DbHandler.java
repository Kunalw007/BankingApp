package com.example.basicbankingapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "customers_sdb";                    // Name of Data base
    private static final String TABLE_CUSTOMERS = "customers_details";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL="email";
    private static final String KEY_PHONE_NUMBER="phone_number";
    private static final String KEY_CURRENT_BALANCE="current_balance";

    public DbHandler(Context context)
    {
        super(context, DB_NAME ,  null,  DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="CREATE TABLE "+TABLE_CUSTOMERS+"("+KEY_ID+" INTEGER,"+ KEY_NAME +" TEXT,"
                +KEY_EMAIL +" TEXT,"+KEY_PHONE_NUMBER+" INTEGER ,"+KEY_CURRENT_BALANCE +" INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);

        // Create tables again
        onCreate(db);
    }

    long insertCustomersDetails(Integer id,String name,String email,Integer phone_number,Integer current_balance){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_ID,id);
        values.put(KEY_NAME,name);
        values.put(KEY_EMAIL,email);
        values.put(KEY_PHONE_NUMBER,phone_number);
        values.put(KEY_CURRENT_BALANCE,current_balance);

        long newRowId=db.insert(TABLE_CUSTOMERS,null,values);
        db.close();

        return newRowId;
    }
    public ArrayList<Customer> getValues(){

        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList<Customer> customerList=new ArrayList<>();
        String query="SELECT id,name,email,phone_number,current_balance FROM " +TABLE_CUSTOMERS;
        Cursor cursor=db.rawQuery(query,null);
        while (cursor.moveToNext()){
            Customer customer=new Customer();
            customer.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
            customer.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            customer.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
           customer.setPhone_number(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_PHONE_NUMBER))));
           customer.setCurrent_balance(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_CURRENT_BALANCE))));
            customerList.add(customer);
            System.out.println("helloo "+customer.getId());

        }
        System.out.println("Hello "+customerList.toString());
        return customerList;
    }
    public ArrayList<Customer> getValuesName(String id1){

        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList<Customer> customerList=new ArrayList<>();
        String query="SELECT id,name,email,phone_number,current_balance FROM " +TABLE_CUSTOMERS +" W+id1HERE id IS NOT ";
        Cursor cursor=db.rawQuery(query,null);
        while (cursor.moveToNext()){
            Customer customer=new Customer();
            customer.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
            customer.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            customer.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
            customer.setPhone_number(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_PHONE_NUMBER))));
            customer.setCurrent_balance(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_CURRENT_BALANCE))));
            customerList.add(customer);
            System.out.println("hello "+customer.getId());

        }
        System.out.println("Hello "+customerList.toString());
        return customerList;
    }
    boolean updateCustomerDetails(String name,Integer amount){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_CURRENT_BALANCE,amount);
        long oldRowId=db.update(TABLE_CUSTOMERS,values,"name = ?",new String[]{ name });

        db.close();

        return true;
    }
}
