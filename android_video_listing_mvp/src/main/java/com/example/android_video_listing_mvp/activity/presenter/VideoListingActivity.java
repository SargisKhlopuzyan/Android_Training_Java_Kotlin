package com.example.android_video_listing_mvp.activity.presenter;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android_video_listing_mvp.R;
import com.example.android_video_listing_mvp.swipetabfragments.ListFragement.presenter.ListFragmentImpl;
import com.example.android_video_listing_mvp.swipetabfragments.SavedListFragment.presenter.SavedListFragmentImpl;
import com.example.android_video_listing_mvp.swipetabfragments.folderlistfragment.presenter.FolderListFragmentImpl;
import com.example.android_video_listing_mvp.utils.FolderListGenerator;
import com.example.android_video_listing_mvp.utils.VideoSearch;
import com.example.android_video_listing_mvp.activity.presenter.manager.pojo.VideoListInfo;
import com.example.android_video_listing_mvp.activity.presenter.manager.VideoListManager;
import com.example.android_video_listing_mvp.activity.presenter.manager.VideoListManagerImpl;
import com.example.android_video_listing_mvp.activity.views.VideoListingViewImpl;
import com.example.android_video_listing_mvp.activity.views.ViewMvpSearch;
import com.example.android_video_listing_mvp.utils.longpressmenuoptions.LongPressOptions;

