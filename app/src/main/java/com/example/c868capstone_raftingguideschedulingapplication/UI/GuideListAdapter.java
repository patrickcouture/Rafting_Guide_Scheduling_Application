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

public class GuideListAdapter extends RecyclerView.Adapter<GuideListAdapter.GuideViewHolder>{


    class GuideViewHolder extends RecyclerView.ViewHolder {

        private final TextView guideItemView;

        private GuideViewHolder (View view) {
            super(view);
            guideItemView = view.findViewById(R.id.guideListTextView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Guides current = mGuides.get(position);
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
    private List<Guides> mGuides;

    private final Context context;

    private final LayoutInflater mInflater;

    public GuideListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public GuideViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guide_list_item, parent, false);

        return new GuideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuideViewHolder holder, int position){
        Guides currentGuide = mGuides.get(position);
        holder.guideItemView.setText(currentGuide.getGuideName());
    }

    public void setGuides(List<Guides> guides){
        mGuides = guides;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        if(mGuides != null) {
            return mGuides.size();
        } else return 0;
    }


}
