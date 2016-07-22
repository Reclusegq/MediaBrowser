package com.imagebrowser.recluseguo.mediabrowser.listeners;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Button;

import java.util.List;

/**
 * Created by recluseguo on 2016/7/19.
 */
public class ViewPagerListener implements ViewPager.OnPageChangeListener {

    private List<Fragment> fragmentList;
    private List<Button> buttonList;

    public ViewPagerListener(@Nullable List<Fragment> fragmentList, @Nullable List<Button> buttonList){
        this.fragmentList = fragmentList;
        this.buttonList = buttonList;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int nextIndex = position + 1;
        if(positionOffset > 0){
            if( null != buttonList ){
                //设置tab的颜色渐变效果
                buttonList.get(nextIndex).setAlpha(positionOffset);
                buttonList.get(position).setAlpha(1 - positionOffset);
            }
        if( null != fragmentList && null != fragmentList.get(position).getView()
                && null != fragmentList.get(nextIndex).getView() ){
                //设置fragment的颜色渐变效果
                fragmentList.get(nextIndex).getView().setAlpha(positionOffset);
                fragmentList.get(position).getView().setAlpha(1 - positionOffset);
                //设置fragment滑动视图由大到小，由小到大的效果
                fragmentList.get(nextIndex).getView().setScaleX(0.5F + positionOffset/2);
                fragmentList.get(nextIndex).getView().setScaleY(0.5F + positionOffset/2);
                fragmentList.get(position).getView().setScaleX(1-(positionOffset/2));
                fragmentList.get(position).getView().setScaleY(1-(positionOffset/2));
            }
        }
    }

    @Override
    public void onPageSelected(int position) {
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

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
