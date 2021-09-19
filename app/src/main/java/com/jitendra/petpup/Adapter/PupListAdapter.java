package com.jitendra.petpup.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jitendra.petpup.R;
import com.jitendra.petpup.model.data.PupItems;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class PupListAdapter extends RecyclerView.Adapter<PupListAdapter.ViewHolder> {

    private static final String TAG = "PupListAdapter";

    ArrayList<PupItems> pupItems;
    Context context;


    public PupListAdapter(ArrayList<PupItems> pupItems, Context context) {
        this.pupItems = pupItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pup_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(pupItems.get(position));
    }

    @Override
    public int getItemCount() {
        return pupItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Button pupName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pupName = itemView.findViewById(R.id.pup_name_btn);
        }

        public void bind(PupItems pupItems) {
            pupName.setText(pupItems.getBreeds());

            pupName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: successfully clicked");
                }
            });
        }
    }
}
