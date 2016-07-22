package com.imagebrowser.recluseguo.mediabrowser.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.imagebrowser.recluseguo.mediabrowser.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by recluseguo on 2016/7/19.
 */
public class MediaItemFragment extends Fragment {

    @BindView(R.id.iv_media_item)
    ImageView ivMediaItem;
    private String path;
    public static final String PATH = "media.path";

    public static MediaItemFragment getInstance(String path) {
        Bundle bundle = new Bundle();
        bundle.putString(PATH, path);
        MediaItemFragment fragment = new MediaItemFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        path = getArguments().getString(PATH);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.media_item_fragment, container, false);
        ButterKnife.bind(this, view);
        Glide.with(getActivity())
                .load(path)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .fitCenter()
                .into(ivMediaItem);
        return view;
    }
}
