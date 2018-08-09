package com.example.android_video_listing_mvp.swipetabfragments.ListFragement.views;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.example.android_video_listing_mvp.activity.presenter.manager.pojo.VideoListInfo;
import com.example.android_video_listing_mvp.viewmvp.ViewMvp;

import java.util.List;

/**
 * Created by nitinagarwal on 3/12/17.
 */
public interface ListFragmentView extends ViewMvp {
    void bindVideoList(List<String> videoList, VideoListInfo videoListInfo);
    ObservableListView getListView();
}