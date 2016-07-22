package com.imagebrowser.recluseguo.mediabrowser.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.imagebrowser.recluseguo.mediabrowser.R;
import com.imagebrowser.recluseguo.mediabrowser.adapters.ViewPagerAdapter;
import com.imagebrowser.recluseguo.mediabrowser.listeners.ViewPagerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by recluseguo on 2016/7/19.
 */
public class TabItemFragment extends Fragment {

    @BindView(R.id.tab_main_viewpager)
    ViewPager tabMainViewpager;
    @BindView(R.id.local_btn_true)
    Button localBtnTrue;
    @BindView(R.id.net_btn_true)
    Button netBtnTrue;
    @BindView(R.id.setting_btn_true)
    Button settingBtnTrue;

    public static final int LOCAL = 0;
    public static final int NET = 1;
    public static final int VIDEO = 2;

    List<Fragment> fragmentList;
    List<Button> buttonList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentList = new ArrayList<>(3);
        buttonList = new ArrayList<>(3);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_item_fragment, container, false);
        ButterKnife.bind(this, view);
        fragmentList.add(MediaFragment.getInstance(LOCAL));
        fragmentList.add(MediaFragment.getInstance(NET));
        fragmentList.add(MediaFragment.getInstance(VIDEO));
        buttonList.addAll(Arrays.asList(localBtnTrue, netBtnTrue, settingBtnTrue));
        tabMainViewpager.setAdapter(new ViewPagerAdapter(getActivity().getSupportFragmentManager(), fragmentList));
        tabMainViewpager.addOnPageChangeListener(new ViewPagerListener(fragmentList, buttonList));
        tabMainViewpager.setCurrentItem(0);
        bottomSelected(0);


        return view;
    }

    @OnClick({R.id.local_btn_true, R.id.net_btn_true, R.id.setting_btn_true})
    void onClick(View view){
        switch (view.getId()){
            case R.id.local_btn_true:
                tabMainViewpager.setCurrentItem(0, false);
                break;
            case R.id.net_btn_true:
                tabMainViewpager.setCurrentItem(1, false);
                break;
            case R.id.setting_btn_true:
                tabMainViewpager.setCurrentItem(2, false);
                break;
        }
    }

    private void bottomSelected(int position){
        if( null != buttonList ){
            for(int i = 0; i < buttonList.size(); i++){
                if( i == position ){
                    buttonList.get(i).setAlpha(1.0f);
                }else{
                    buttonList.get(i).setAlpha(0.0f);
                }
            }
        }
    }
}
