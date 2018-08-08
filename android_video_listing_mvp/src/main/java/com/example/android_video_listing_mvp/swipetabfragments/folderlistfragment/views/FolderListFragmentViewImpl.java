package com.example.android_video_listing_mvp.swipetabfragments.folderlistfragment.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_video_listing_mvp.R;
import com.example.android_video_listing_mvp.activity.presenter.manager.pojo.VideoListInfo;
import com.example.android_video_listing_mvp.swipetabfragments.adapters.FolderListAdapter;
import com.example.android_video_listing_mvp.swipetabfragments.folderlistfragment.ObservableFolderList.ObservableExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nitinagarwal on 3/15/17.
 */

public class FolderListFragmentViewImpl implements FolderListFragmentView {

    View mFragmentFolderListView;
    ObservableExpandableListView mExpandableListView;
    FolderListAdapter mFolderListAdapter;


    public FolderListFragmentViewImpl(Context context, ViewGroup container, LayoutInflater inflater)
    {
        mFragmentFolderListView = inflater.inflate(R.layout.tab_folderlist, container, false);
        mExpandableListView = (ObservableExpandableListView) mFragmentFolderListView.findViewById(R.id.expandablelistview);
        mFolderListAdapter = new FolderListAdapter(context);
        mExpandableListView.setAdapter(mFolderListAdapter);
    }

    @Override
    public ObservableExpandableListView getExpandableListView() {
        return mExpandableListView;
    }

    @Override
    public void bindVideoList(HashMap<String, List<String>> folderListHashMap, ArrayList<String> folderNames, VideoListInfo videoListInfo)
    {
        mFolderListAdapter.bindVideoList(folderListHashMap, folderNames, videoListInfo);
        mFolderListAdapter.notifyDataSetChanged();
    }

    @Override
    public View getRootView() {
        return mFragmentFolderListView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }
}
