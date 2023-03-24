package com.example.c868capstone_raftingguideschedulingapplication.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c868capstone_raftingguideschedulingapplication.R;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Customers;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Guides;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Trips;

import java.util.List;

public class TripsCustomerAdapter extends RecyclerView.Adapter<TripsCustomerAdapter.TripsCustomerViewHolder>{

    class TripsCustomerViewHolder extends RecyclerView.ViewHolder {

        private final TextView assignTripItemView;

        private TripsCustomerViewHolder (View view) {
            super(view);
            assignTripItemView = view.findViewById(R.id.assocTripsTxView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Customers current = mTripsCustomer.get(position);
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
    private List<Customers> mTripsCustomer;

    private final Context context;

    private final LayoutInflater mInflater;

    public TripsCustomerAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TripsCustomerAdapter.TripsCustomerViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_list_item, parent, false);

        return new TripsCustomerAdapter.TripsCustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripsCustomerAdapter.TripsCustomerViewHolder holder, int position){
        if(mTripsCustomer != null) {
            Customers currentCustomer = mTripsCustomer.get(position);
            String name = currentCustomer.getCustomerName();
            holder.assignTripItemView.setText(name);
        } else {
            holder.assignTripItemView.setText("No Trip Name");
        }
    }

    public void setCustomers(List<Customers> customers){
        mTripsCustomer = customers;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        if(mTripsCustomer != null) {
            return mTripsCustomer.size();
        } else return 0;
    }
}
