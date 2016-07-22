package com.imagebrowser.recluseguo.mediabrowser.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.imagebrowser.recluseguo.mediabrowser.R;
import com.imagebrowser.recluseguo.mediabrowser.listeners.ItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by recluseguo on 2016/7/18.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ImageViewHolder> {

    Context context;
    List<String> imageList;

    private ItemClickListener itemClickListener;


    public RecyclerViewAdapter(Context context, List<String> imageList){
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageViewHolder viewHolder = new ImageViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.recycler_view_item, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Glide.with(context)
                .load(imageList.get(position))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .centerCrop()
                .into(holder.imageView);

    }

    public void setOnItemClickListener(ItemClickListener listener){
        this.itemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.image_item)
        ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemClickListener != null){
                        itemClickListener.onItemClicked(getAdapterPosition());
                    }
                }
            });

        }

    }
}
