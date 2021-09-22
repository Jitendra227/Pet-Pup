package com.jitendra.petpup.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;
import com.jitendra.petpup.R;
import com.jitendra.petpup.model.data.PupImages;
import com.jitendra.petpup.model.data.PupItems;

import java.util.ArrayList;

public class RandomScreenAdapter extends RecyclerView.Adapter<RandomScreenAdapter.ViewHolder> {

    Context context;
    ArrayList<PupImages> pupImages;
    int resources;

    public RandomScreenAdapter(Context context, ArrayList<PupImages> pupImages, int resources) {
        this.context = context;
        this.pupImages = pupImages;
        this.resources = resources;
    }

    @NonNull
    @Override
    public RandomScreenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.random_pup_pics, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomScreenAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(pupImages.get(position).getMessage())
                .into(holder.pupPics);
    }

    @Override
    public int getItemCount() {
        return pupImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView pupPics;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pupPics = itemView.findViewById(R.id.pup_image);
        }

//        public void bind(PupImages pupImages) {
//
//            GlideToVectorYou
//                    .init()
//                    .with(context)
//                    .withListener(new GlideToVectorYouListener() {
//                        @Override
//                        public void onLoadFailed() {
////                            Toast.makeText(context, "Load failed", Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onResourceReady() {
////                            Toast.makeText(context, "Image ready", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .load(Uri.parse(pupImages.getMessage()), pupPics);
//        }
    }
}
