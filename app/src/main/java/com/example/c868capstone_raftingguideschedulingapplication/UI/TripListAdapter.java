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
import com.example.c868capstone_raftingguideschedulingapplication.entities.Equipment;
import com.example.c868capstone_raftingguideschedulingapplication.entities.Trips;

import java.util.List;

public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.TripListViewHolder>{


    class TripListViewHolder extends RecyclerView.ViewHolder {

        private final TextView tripItemView;

        private TripListViewHolder (View view) {
            super(view);
            tripItemView = view.findViewById(R.id.assocTripsTxView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Trips current = mTrips.get(position);
                    Intent intent = new Intent(context, TripDetails.class);
                    intent.putExtra("tripID", current.getTripID());
                    intent.putExtra("tripName", current.getTripName());
                    intent.putExtra("location", current.getLocation());
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
    private List<Trips> mTrips;

    private final Context context;

    private final LayoutInflater mInflater;

    public TripListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TripListAdapter.TripListViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_list_item, parent, false);

        return new TripListAdapter.TripListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripListAdapter.TripListViewHolder holder, int position){
        Trips currentTrip = mTrips.get(position);
        holder.tripItemView.setText(currentTrip.getTripName());
    }

    public void setTrips(List<Trips> trip){
        mTrips = trip;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        if(mTrips != null) {
            return mTrips.size();
        } else return 0;
    }
}
