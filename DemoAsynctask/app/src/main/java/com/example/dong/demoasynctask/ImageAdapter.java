package com.example.dong.demoasynctask;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolderImage> {

    private Context mContext;
    private List<Image> mImages;

    public ImageAdapter(Context context, List<Image> images) {
        this.mContext = context;
        this.mImages = images;
    }

    public void setImageList(List<Image> imageList) {
        this.mImages = imageList;
    }

    @NonNull
    @Override
    public ViewHolderImage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false);
        return new ViewHolderImage(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderImage holder, int position) {
        Glide.with(mContext)
                .load(mImages.get(position).getPath())
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mImages == null ? 0 : mImages.size();
    }

    public class ViewHolderImage extends RecyclerView.ViewHolder {

        ImageView mImage;

        public ViewHolderImage(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image_item);
        }
    }
}
