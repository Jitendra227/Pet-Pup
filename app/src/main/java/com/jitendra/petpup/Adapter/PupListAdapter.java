package com.jitendra.petpup.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jitendra.petpup.R;
import com.jitendra.petpup.model.data.PupItems;

import java.util.ArrayList;

public class PupListAdapter extends RecyclerView.Adapter<PupListAdapter.ViewHolder> {

    private static final String TAG = "PupListAdapter";

    private final OnItemClickListener listener;
    ArrayList<PupItems> pupItems;
    Context context;
    int resources;


    public PupListAdapter(ArrayList<PupItems> pupItems, Context context, int resources, OnItemClickListener listener) {
        this.pupItems = pupItems;
        this.context = context;
        this.resources = resources;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pup_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(pupItems.get(position), listener);
        //PupItems details = pupItems.get(position);

        //holder.pupName.setText(details.getMessage());
    }

    @Override
    public int getItemCount() {
        return pupItems.size();
    }

    public interface OnItemClickListener {
        void onItemClick(PupItems item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final Button pupName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pupName = itemView.findViewById(R.id.pup_name_btn);
        }

        public void bind(PupItems pupItems, OnItemClickListener listener) {

            pupName.setText(pupItems.getMessage());

            pupName.setOnClickListener(v -> listener.onItemClick(pupItems));
        }
    }
}
