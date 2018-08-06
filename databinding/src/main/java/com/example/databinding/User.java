package com.example.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class User extends BaseObservable {

    String name;
    String email;
    String profileImage;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }


    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @BindingAdapter("android:profileImage")
    public static void loadImage(ImageView view, String imageUrl) {
//        Glide.with(view.getContext()).load(imageUrl).into(view);

        // If you consider Picasso, follow the below
         Picasso.get().load(imageUrl).placeholder(R.drawable.placeholder).into(view);
    }


    // ObservableField
    public static ObservableField<String> nameObservable = new ObservableField<>();
    public static ObservableField<String> emailObservable = new ObservableField<>();

    public ObservableField<String> getNameObservable() {
        return nameObservable;
    }

    public ObservableField<String> getEmailObservable() {
        return emailObservable;
    }
    //

}
