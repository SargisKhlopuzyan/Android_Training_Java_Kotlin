package com.example.android_video_listing_mvp.activity.views;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.android_video_listing_mvp.viewmvp.ViewMvp;

/**
 * Created by nitinagarwal on 3/5/17.
 */

public interface ViewMvpVideoList extends ViewMvp{
    ViewPager getViewPager();
}
