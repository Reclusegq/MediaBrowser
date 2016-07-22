package com.imagebrowser.recluseguo.mediabrowser.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.imagebrowser.recluseguo.mediabrowser.MainActivity;
import com.imagebrowser.recluseguo.mediabrowser.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by recluseguo on 2016/7/19.
 */
public class VideoItemFragment extends Fragment{
    @BindView(R.id.video_view_setting)
    VideoView videoViewSetting;
    List<String> stringList;
    private String path;
    public static final String PATH = "video.path";

    public static VideoItemFragment getInstance(String path){
        Bundle bundle = new Bundle();
        bundle.putString(PATH, path);
        VideoItemFragment fragment = new VideoItemFragment();
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
        View view = inflater.inflate(R.layout.video_fragment, container, false);
        ButterKnife.bind(this, view);
        stringList = MainActivity.getLocalVideos(getActivity());
        videoViewSetting.setVideoPath(path);
        videoViewSetting.start();
        return view;
    }
}
