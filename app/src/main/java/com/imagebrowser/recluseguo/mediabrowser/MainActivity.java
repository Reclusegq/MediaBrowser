package com.imagebrowser.recluseguo.mediabrowser;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.imagebrowser.recluseguo.mediabrowser.fragments.TabItemFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_container)FrameLayout container;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.main_container);
        if(fragment == null){
            fragment = new TabItemFragment();
            fragmentManager.beginTransaction().add(R.id.main_container, fragment).commit();
        }
    }

    public static List<String> getLocalImages(Context context){
        List<String> result = new ArrayList<>();
        Uri uriImage = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        //Uri uriVideo = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(uriImage, null, null, null, null);
        if (cursor == null || cursor.getCount() <= 0) return null; // 没有图片
        while (cursor.moveToNext())
        {
            int index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String path = cursor.getString(index); // 文件地址
            File file = new File(path);
            if (file.exists())
            {
                result.add(path);
            }
        }
        return result;
    }

    public static List<String> getLocalVideos(Context context){
        List<String> result = new ArrayList<>();
        Uri uriVideo = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(uriVideo, null, null, null, null);
        if (cursor == null || cursor.getCount() <= 0) return null; // 没有图片
        while (cursor.moveToNext())
        {
            int index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String path = cursor.getString(index); // 文件地址
            File file = new File(path);
            if (file.exists())
            {
                result.add(path);
            }
        }
        return result;
    }

    public static List<String> getNetImages(){
        List<String> result = new ArrayList<>();
        result.addAll(Arrays.asList("https://img3.doubanio.com/view/photo/photo/public/p2165037365.jpg",
                "https://img1.doubanio.com/view/photo/photo/public/p2165037359.jpg",
                "https://img3.doubanio.com/view/photo/photo/public/p2165037355.jpg",
                "https://img3.doubanio.com/view/photo/photo/public/p2165037343.jpg",
                "https://img3.doubanio.com/view/photo/photo/public/p2165037340.jpg",
                "https://img3.doubanio.com/view/photo/photo/public/p2165037335.jpg",
                "https://img1.doubanio.com/view/photo/photo/public/p2165037328.jpg",
                "https://img3.doubanio.com/view/photo/photo/public/p2165037311.jpg",
                "https://img1.doubanio.com/view/photo/photo/public/p2165037309.jpg"));

        for(int i = 10; i < 100; i++){
            result.add("https://img3.doubanio.com/view/photo/photo/public/p21550373" + i + ".jpg");
        }
        return result;
    }

}
