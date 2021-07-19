package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewCustomerDetails extends AppCompatActivity {

    TextView t1;
    Button pay;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer_details);
        t1=findViewById(R.id.data1);
        pay=findViewById(R.id.SendMoney);
        id=getIntent().getStringExtra("id");
        String name=getIntent().getStringExtra("name");
        String email=getIntent().getStringExtra("email");
        String phone_number=getIntent().getStringExtra("phone_number");
        String current_balance=getIntent().getStringExtra("current_balance");
        t1.setText("Id : "+id+"\n\nName : "+name+"\n\nEmail : "+email+"\n\nPhone No. : "+phone_number+"\n\nBalance : "+current_balance);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),TransferMoney.class);
                i.putExtra("id",id);
                i.putExtra("name",name);
                i.putExtra("current_balance",current_balance);
                startActivity(i);
            }
        });
    }
}