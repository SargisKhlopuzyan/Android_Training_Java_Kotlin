package com.example.android_video_listing_mvp.activity.presenter.manager;

/**
 * Created by nitinagarwal on 4/10/17.
 */

public interface VideoListUpdateManager {

    void updateForDeleteVideo(int id);
    void updateForRenameVideo(int id, String newFilePath, String updatedTitle);
}
