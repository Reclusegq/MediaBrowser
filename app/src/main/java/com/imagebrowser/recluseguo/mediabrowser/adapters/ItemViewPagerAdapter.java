package com.imagebrowser.recluseguo.mediabrowser.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.imagebrowser.recluseguo.mediabrowser.fragments.MediaItemFragment;
import com.imagebrowser.recluseguo.mediabrowser.fragments.MediaItemPagerFragment;

import java.util.List;

/**
 * Created by recluseguo on 2016/7/19.
 */
public class ItemViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> stringList;

    public ItemViewPagerAdapter(FragmentManager fm, List<String> stringList) {
        super(fm);
        this.stringList = stringList;


    }

    @Override
    public Fragment getItem(int position) {
        return MediaItemFragment.getInstance(stringList.get(position));
    }

    @Override
    public int getCount() {
        return stringList.size();
    }
}
