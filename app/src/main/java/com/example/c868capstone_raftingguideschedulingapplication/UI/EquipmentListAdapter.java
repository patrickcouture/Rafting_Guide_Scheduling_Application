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

public class EquipmentListAdapter extends RecyclerView.Adapter<EquipmentListAdapter.EquipViewHolder>{

    class EquipViewHolder extends RecyclerView.ViewHolder {

        private final TextView equipItemView;

        private EquipViewHolder (View view) {
            super(view);
            equipItemView = view.findViewById(R.id.equipListItemTextView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Equipment current = mEquipment.get(position);
                    Intent intent = new Intent(context, EquipmentDetails.class);
                    intent.putExtra("equipmentID", current.getEquipmentID());
                    intent.putExtra("equipmentName", current.getEquipmentName());
                    intent.putExtra("equipmentMaxCapacity", current.getEquipmentMaxCapacity());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Equipment> mEquipment;

    private final Context context;

    private final LayoutInflater mInflater;

    public EquipmentListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public EquipmentListAdapter.EquipViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.equip_list_item, parent, false);

        return new EquipmentListAdapter.EquipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipmentListAdapter.EquipViewHolder holder, int position){
        Equipment currentEquipment = mEquipment.get(position);
        holder.equipItemView.setText(currentEquipment.getEquipmentName());
    }

    public void setGuides(List<Equipment> equipment){
        mEquipment = equipment;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        if(mEquipment != null) {
            return mEquipment.size();
        } else return 0;
    }



}
