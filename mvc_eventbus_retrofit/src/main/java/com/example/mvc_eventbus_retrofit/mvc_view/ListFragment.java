package com.example.mvc_eventbus_retrofit.mvc_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

import com.example.mvc_eventbus_retrofit.R;
import com.example.mvc_eventbus_retrofit.util.Const;
import com.example.mvc_eventbus_retrofit.mvc_controller.FragmentRouter;
import com.example.mvc_eventbus_retrofit.mvc_controller.ModeLocator;
import com.example.mvc_eventbus_retrofit.mvc_model.ItemPhoto;
import com.example.mvc_eventbus_retrofit.mvc_model.ItemPhotosModel;

public class ListFragment extends Fragment {

    @BindView(R.id.itemCountTextView)
    TextView itemCountTextView;

    @BindView(R.id.itemPhotoListView)
    ListView itemPhotoListView;

    private final List<ItemPhoto> itemPhotoList = new ArrayList<>();
    private ItemPhotoListAdapter itemPhotoListAdapter;

    private Unbinder unbinder;

    public ListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view); // ButterKnife
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        itemPhotoListAdapter = new ItemPhotoListAdapter(getActivity(), R.layout.adapter_photo_item_list, itemPhotoList);
        itemPhotoListView.setAdapter(itemPhotoListAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateView();
        ((ItemPhotosModel) ModeLocator.get(ModeLocator.Tag.ITEM_PHOTOS)).load();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this); // EventBus
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this); // EventBus
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind(); // ButterKnife
    }

    // ListView
    @SuppressWarnings("unused")
    @OnItemClick(R.id.itemPhotoListView)
    public void onItemClickQiitaItemListView(int position) {
        Bundle args = new Bundle();
        itemPhotoList.get(position).setThumbnailUrl("https://www.google.com/");
        args.putString(Const.BundleKey.URL.toString(), itemPhotoList.get(position).getThumbnailUrl());
        FragmentRouter.replace(getFragmentManager(), R.id.container, FragmentRouter.Tag.DETAIL, args, FragmentRouter.Animation.SLIDE_IN_BOTTOM);
    }

    // EventBus
    @SuppressWarnings("unused")
    @Subscribe
    public void onEventMainThread(ItemPhotosModel.VideosLoadedEvent event) {
        if (event.isSuccess()) {
            updateView();
        }
    }

    // View
    private void updateView() {
        itemCountTextView.setText(((ItemPhotosModel) ModeLocator.get(ModeLocator.Tag.ITEM_PHOTOS)).getItemCount() + " Items");
        itemPhotoList.clear();
        itemPhotoList.addAll(((ItemPhotosModel) ModeLocator.get(ModeLocator.Tag.ITEM_PHOTOS)).getItemPhotosList());
        itemPhotoListAdapter.notifyDataSetChanged();
    }


    private void xxx(final int x) {

        final int xxx = 10;
    }

}
