package com.imagebrowser.recluseguo.mediabrowser.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imagebrowser.recluseguo.mediabrowser.MainActivity;
import com.imagebrowser.recluseguo.mediabrowser.R;
import com.imagebrowser.recluseguo.mediabrowser.adapters.RecyclerViewAdapter;
import com.imagebrowser.recluseguo.mediabrowser.decorators.DividerGridItemDecoration;
import com.imagebrowser.recluseguo.mediabrowser.listeners.ItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by recluseguo on 2016/7/19.
 */
public class MediaFragment extends Fragment implements ItemClickListener{

    public static final String typeKey = "type.key";
    private int type;
    @BindView(R.id.media_recycler_view)
    RecyclerView mediaRecyclerView;
    private List<String> stringList;

    public static MediaFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(typeKey, type);
        MediaFragment fragment = new MediaFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt(typeKey);
        switch (type){
            case TabItemFragment.LOCAL:
                stringList = MainActivity.getLocalImages(getActivity());
                break;
            case TabItemFragment.NET:
                stringList = MainActivity.getNetImages();
                break;
            case TabItemFragment.VIDEO:
                stringList = MainActivity.getLocalVideos(getActivity());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.media_fragment, container, false);

        ButterKnife.bind(this, view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), stringList);
        adapter.setOnItemClickListener(this);
        mediaRecyclerView.setAdapter(adapter);
        mediaRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mediaRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //mediaRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        return view;
    }

    @Override
    public void onItemClicked(int position) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.main_container);
        if(type == TabItemFragment.VIDEO){
            fragmentManager.beginTransaction()
                    .hide(fragment)
                    .add(R.id.main_container, VideoItemFragment.getInstance(stringList.get(position)))
                    .addToBackStack(null)
                    .commit();
            return;
        }

        fragmentManager.beginTransaction()
                .hide(fragment)
                .add(R.id.main_container, MediaItemPagerFragment.getInstance(type, position))
                .addToBackStack(null)
                .commit();
    }
}

