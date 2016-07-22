package com.imagebrowser.recluseguo.mediabrowser.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imagebrowser.recluseguo.mediabrowser.MainActivity;
import com.imagebrowser.recluseguo.mediabrowser.R;
import com.imagebrowser.recluseguo.mediabrowser.adapters.ItemViewPagerAdapter;
import com.imagebrowser.recluseguo.mediabrowser.adapters.ViewPagerAdapter;
import com.imagebrowser.recluseguo.mediabrowser.listeners.ViewPagerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by recluseguo on 2016/7/19.
 */
public class MediaItemPagerFragment extends Fragment {

    @BindView(R.id.vp_media_item_pager)
    ViewPager vpMediaItemPager;
    //private List<Fragment> fragmentList;
    private int position;
    private int type;
    public static final String POS = "position.key";
    public static final String TYPE = "type.key";

    public static MediaItemPagerFragment getInstance(int type, int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);
        bundle.putInt(POS, position);
        MediaItemPagerFragment fragment = new MediaItemPagerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt(TYPE);
        position = getArguments().getInt(POS);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.media_item_pager_fragment, container, false);
        ButterKnife.bind(this, view);
        //fragmentList = getImageList(type);
        vpMediaItemPager.setAdapter(new ItemViewPagerAdapter(getFragmentManager(), getImageList(type)));
        //vpMediaItemPager.addOnPageChangeListener(new ViewPagerListener(fragmentList, null));
        vpMediaItemPager.setCurrentItem(position);
        return view;
    }

    public List<String> getImageList(int type){
        List<String> list = null;
        switch (type){
            case TabItemFragment.LOCAL:
                list = MainActivity.getLocalImages(getActivity());
                break;
            case TabItemFragment.NET:
                list = MainActivity.getNetImages();
                break;
        }

        /*List<Fragment> fragmentList = new ArrayList<>(list.size());
        for(int i = 0; i < list.size(); i++){
            fragmentList.add(MediaItemFragment.getInstance(list.get(i)));
        }*/
        return list;
    }
}
