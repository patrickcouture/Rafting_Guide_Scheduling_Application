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
import com.example.c868capstone_raftingguideschedulingapplication.entities.Guides;

import java.util.List;

public class TripsEquipmentAdapter extends RecyclerView.Adapter<TripsEquipmentAdapter.TripsEquipViewHolder>{

    class TripsEquipViewHolder extends RecyclerView.ViewHolder {

        private final TextView assignTripItemView;

        private TripsEquipViewHolder (View view) {
            super(view);
            assignTripItemView = view.findViewById(R.id.assocTripsTxView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Equipment current = mTripsEquip.get(position);
                    Intent intent = new Intent(context, EquipmentDetails.class);
                    intent.putExtra("equipmentID", current.getEquipmentID());
                    intent.putExtra("equipmentName", current.getEquipmentName());
                    intent.putExtra("equipmentMaxCapacity", current.getEquipmentMaxCapacity());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Equipment> mTripsEquip;

    private final Context context;

    private final LayoutInflater mInflater;

    public TripsEquipmentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TripsEquipmentAdapter.TripsEquipViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_list_item, parent, false);

        return new TripsEquipmentAdapter.TripsEquipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripsEquipmentAdapter.TripsEquipViewHolder holder, int position){
        if(mTripsEquip != null) {
            Equipment currentEquipment = mTripsEquip.get(position);
            String name = currentEquipment.getEquipmentName();
            holder.assignTripItemView.setText(name);
        } else {
            holder.assignTripItemView.setText("No Trip Name");
        }
    }

    public void setEquipment(List<Equipment> equipment){
        mTripsEquip = equipment;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        if(mTripsEquip != null) {
            return mTripsEquip.size();
        } else return 0;
    }
}
