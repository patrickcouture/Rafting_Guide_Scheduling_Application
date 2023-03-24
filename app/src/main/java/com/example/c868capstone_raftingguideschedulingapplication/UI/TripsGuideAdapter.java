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

import java.util.List;

public class TripsGuideAdapter extends RecyclerView.Adapter<TripsGuideAdapter.TripsGuideViewHolder>{

    class TripsGuideViewHolder extends RecyclerView.ViewHolder {

        private final TextView assignTripItemView;

        private TripsGuideViewHolder (View view) {
            super(view);
            assignTripItemView = view.findViewById(R.id.assocTripsTxView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Guides current = mTripsGuide.get(position);
                    Intent intent = new Intent(context, GuideDetails.class);
                    intent.putExtra("guideID", current.getGuideID());
                    intent.putExtra("guideName", current.getGuideName());
                    intent.putExtra("guideEmail", current.getGuideEmail());
                    intent.putExtra("guidePhone", current.getGuidePhone());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Guides> mTripsGuide;

    private final Context context;

    private final LayoutInflater mInflater;

    public TripsGuideAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TripsGuideAdapter.TripsGuideViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_list_item, parent, false);

        return new TripsGuideAdapter.TripsGuideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripsGuideAdapter.TripsGuideViewHolder holder, int position){
        if(mTripsGuide != null) {
            Guides currentGuide = mTripsGuide.get(position);
            String name = currentGuide.getGuideName();
            holder.assignTripItemView.setText(name);
        } else {
            holder.assignTripItemView.setText("No Trip Name");
        }
    }

    public void setGuides(List<Guides> guides){
        mTripsGuide = guides;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        if(mTripsGuide != null) {
            return mTripsGuide.size();
        } else return 0;
    }
}
