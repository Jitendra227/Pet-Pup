package com.jitendra.petpup.Adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;
import com.jitendra.petpup.BreedDetailScreen;
import com.jitendra.petpup.R;
import com.jitendra.petpup.model.data.BreedImages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BreedDetailAdapter extends RecyclerView.Adapter<BreedDetailAdapter.ViewHolder> {

    private static final String TAG = "((BreedDetailAdapter))--->";
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    Context context;
    ArrayList<BreedImages> breedImagesArrayList;
    int resources;
    private Activity activity;

    public BreedDetailAdapter(ArrayList<BreedImages> breedImagesArrayList, Context context, int resources, Activity activity) {
        this.breedImagesArrayList = breedImagesArrayList;
        this.context = context;
        this.resources = resources;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BreedDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.specific_breed_pics, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreedDetailAdapter.ViewHolder holder, int position) {
        holder.downloadBtn.setText("Download");
        holder.downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage(holder);
            }
        });
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

    }
    private void saveImage(ViewHolder holder) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        FileOutputStream fileOutputStream = null;
        File file = getDisc();

        if(!file.exists() && !file.mkdirs()) {
            file.mkdirs();

        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = simpleDateFormat.format(new Date());
        String name = "IMG" + date+ ".jpg";
        String file_name = file.getAbsolutePath()+"/"+name;
        File newFile = new File(file_name);

        try {
            BitmapDrawable draw = (BitmapDrawable) holder.breedImages.getDrawable();
            Bitmap bitmap = draw.getBitmap();
            fileOutputStream = new FileOutputStream(newFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            Toast.makeText(context, "image downloaded", Toast.LENGTH_SHORT).show();
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        refreshGallery(newFile);
    }

    private void refreshGallery(File file) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        context.sendBroadcast(intent);
    }

    private File getDisc() {
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(file,"BreedPics");
    }

}
