package com.example.mvvm_people;

import android.app.Application;
import android.content.Context;

import com.example.mvvm_people.data.PeopleFactory;
import com.example.mvvm_people.data.PeopleService;

import java.util.Observer;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PeopleApplication extends Application {

    private PeopleService peopleService;
    private Scheduler scheduler;

    private static PeopleApplication get(Context context) {
        return (PeopleApplication) context.getApplicationContext();
    }

    public static PeopleApplication create(Context context) {
        return PeopleApplication.get(context);
    }

    public PeopleService getPeopleService() {
        if (peopleService == null) {
            peopleService = PeopleFactory.create();
        }

        return peopleService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setPeopleService(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

}
