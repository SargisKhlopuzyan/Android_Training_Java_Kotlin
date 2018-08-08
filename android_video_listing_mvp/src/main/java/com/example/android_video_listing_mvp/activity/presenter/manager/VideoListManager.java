package com.example.android_video_listing_mvp.activity.presenter.manager;

import com.example.android_video_listing_mvp.activity.presenter.manager.pojo.VideoListInfo;

/**
 * Created by nitinagarwal on 3/7/17.
 */

public interface VideoListManager {

    interface VideoListManagerListener {
        void onVideoListUpdate(VideoListInfo videoListInfo);
    }

    void getVideosWithNewSorting(int sortType);
    void registerListener(VideoListManagerListener videoListManagerListener);

    void unRegisterListener();

}
