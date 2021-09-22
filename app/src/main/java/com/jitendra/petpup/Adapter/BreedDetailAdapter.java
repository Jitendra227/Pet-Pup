package com.jitendra.petpup.Adapter;

import android.app.DownloadManager;
import android.content.Context;
;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;
import com.jitendra.petpup.R;
import com.jitendra.petpup.model.data.BreedImages;

import java.util.ArrayList;

public class BreedDetailAdapter extends RecyclerView.Adapter<BreedDetailAdapter.ViewHolder> {

    Context context;
    ArrayList<BreedImages> breedImagesArrayList;
    int resources;

    public BreedDetailAdapter(ArrayList<BreedImages> breedImagesArrayList, Context context, int resources) {
        this.breedImagesArrayList = breedImagesArrayList;
        this.context = context;
        this.resources = resources;
    }

    @NonNull
    @Override
    public BreedDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.specific_breed_pics, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreedDetailAdapter.ViewHolder holder, int position) {
        holder.bind(breedImagesArrayList.get(position));
        Glide.with(context)
                .load(breedImagesArrayList.get(position).getMessage())
                .into(holder.breedImages);
    }

    @Override
    public int getItemCount() {
        return breedImagesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView breedImages;
        private Button downloadBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            breedImages = itemView.findViewById(R.id.breed_pic);
            downloadBtn = itemView.findViewById(R.id.download_btn);
        }

        public void bind(BreedImages breedImages) {
            downloadBtn.setText("Download");
        }

//        public void bind(PupImages pupImages) {
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
//                    .load(Uri.parse(pupImages.getMessage()), breedImages);
//        }
    }
}
