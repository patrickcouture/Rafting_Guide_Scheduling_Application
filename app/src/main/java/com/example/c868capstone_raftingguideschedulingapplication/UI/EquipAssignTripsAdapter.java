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
import com.example.c868capstone_raftingguideschedulingapplication.entities.Trips;

import java.util.List;

public class EquipAssignTripsAdapter extends RecyclerView.Adapter<EquipAssignTripsAdapter.EquipAssignViewHolder>{
    class EquipAssignViewHolder extends RecyclerView.ViewHolder {

        private final TextView assignTripItemView;

        private EquipAssignViewHolder (View view) {
            super(view);
            assignTripItemView = view.findViewById(R.id.assocTripsTxView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Trips current = mEquipTrips.get(position);
                    Intent intent = new Intent(context, TripDetails.class);
                    intent.putExtra("tripID", current.getTripID());
                    intent.putExtra("tripName", current.getTripName());
                    intent.putExtra("guideID", current.getGuideID());
                    intent.putExtra("customerID", current.getCustomerID());
                    intent.putExtra("equipmentID", current.getEquipmentID());
                    intent.putExtra("tripStart", current.getTripStart());
                    intent.putExtra("tripEnd", current.getTripEnd());
                    intent.putExtra("tripNotes", current.getTripNotes());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Trips> mEquipTrips;

    private final Context context;

    private final LayoutInflater mInflater;

    public EquipAssignTripsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public EquipAssignTripsAdapter.EquipAssignViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_list_item, parent, false);

        return new EquipAssignTripsAdapter.EquipAssignViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipAssignTripsAdapter.EquipAssignViewHolder holder, int position){
        if(mEquipTrips != null) {
            Trips currentTrips = mEquipTrips.get(position);
            String name = currentTrips.getTripName();
            holder.assignTripItemView.setText(name);
        } else {
            holder.assignTripItemView.setText("No Trip Name");
        }
    }

    public void setTrips(List<Trips> trips){
        mEquipTrips = trips;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        if(mEquipTrips != null) {
            return mEquipTrips.size();
        } else return 0;
    }
}
