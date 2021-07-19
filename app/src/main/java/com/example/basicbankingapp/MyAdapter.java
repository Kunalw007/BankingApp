package com.example.basicbankingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {


    private List<Customer> customer;
    private OnNoteListener onNoteListener;
    Context context;
    public MyAdapter(Context context, List<Customer> customer,OnNoteListener onNoteListener) {
        this.context=context;
        this.customer = customer;
        this.onNoteListener=onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data,parent,false);
        return new ViewHolder(view,onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Customer cs=customer.get(position);
    System.out.println("byee "+cs.getId());
    holder.txtid.setText(cs.getId()+"");
    holder.txtname.setText(cs.getName());



    }

    @Override
    public int getItemCount() {
        return customer.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtid,txtname;
        LinearLayout layout;
        OnNoteListener onNoteListener;
        public ViewHolder(@NonNull View itemView ,OnNoteListener onNoteListener) {
            super(itemView);
            txtid=(TextView) itemView.findViewById(R.id.idt);
            txtname=(TextView) itemView.findViewById(R.id.nametxt);
            this.onNoteListener=onNoteListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClicked(getAdapterPosition());
        }
    }
    public interface OnNoteListener{
        void onNoteClicked(int position);
    }

}


//    public void onClick(View v) {

//    }