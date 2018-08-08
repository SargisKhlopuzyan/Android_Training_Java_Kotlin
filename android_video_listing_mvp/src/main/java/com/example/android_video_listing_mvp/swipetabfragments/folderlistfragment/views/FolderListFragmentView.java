package com.example.android_video_listing_mvp.swipetabfragments.folderlistfragment.views;

import com.example.android_video_listing_mvp.activity.presenter.manager.pojo.VideoListInfo;
import com.example.android_video_listing_mvp.swipetabfragments.folderlistfragment.ObservableFolderList.ObservableExpandableListView;
import com.example.android_video_listing_mvp.viewmvp.ViewMvp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nitinagarwal on 3/15/17.
 */
public interface FolderListFragmentView extends ViewMvp{
    ObservableExpandableListView getExpandableListView();
    void bindVideoList(HashMap<String, List<String>> folderListHashMap, ArrayList<String> folderNames, VideoListInfo videoListInfo);
}
