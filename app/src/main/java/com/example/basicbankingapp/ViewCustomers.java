package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewCustomers extends AppCompatActivity implements MyAdapter.OnNoteListener {

    private MyAdapter myAdapter;
    private RecyclerView rv;
    ArrayList<Customer> customerList;
    private int progressBarStatus = 0;
    ProgressBar mProgress;
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customers);



        //write this code in oncreate after setContentView(..)
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        new Thread(new Runnable() {
            public void run() {

                    try {
                        Thread.sleep(1500);
                        progressBarStatus++;
                        mProgress.setVisibility(ProgressBar.INVISIBLE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mHandler.post(new Runnable() {
                        public void run() {
                            DbHandler db=new DbHandler(getApplicationContext());
                            customerList=db.getValues();
                            rv=findViewById(R.id.recyclerview);
                            rv.setHasFixedSize(true);
                            rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            myAdapter=new MyAdapter(ViewCustomers.this,customerList,ViewCustomers.this::onNoteClicked);
                            // RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                            //  rv.setLayoutManager(layoutManager);
                            rv.setAdapter(myAdapter);
                        }
                    });
                }

        }).start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mProgress.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public void onNoteClicked(int position) {
        Customer c=customerList.get(position);
        Intent i=new Intent(this,ViewCustomerDetails.class);
        i.putExtra("id",c.getId()+"");
        i.putExtra("name",c.getName());
        i.putExtra("email",c.getEmail());
        i.putExtra("phone_number",c.getPhone_number()+"");
        i.putExtra("current_balance",c.getCurrent_balance()+"");
        startActivity(i);
    }
}