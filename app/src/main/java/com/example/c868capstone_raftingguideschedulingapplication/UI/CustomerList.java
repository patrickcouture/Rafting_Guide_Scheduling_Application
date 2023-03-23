package com.example.c868capstone_raftingguideschedulingapplication.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.c868capstone_raftingguideschedulingapplication.R;
import com.example.c868capstone_raftingguideschedulingapplication.database.Repository;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Customers;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CustomerList extends AppCompatActivity {

    private Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        FloatingActionButton fab = findViewById(R.id.addNewCustomerBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerList.this, CustomerDetails.class);
                startActivity(intent);
            }
        });

        repository = new Repository(getApplication());
        List<Customers> allCustomers = repository.getAllCustomers();
        RecyclerView recyclerView = findViewById(R.id.custListRecView);
        final CustomerAdapter customerAdapter = new CustomerAdapter(this);
        recyclerView.setAdapter(customerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customerAdapter.setCustomers(allCustomers);
        customerAdapter.notifyDataSetChanged();



    }

    @Override
    protected void onResume(){
        super.onResume();
        List<Customers> allCustomers = repository.getAllCustomers();
        RecyclerView recyclerView = findViewById(R.id.custListRecView);
        final CustomerAdapter customerAdapter = new CustomerAdapter(this);
        recyclerView.setAdapter(customerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customerAdapter.setCustomers(allCustomers);
        customerAdapter.notifyDataSetChanged();
    }

    public static class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

        class CustomerViewHolder extends RecyclerView.ViewHolder {

            private final TextView customerItemView;

            private CustomerViewHolder (View view) {
                super(view);
                customerItemView = view.findViewById(R.id.custListTextView);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        final Customers current = mCustomers.get(position);
                        Intent intent = new Intent(context, CustomerDetails.class);
                        intent.putExtra("customerID", current.getCustomerID());
                        intent.putExtra("customerName", current.getCustomerName());
                        intent.putExtra("customerEmail", current.getCustomerEmail());
                        intent.putExtra("customerPhone", current.getCustomerPhone());
                        intent.putExtra("customerGroupTotal", current.getCustomerGroupTotal());
                        context.startActivity(intent);
                    }
                });
            }
        }
        private List<Customers> mCustomers;

        private final Context context;

        private final LayoutInflater mInflater;

        public CustomerAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            this.context = context;
        }

        @NonNull
        @Override
        public CustomerViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_item, parent, false);

            return new CustomerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position){
            Customers currentCustomer = mCustomers.get(position);
            holder.customerItemView.setText(currentCustomer.getCustomerName());
        }

        public void setCustomers(List<Customers> customers){
            mCustomers = customers;
            notifyDataSetChanged();
        }

        public int getItemCount() {
            if(mCustomers != null) {
                return mCustomers.size();
            } else return 0;
        }

    }
}