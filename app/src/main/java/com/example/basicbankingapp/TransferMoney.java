package com.example.basicbankingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TransferMoney extends AppCompatActivity {

    ArrayList<Customer> customerList=new ArrayList<>();
    ArrayList<String> names=new ArrayList<>();
    ArrayList<String> amount=new ArrayList<>();
    TextView amount1;
    String creditAmount,tutorialsName;
    Button pay;
    Customer c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_money);
        Spinner spinner = findViewById(R.id.spinner);
        amount1=findViewById(R.id.amount);
        String id=getIntent().getStringExtra("id");
        String name=getIntent().getStringExtra("name");

        String current_balance=getIntent().getStringExtra("current_balance");
        DbHandler db=new DbHandler(getApplicationContext());
        pay=findViewById(R.id.SendMoney);
        customerList=db.getValuesName(id);
        for(int i=0;i<customerList.size();i++){
            c=customerList.get(i);
            names.add(c.getName());
            amount.add(c.getCurrent_balance()+"");
        }
        System.out.println("hemlo "+names.toString());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, names);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tutorialsName = parent.getItemAtPosition(position).toString();
                creditAmount=amount.get(position);
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName+amount.get(position), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {

            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount =amount1.getText().toString();
                Integer debit=Integer.parseInt(current_balance)-(Integer.parseInt(amount));
                DbHandler dbHandler=new DbHandler(getApplicationContext());
                boolean rowid=dbHandler.updateCustomerDetails(name,debit);
                Integer credit=Integer.parseInt(creditAmount)+(Integer.parseInt(amount));
                boolean rowid2=dbHandler.updateCustomerDetails(tutorialsName,credit);
                if(rowid2 && rowid) {
                    Toast.makeText(TransferMoney.this,"Payment Successful", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),ViewCustomers.class);
                    startActivity(i);
                    finishAffinity();
                }
                else
                    Toast.makeText(TransferMoney.this, "Payment Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
