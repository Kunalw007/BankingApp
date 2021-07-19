package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Add_Customers extends AppCompatActivity {
    TextView id,name,email,phone_number,current_balance;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customers);
        id=findViewById(R.id.editTextNumber2);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone_number = findViewById(R.id.phone_number);
        current_balance = findViewById(R.id.current_balance);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String c_name=name.getText().toString();
            Integer c_id=Integer.parseInt(id.getText().toString());
            String c_email=email.getText().toString();
            Integer c_phone_number=Integer.parseInt(phone_number.getText().toString());
            Integer c_current_balance=Integer.parseInt(current_balance.getText().toString());

            DbHandler dbHandler=new DbHandler(Add_Customers.this);

            long rowid=dbHandler.insertCustomersDetails(c_id,c_name,c_email,c_phone_number,c_current_balance);

                Toast.makeText( getApplicationContext(), "Details Inserted Successfully ID = " + rowid, Toast.LENGTH_LONG).show();
            }
        });
    }
}