import java.security.Permission;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class VideoListingActivity extends AppCompatActivity implements VideoListManager.VideoListManagerListener, VideoUserInteraction{

    private static final String SORT_TYPE_PREFERENCE_KEY = "sort_type";
    private int mSortingType;
    public static final int NAME_ASC = 0;
    public static final int NAME_DESC = 1;
    public static final int DATE_ASC = 2;
    public static final int DATE_DESC = 3;
    public static final int SIZE_ASC = 4;
    public static final int SIZE_DESC = 5;

    public static final int SHARE_VIDEO = 0;
    public static final int DELETE_VIDEO = 1;
    public static final int RENAME_VIDEO = 2;

    VideoListingViewImpl mVideoListingViewImpl;
    VideoListManagerImpl mVideoListManagerImpl;
    ViewPager mViewPager;
    ListFragmentImpl mListFragment;
    SavedListFragmentImpl mSavedListFragment;
    FolderListFragmentImpl mFolderListFragment;
    VideoListInfo mVideoListInfo;

    boolean mIsInSearchMode;
    String mSearchText;
    private int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 12;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to read the contacts
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique
            return;
        }
        initDatAfterRequestGranted();
    }

    private void initDatAfterRequestGranted() {

        mVideoListingViewImpl = new VideoListingViewImpl(this, null);

        setContentView(mVideoListingViewImpl.getRootView());

        mViewPager = mVideoListingViewImpl.getViewPager();

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        mSortingType = settings.getInt(SORT_TYPE_PREFERENCE_KEY, 3);
        mVideoListManagerImpl = new VideoListManagerImpl(this, mSortingType);
        mVideoListManagerImpl.registerListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initDatAfterRequestGranted();
            }
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mVideoListingViewImpl.AddSearchBar(menu.findItem(R.id.action_search));

        ViewMvpSearch.SearchVideo searchVideoListener = new ViewMvpSearch.SearchVideo() {
            @Override
            public void onVideoSearched(String seachText) {

                mSearchText = seachText;
                if(seachText.trim().equals("")) {
                    mIsInSearchMode = false;
                    mVideoListInfo.getVideosList().clear();
                    mVideoListInfo.getVideosList().addAll(mVideoListInfo.getVideoListBackUp());
                    mVideoListInfo.getFolderListHashMap().clear();
                    mVideoListInfo.getFolderListHashMap().putAll(mVideoListInfo.getFolderListHashMapBackUp());
                } else {
                    mIsInSearchMode = true;

                    mVideoListInfo.setVideosList(VideoSearch.SearchResult(seachText, mVideoListInfo.getVideoListBackUp()));
                    mVideoListInfo.setFolderListHashMap(VideoSearch.SearchResult(seachText,
                            mVideoListInfo.getFolderListHashMapBackUp()));
                }

                mVideoListInfo.setSavedVideoList(FolderListGenerator.getSavedVideoListFromFolderHashMap(
                                              mVideoListInfo.getFolderListHashMap()));

                if(mListFragment != null)
                    mListFragment.bindVideoList(mVideoListInfo);
                if(mFolderListFragment != null)
                    mFolderListFragment.bindVideoList(mVideoListInfo);
                if(mSavedListFragment != null)
                    mListFragment.bindVideoList(mVideoListInfo);
            }
        };

        mVideoListingViewImpl.SetSearchListener(searchVideoListener);

        setSortingOptionChecked(menu);

        return true;
    }

    private void setSortingOptionChecked(Menu menu) {
        switch (mSortingType) {
            case NAME_ASC:
                menu.findItem(R.id.sort_name_asc).setChecked(true);
                break;
            case NAME_DESC:
                menu.findItem(R.id.sort_name_dsc).setChecked(true);
                break;
            case DATE_ASC:
                menu.findItem(R.id.sort_date_asc).setChecked(true);
                break;
            case DATE_DESC:
                menu.findItem(R.id.sort_date_dsc).setChecked(true);
                break;
            case SIZE_ASC:
                menu.findItem(R.id.sort_size_asc).setChecked(true);
                break;
            case SIZE_DESC:
                menu.findItem(R.id.sort_size_dsc).setChecked(true);
                break;
            default:
                menu.findItem(R.id.sort_date_dsc).setChecked(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.sort_name_asc:
                updateSharedPreferenceAndGetNewList(NAME_ASC);
                item.setChecked(true);
                break;
            case R.id.sort_name_dsc:
                updateSharedPreferenceAndGetNewList(NAME_DESC);
                item.setChecked(true);
                break;
            case R.id.sort_date_asc:
                updateSharedPreferenceAndGetNewList(DATE_ASC);
                item.setChecked(true);
                break;
            case R.id.sort_date_dsc:
                updateSharedPreferenceAndGetNewList(DATE_DESC);
                item.setChecked(true);
                break;
            case R.id.sort_size_asc:
                updateSharedPreferenceAndGetNewList(SIZE_ASC);
                item.setChecked(true);
                break;
            case R.id.sort_size_dsc:
                updateSharedPreferenceAndGetNewList(SIZE_DESC);
                item.setChecked(true);
                break;
            default:
                updateSharedPreferenceAndGetNewList(DATE_DESC);
                item.setChecked(true);
        }

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateSharedPreferenceAndGetNewList(int sortType) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(SORT_TYPE_PREFERENCE_KEY, sortType);
        editor.apply();
        mVideoListManagerImpl.getVideosWithNewSorting(sortType);
    }

    @Override
    public void onVideoListUpdate(VideoListInfo videoListInfo) {

        mVideoListInfo = videoListInfo;

        if (mIsInSearchMode) {
            mVideoListInfo.setVideosList(VideoSearch.SearchResult(mSearchText, mVideoListInfo.getVideoListBackUp()));
            mVideoListInfo.setFolderListHashMap(VideoSearch.SearchResult(mSearchText,
                            mVideoListInfo.getFolderListHashMapBackUp()));
        } else {
            if(mVideoListInfo.getVideosList() != null)
                mVideoListInfo.getVideosList().clear();
            mVideoListInfo.getVideosList().addAll(mVideoListInfo.getVideoListBackUp());

            if(mVideoListInfo.getFolderListHashMap() != null)
                mVideoListInfo.getFolderListHashMap().clear();
            mVideoListInfo.getFolderListHashMap().putAll(mVideoListInfo.getFolderListHashMapBackUp());
        }

        mVideoListInfo.setSavedVideoList(FolderListGenerator.getSavedVideoListFromFolderHashMap(
                mVideoListInfo.getFolderListHashMap()));
        fetchFolderList();
        fetchVideoList();
        fetchSavedList();
    }

    public void fetchVideoList() {
        if(mListFragment != null && mVideoListInfo != null) {
            mListFragment.bindVideoList(mVideoListInfo);
        }
    }

    public void fetchSavedList() {
        if(mSavedListFragment != null && mVideoListInfo != null)
            mSavedListFragment.bindVideoList(mVideoListInfo);
    }

    public void fetchFolderList() {
        if(mFolderListFragment != null && mVideoListInfo != null)
            mFolderListFragment.bindVideoList(mVideoListInfo);
    }

    public void registerListener(ListFragmentImpl listFragment) {
        mListFragment = listFragment;
    }

    public void registerListener(FolderListFragmentImpl folderListFragment) {
        mFolderListFragment = folderListFragment;
    }

    public void registerListener(SavedListFragmentImpl savedListFragment) {
        mSavedListFragment = savedListFragment;
    }

    @Override
    public void onVideoSelected(String videoPath) {
        Toast.makeText(this, videoPath + "clicked", Toast.LENGTH_SHORT).show();
    }

    public VideoListingViewImpl getVideoListActivityView() {
        return mVideoListingViewImpl;
    }

    @Override
    public void onVideoLongPressed(String videoPath, int itemId) {

        switch (itemId)
        {
            case R.id.long_press_menu_share:
                LongPressOptions.shareFile(this, videoPath);
                break;

            case R.id.long_press_menu_delete:
                int deleteVideoId = mVideoListInfo.getVideoIdHashMap().get(videoPath);
                LongPressOptions.deleteFile(this, videoPath, deleteVideoId, mVideoListManagerImpl);
                Log.d("nitin123", "we are here");
                break;

            case R.id.long_press_menu_rename:
                String selectedVideoTitleWithExtension = mVideoListInfo.getVideoTitleHashMap().get(videoPath);
                int index = selectedVideoTitleWithExtension.lastIndexOf('.');
                String selectedVideoTitleForRename;
                String extensionValue;

                if (index > 0) {
                    selectedVideoTitleForRename = selectedVideoTitleWithExtension.substring(0, index);
                    extensionValue = selectedVideoTitleWithExtension.substring(index, selectedVideoTitleWithExtension.length());
                } else {
                    selectedVideoTitleForRename = selectedVideoTitleWithExtension;
                    extensionValue = "";
                }

                int renameVideoId = mVideoListInfo.getVideoIdHashMap().get(videoPath);
                LongPressOptions.renameFile(this, selectedVideoTitleForRename, videoPath,
                        extensionValue, renameVideoId, mVideoListManagerImpl);
                break;
        }

    }

}