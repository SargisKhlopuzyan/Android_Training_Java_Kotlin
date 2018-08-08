package com.example.android_video_listing_mvp.swipetabfragments.SavedListFragment.Views;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.example.android_video_listing_mvp.activity.presenter.manager.pojo.VideoListInfo;
import com.example.android_video_listing_mvp.viewmvp.ViewMvp;

import java.util.List;

/**
 * Created by nitinagarwal on 3/13/17.
 */

public interface SavedListView extends ViewMvp{
    void bindSavedVideoList(List<String> savedVideoList, VideoListInfo videoListInfo);
    ObservableListView getSavedListView();
}
