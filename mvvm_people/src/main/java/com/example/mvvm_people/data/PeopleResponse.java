package com.example.mvvm_people.data;

import com.example.mvvm_people.model.People;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PeopleResponse {

    @SerializedName("results") private List<People> peopleList;

    public List<People> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<People> mPeopleList) {
        this.peopleList = mPeopleList;
    }
}

